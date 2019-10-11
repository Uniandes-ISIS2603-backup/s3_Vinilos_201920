/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.vinilos.persistence.MetodoDePagoPersistence;
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
 * @author Juan Diego Bogotá
 */
@RunWith(Arquillian.class)
public class MetodoDePagoPersistenceTest {
    
   @PersistenceContext
   private EntityManager em;
   
   @Inject
   MetodoDePagoPersistence ap;
    
   private List<MetodoDePagoEntity> metodosDePago = new ArrayList<MetodoDePagoEntity>();
   
    @Inject
    UserTransaction utx;

        /**
     * Configuración inicial de la prueba.
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MetodoDePagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MetodoDePagoEntity entity = factory.manufacturePojo(MetodoDePagoEntity.class);

            em.persist(entity);
            metodosDePago.add(entity);
        }
        System.out.println("Datos: " + metodosDePago);
    }
   @Deployment
   public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)

              
        .addPackage(MetodoDePagoEntity.class.getPackage())

        .addPackage(MetodoDePagoPersistence.class.getPackage())

        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")

        .addAsManifestResource("META-INF/beans.xml", "beans.xml");

   }
    
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity metodoDePago = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = ap.create(metodoDePago);
        Assert.assertNotNull(result);
        
    }
    
    @Test
    public void createMetodoDePagoTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity metodoDePago = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = ap.create(metodoDePago);
        
        Assert.assertNotNull(result);
        
  
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class, result.getId());
        Assert.assertEquals(metodoDePago.getId(), entity.getId());
        
    }   

 
    
    @Test
    public void getMetodoDePagosTest() {
        List<MetodoDePagoEntity> lista = ap.findAll();
        Assert.assertEquals(metodosDePago.size(), lista.size());
        for (MetodoDePagoEntity ent : lista) {
            boolean found = false;
            for (MetodoDePagoEntity entity : metodosDePago) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getMetodoDePagoTest() {
        MetodoDePagoEntity entity = metodosDePago.get(0);
        MetodoDePagoEntity newEntity = ap.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    @Test
    public void updateMetodoDePagoTest() {
        MetodoDePagoEntity entity = metodosDePago.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
    
        newEntity.setId(entity.getId());

        ap.update(newEntity);

        MetodoDePagoEntity metodoDePagoResp = em.find(MetodoDePagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), metodoDePagoResp.getId());
    }
    
    @Test
    public void deleteMetodoDePagoTest() {
        MetodoDePagoEntity entity = metodosDePago.get(0);
        ap.delete(entity.getId());
        MetodoDePagoEntity deleted = em.find(MetodoDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}

