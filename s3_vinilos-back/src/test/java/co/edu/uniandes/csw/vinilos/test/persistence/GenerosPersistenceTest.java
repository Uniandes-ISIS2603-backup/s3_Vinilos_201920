/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.persistence.GeneroPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author juan gonzalez
 */
@RunWith(Arquillian.class)
public class GenerosPersistenceTest {
    
   @PersistenceContext
   protected EntityManager em;
 
    @Inject
    private GeneroPersistence generoPersistence;
    
    private List<GeneroEntity> data = new ArrayList<>();
    
    @Inject
    UserTransaction utx;
 
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(GeneroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    GeneroPersistence ep;
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
     private void clearData() 
    {
        em.createQuery("delete from GeneroEntity").executeUpdate();
    }

     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GeneroEntity entity = factory.manufacturePojo(GeneroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     
    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        GeneroEntity genero = factory.manufacturePojo(GeneroEntity.class);
        GeneroEntity result = ep.create(genero);
        Assert.assertNotNull(result);
        GeneroEntity entity = em.find(GeneroEntity.class, result.getId());
        Assert.assertEquals(genero.getNombre(), entity.getNombre());
    }
    
    @Test
    public void getGeneroTest() 
    {
        GeneroEntity entity = data.get(0);
        GeneroEntity newEntity = generoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    
     @Test
    public void updateReviewTest()
    {
        GeneroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GeneroEntity newEntity = factory.manufacturePojo(GeneroEntity.class);
        newEntity.setId(entity.getId());
        generoPersistence.update(newEntity);
        GeneroEntity resp = em.find(GeneroEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
     
    }
    
     
    @Test
    public void deleteReviewTest() 
    {
        GeneroEntity entity = data.get(0);
        generoPersistence.delete(entity.getId());
        GeneroEntity deleted = em.find(GeneroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
