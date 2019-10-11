/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;
import co.edu.uniandes.csw.vinilos.ejb.EnvioLogic;
import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.EnvioPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author Juan gonzalez
 */
@RunWith(Arquillian.class)
public class EnvioLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EnvioLogic envioLogic;
    
    @PersistenceContext
    private EntityManager em;
    
 
  
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EnvioEntity.class.getPackage())
                .addPackage(EnvioLogic.class.getPackage())
                .addPackage(EnvioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
               
    }
    
    
    @Test
    public void createEnvioTest() throws BusinessLogicException {
      EnvioEntity newEntity = factory.manufacturePojo(EnvioEntity.class);
        EnvioEntity result = envioLogic.createEnvio(newEntity);
        Assert.assertNotNull(result);
       
    }
    
    
}
