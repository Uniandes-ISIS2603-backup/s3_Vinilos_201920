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
    
    /**
     * Relacion con EntityManager
     */
   @PersistenceContext
   protected EntityManager em;
 
   /**
    * Relacion con la persistensia de genero
    */
   
    @Inject
    private GeneroPersistence generoPersistence;
    
    /**
     * Lista de generos
     */
    private List<GeneroEntity> data = new ArrayList<>();
    
    /**
     * user transaction
     */
    @Inject
    UserTransaction utx;
 
    /**
     * crea el Deployment
     * @return Deployment
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(GeneroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * relacion con genero persistence
     */
    @Inject
    GeneroPersistence ep;
    
    /**
     * test configuracion
     */
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
    
    /**
     * borrar datos
     */
     private void clearData() 
    {
        em.createQuery("delete from GeneroEntity").executeUpdate();
    }

     /**
      * agregar datos
      */
     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GeneroEntity entity = factory.manufacturePojo(GeneroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     /**
      * crea el test de la persistencia 
      */
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
    
    /**
     * test de busqueda del genero
     */
    @Test
    public void getGeneroTest() 
    {
        GeneroEntity entity = data.get(0);
        GeneroEntity newEntity = generoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    /**
     * Test de la actualizacion
     */
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
    
     /**
      * test de el borrado
      */
    @Test
    public void deleteReviewTest() 
    {
        GeneroEntity entity = data.get(0);
        generoPersistence.delete(entity.getId());
        GeneroEntity deleted = em.find(GeneroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
