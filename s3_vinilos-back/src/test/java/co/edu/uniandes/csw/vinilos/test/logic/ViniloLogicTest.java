/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.ViniloPersistence;
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
 * @author Jhoan Sebastian Díaz Romero
 */
@RunWith(Arquillian.class)
public class ViniloLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ViniloLogic viniloLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(ViniloEntity.class.getPackage()).addPackage(ViniloLogic.class.getPackage()).addPackage(ViniloPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public void crearVinilo() throws BusinessLogicException {
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);
        ViniloEntity result = viniloLogic.crearVinilo(newEntity);
        Assert.assertNotNull(result);
        
        ViniloEntity entity = em.find(ViniloEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void crearViniloNombreNull() throws BusinessLogicException {
        
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);
        newEntity.setNombre(null);
        ViniloEntity result = viniloLogic.crearVinilo(newEntity);
        
    }
    
}
