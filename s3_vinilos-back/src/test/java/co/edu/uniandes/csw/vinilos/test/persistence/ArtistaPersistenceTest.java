/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.persistence.ArtistaPersistence;
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
public class ArtistaPersistenceTest {
    
   @PersistenceContext
   private EntityManager em;
   
   @Inject
   ArtistaPersistence ap;
    
   private List<ArtistaEntity> artistas = new ArrayList<ArtistaEntity>();
   
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
        em.createQuery("delete from ArtistaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ArtistaEntity entity = factory.manufacturePojo(ArtistaEntity.class);

            em.persist(entity);
            artistas.add(entity);
        }
        System.out.println("Datos: " + artistas);
    }
   @Deployment
   public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)

              
        .addPackage(ArtistaEntity.class.getPackage())

        .addPackage(ArtistaPersistence.class.getPackage())

        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")

        .addAsManifestResource("META-INF/beans.xml", "beans.xml");

   }
    
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ArtistaEntity artista = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = ap.create(artista);
        Assert.assertNotNull(result);
        
    }
    
    @Test
    public void createArtistaTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ArtistaEntity artista = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = ap.create(artista);
        
        Assert.assertNotNull(result);
        
  
        ArtistaEntity entity = em.find(ArtistaEntity.class, result.getId());
        Assert.assertEquals(artista.getName(), entity.getName());
        
    }   

 
    
    @Test
    public void getArtistasTest() {
        List<ArtistaEntity> lista = ap.findAll();
        Assert.assertEquals(artistas.size(), lista.size());
        for (ArtistaEntity ent : lista) {
            boolean found = false;
            for (ArtistaEntity entity : artistas) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getArtistaTest() {
        ArtistaEntity entity = artistas.get(0);
        ArtistaEntity newEntity = ap.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void updateArtistaTest() {
        ArtistaEntity entity = artistas.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    
        newEntity.setId(entity.getId());

        ap.update(newEntity);

        ArtistaEntity artistaResp = em.find(ArtistaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), artistaResp.getName());
    }
    
    @Test
    public void deleteArtistaTest() {
        ArtistaEntity entity = artistas.get(0);
        ap.delete(entity.getId());
        ArtistaEntity deleted = em.find(ArtistaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}