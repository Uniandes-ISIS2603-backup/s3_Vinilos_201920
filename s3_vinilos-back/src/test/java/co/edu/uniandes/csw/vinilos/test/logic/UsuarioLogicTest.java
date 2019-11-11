/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.vinilos.ejb.GeneroLogic;
import co.edu.uniandes.csw.vinilos.ejb.PedidoLogic;
import co.edu.uniandes.csw.vinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.vinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
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
    
    /**
     * factory
     */
    private PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * Lista de usuarios 
     */
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
    
    /**
     * Usuariologic
     */
    @Inject
    private UsuarioLogic usuarioLogic;
    
    /**
     * Entity manager
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * User transaction
     */
    @Inject
    private UserTransaction utx;
     
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(PedidoEntity.class.getPackage())
                .addPackage(ViniloEntity.class.getPackage())
                .addPackage(ArtistaEntity.class.getPackage())
                .addPackage(GeneroEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    /**
     * Configuración inicial de la prueba
     */
    @Before
    public void configTest(){
        try {
            utx.begin();
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
     * Limpia las tablas
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Inserta usuarios en las tablas
     */
    private void insertData(){
        for(int i=0; i < 1 ;i++){
                UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
                entity.setCelular(i);
                entity.setCorreo("correo" + i);
                data.add(entity);
                em.persist(entity);             
        }
    }
    
    @Test
    public void createUsuarioTest() throws BusinessLogicException{
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        usuario.setNombre("pepito");
        UsuarioEntity result = usuarioLogic.createUsuario(usuario);
        Assert.assertNotNull(result);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());    
        Assert.assertEquals(usuario.getNombre(), entity.getNombre());
        usuarioLogic.deleteUsuario(usuario.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConMismoCorreoTest() throws BusinessLogicException{
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        entity.setCorreo("correo");
        usuarioLogic.createUsuario(entity);
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo(entity.getCorreo());
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);
        usuarioLogic.deleteUsuario(entity.getId());
        usuarioLogic.deleteUsuario(newEntity.getId());
    
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearNombreNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setNombre(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearCorreoNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity); 
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearCelularNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCelular(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity); 
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearDireccionNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setDireccion(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearPaisNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setPais(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearFechaNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setFechaNacim(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void crearContrasenaNullTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setContrasena(null);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);  
        usuarioLogic.deleteUsuario(newEntity.getId());
    }
    
    /**
     * Prueba para consultar un Usuario.
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getUsuario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(resultEntity.getCelular(), entity.getCelular());
    }
    
     /**
     * Prueba para actualizar un Usuario.
     */
    @Test
    public void updateUsuarioTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        
        usuarioLogic.updateUsuario(entity.getId() , entity);

        UsuarioEntity resultEntity = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(entity.getId(), resultEntity.getId());       
        Assert.assertEquals(resultEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(resultEntity.getCelular(), entity.getCelular());
    }
    
    /**
     * Prueba para eliminar un Usuario.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteUsuarioTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        System.out.println(entity);
        /*
        List<PedidoEntity> pedido = new ArrayList<PedidoEntity>();
        entity.setPedidos(pedido);
        List<ViniloEntity> venta = new ArrayList<ViniloEntity>();
        entity.setVinilosVenta(venta);*/
        usuarioLogic.deleteUsuario(entity.getId());
    }
}
