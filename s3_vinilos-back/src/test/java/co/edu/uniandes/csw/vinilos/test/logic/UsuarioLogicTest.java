/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.UsuarioPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
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
 * @author Manuel Sosa
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<UsuarioEntity> data;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UsuarioEntity.class)
                .addClass(UsuarioLogic.class)
                .addClass(UsuarioPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private void insertData(){
        for(int i=0; i < 3 ;i++){
                UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
                em.persist(usuario);
                data.add(usuario);
        }
    }
    
    @Before
    public void configTest(){
        try{
            
        }catch(Exception e){
            
        }
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioTest() throws BusinessLogicException{
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioLogic.createUsuario(usuario);
        Assert.assertNotNull(result);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());    
        Assert.assertEquals(usuario.getName(), entity.getName());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConMismoCorreoTest() throws BusinessLogicException{
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        entity.setCorreo("correo");
        usuarioLogic.createUsuario(entity);
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo(entity.getCorreo());
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);
    
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearNombreNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setName(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearCorreoNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearCelularNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCelular(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearDireccionNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setDireccion(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearPaisNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setPais(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearFechaNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setFechaNacim(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
    }
}
