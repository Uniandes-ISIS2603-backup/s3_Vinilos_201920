/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;


import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.persistence.EnvioPersistence;
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
public class EnvioPersistenceTest {

   @PersistenceContext
   protected EntityManager em;
 
    @Inject
    private EnvioPersistence envioPersistence;
    
    private List<EnvioEntity> data = new ArrayList<>();
    
    @Inject
    UserTransaction utx;
 
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EnvioEntity.class.getPackage())
                .addPackage(EnvioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    EnvioPersistence ep;
    
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
        em.createQuery("delete from EnvioEntity").executeUpdate();
    }

     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EnvioEntity entity = factory.manufacturePojo(EnvioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        EnvioEntity genero = factory.manufacturePojo(EnvioEntity.class);
        EnvioEntity result = ep.create(genero);
        Assert.assertNotNull(result);
      // EnvioEntity entity = em.find(EnvioEntity.class, result.getId());
        
    }
    
    @Test
    public void getEnvioTest() 
    {
        EnvioEntity entity = data.get(0);
        EnvioEntity newEntity = envioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
    }
    
    
     @Test
    public void updateReviewTest()
    {
        EnvioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EnvioEntity newEntity = factory.manufacturePojo(EnvioEntity.class);
        newEntity.setId(entity.getId());
        envioPersistence.update(newEntity);
       // EnvioEntity resp = em.find(EnvioEntity.class, entity.getId());
     
    }
    
     
    @Test
    public void deleteEnvioTest() 
    {
        EnvioEntity entity = data.get(0);
        envioPersistence.delete(entity.getId());
        EnvioEntity deleted = em.find(EnvioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
