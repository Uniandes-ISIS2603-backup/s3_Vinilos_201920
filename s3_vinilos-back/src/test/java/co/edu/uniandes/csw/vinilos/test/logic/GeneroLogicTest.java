/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.GeneroLogic;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.GeneroPersistence;
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
 * @author juan gonzalez
 */
@RunWith(Arquillian.class)
public class GeneroLogicTest {
    /**
     * podamFactory
     */
     private PodamFactory factory = new PodamFactoryImpl();
    
     /**
      * Relacion con logica de genero
      */
    @Inject
    private GeneroLogic generoLogic;
    
    /**
     * relacion con entityManager
     */
    @PersistenceContext
    private EntityManager em;
    
 
  
    /**
     * crea el deployment
     * @return deployment
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(GeneroLogic.class.getPackage())
                .addPackage(GeneroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
               
    }
    
    
    /**
     * test de la creacion de genero
     * @throws BusinessLogicException  si el genero no existe o no es que se pidio buscar
     */
    @Test
    public void createGeneroTest() throws BusinessLogicException {
      GeneroEntity newEntity = factory.manufacturePojo(GeneroEntity.class);
        GeneroEntity result = generoLogic.createGenero(newEntity);
        Assert.assertNotNull(result);
        GeneroEntity entity = em.find(GeneroEntity.class, result.getId());
          
        Assert.assertEquals(entity.getNombre(), result.getNombre());       
        
    }
    
    /**
     * test de genero cuando este sea null
     * @throws BusinessLogicException si el genero es encontrado
     */
    @Test (expected = BusinessLogicException.class)
    public void crearGeneroNombreNull() throws BusinessLogicException {
        
       GeneroEntity newEntity = factory.manufacturePojo(GeneroEntity.class);
        newEntity.setNombre(null);
       GeneroEntity result = generoLogic.createGenero(newEntity);
    }
}
