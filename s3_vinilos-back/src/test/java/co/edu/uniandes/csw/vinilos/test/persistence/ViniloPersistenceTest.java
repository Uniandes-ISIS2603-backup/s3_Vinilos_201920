/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.persistence.ViniloPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @Vinilo Estudiante
 */
@RunWith(Arquillian.class)
public class ViniloPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ViniloEntity.class.getPackage()).addPackage(ViniloPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    ViniloPersistence vp;
    
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    private List<ViniloEntity> data = new ArrayList<>();

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
        em.createQuery("delete from ViniloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ViniloEntity entity = factory.manufacturePojo(ViniloEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        System.out.println("Datos: " + data);
    }

    
    @Test
    public void createViniloTest() {
        //Falla crear vinilo
        PodamFactory factory = new PodamFactoryImpl();
        ViniloEntity vinilo = factory.manufacturePojo(ViniloEntity.class);
        
        ViniloEntity result = vp.create(vinilo);
        Assert.assertNotNull(result);
        
        ViniloEntity entity = em.find(ViniloEntity.class, result.getId());
        Assert.assertEquals(vinilo.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para actualizar un Vinilo.
     */
    @Test
    public void updateViniloTest() {
        ViniloEntity entity = data.get(0);
        
        PodamFactory factory = new PodamFactoryImpl();
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);

        newEntity.setId(entity.getId());
        
        vp.update(newEntity);
        
        ViniloEntity resp = em.find(ViniloEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar un Vinilo.
     */
    @Test
    public void deleteViniloTest() {
        ViniloEntity entity = data.get(0);
        vp.delete(entity.getId());
        ViniloEntity deleted = em.find(ViniloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para consultar la lista de Vinilos.
     */
    @Test
    public void getVinilosTest() {
        List<ViniloEntity> list = vp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViniloEntity ent : list) {
            boolean found = false;
            for (ViniloEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Vinilo.
     */
    @Test
    public void getViniloTest() {
        ViniloEntity entity = data.get(0);
        ViniloEntity newEntity = vp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    
}
