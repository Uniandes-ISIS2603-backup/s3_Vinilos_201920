/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.MetodoDePagoLogic;
import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
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
public class MetodoDePagoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MetodoDePagoLogic metodoDePagoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
 
    @Inject
    private MetodoDePagoLogic editorialLogic;
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MetodoDePagoEntity.class.getPackage())
                .addPackage(MetodoDePagoLogic.class.getPackage())
                .addPackage(MetodoDePagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
               
    }
    
    
    @Test
    public void createMetodoDePagoTest() throws BusinessLogicException {
       MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = metodoDePagoLogic.createMetodoDePago(newEntity);
        Assert.assertNotNull(result);
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class, result.getId());
          
        Assert.assertEquals(entity.getId(), result.getId());       
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void crearMetodoDePagoCuentaPSENull() throws BusinessLogicException {
        
        MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
        newEntity.setCuentaPSE(null);
        MetodoDePagoEntity result = metodoDePagoLogic.createMetodoDePago(newEntity);
        
    }
}