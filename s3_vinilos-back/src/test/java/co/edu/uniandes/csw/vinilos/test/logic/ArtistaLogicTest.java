/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
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
public class ArtistaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ArtistaLogic artistaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
 
    @Inject
    private ArtistaLogic editorialLogic;
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArtistaEntity.class.getPackage())
                .addPackage(ArtistaLogic.class.getPackage())
                .addPackage(ArtistaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
               
    }
    
    
    @Test
    public void createArtistaTest() throws BusinessLogicException {
       ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = artistaLogic.createArtista(newEntity);
        Assert.assertNotNull(result);
        ArtistaEntity entity = em.find(ArtistaEntity.class, result.getId());
          
        Assert.assertEquals(entity.getName(), result.getName());       
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void crearArtistaNombreNull() throws BusinessLogicException {
        
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
        newEntity.setName(null);
        ArtistaEntity result = artistaLogic.createArtista(newEntity);
        
    }
}
