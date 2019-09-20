/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.logic;

import co.edu.uniandes.csw.vinilos.ejb.PedidoLogic;
import co.edu.uniandes.csw.vinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.PedidoPersistence;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
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
 * Pruebas de logica de Pedido
 *
 * @author Stephania Otalora
 */
@RunWith(Arquillian.class)
public class PedidoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PedidoLogic pedidoLogic;
    
    @Inject
    private ViniloLogic vinLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PedidoEntity> data = new ArrayList<PedidoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PedidoEntity.class.getPackage())
                .addPackage(EnvioEntity.class.getPackage())
                .addPackage(ViniloEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(PedidoLogic.class.getPackage())
                .addPackage(PedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PedidoEntity").executeUpdate();
    }
    
     private void insertData() {
        for (int i = 0; i < 3; i++) {
            PedidoEntity entity = factory.manufacturePojo(PedidoEntity.class);
            data.add(entity);
            em.persist(entity);
        }
    }

    
    /**
     * Prueba para crear un Pedido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createPedidoCompraTest() throws BusinessLogicException {
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.COMPRA);
        ViniloEntity newVinEntity = factory.manufacturePojo(ViniloEntity.class);
        newVinEntity = vinLogic.crearVinilo(newVinEntity);
        newEntity.setViniloCompra(newVinEntity);
        newEntity.setVinilosIntercambio(null);
        PedidoEntity result = pedidoLogic.createPedido(newEntity);
        Assert.assertNotNull(result);
        PedidoEntity entity = em.find(PedidoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAceptado(), entity.getAceptado());
        Assert.assertEquals(newEntity.getFechaGeneracion(), entity.getFechaGeneracion());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
    @Test
    public void createPedidoIntercambioTest() throws BusinessLogicException {
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.INTERCAMBIO);
        ViniloEntity newVinEntity = factory.manufacturePojo(ViniloEntity.class);
        newVinEntity = vinLogic.crearVinilo(newVinEntity);
        newEntity.setViniloCompra(null);
        newEntity.addViniloIntercambio(newVinEntity);
        PedidoEntity result = pedidoLogic.createPedido(newEntity);
        Assert.assertNotNull(result);
        PedidoEntity entity = em.find(PedidoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAceptado(), entity.getAceptado());
        Assert.assertEquals(newEntity.getFechaGeneracion(), entity.getFechaGeneracion());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para crear un Pedido de tipo compra con vinilos de intercambio registrados.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPedidoIntercambioViniloCompraTest() throws BusinessLogicException, ParseException, ParseException {
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        ViniloEntity newVinEntity = factory.manufacturePojo(ViniloEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.INTERCAMBIO);
        newVinEntity = vinLogic.crearVinilo(newVinEntity);
        newEntity.setViniloCompra(newVinEntity);
        pedidoLogic.createPedido(newEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void createPedidoCompraVinilosIntercambioTest() throws BusinessLogicException{
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        ViniloEntity newVinEntity = factory.manufacturePojo(ViniloEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.COMPRA);
        newVinEntity = vinLogic.crearVinilo(newVinEntity);
        newEntity.addViniloIntercambio(newVinEntity);
        pedidoLogic.createPedido(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPedidoCompraInvalidoTest() throws BusinessLogicException, ParseException {
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.COMPRA);
        newEntity.setViniloCompra(null);
        pedidoLogic.createPedido(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPedidoIntercambioInvalidoTest() throws BusinessLogicException, ParseException {
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        newEntity.setTipo(PedidoEntity.TipoPedido.INTERCAMBIO);
        newEntity.setVinilosIntercambio(null);
        pedidoLogic.createPedido(newEntity);
    }

    
    /**
     * Prueba para consultar la lista de Pedidos.
     */
    @Test
    public void getPedidosTest() {
        List<PedidoEntity> list = pedidoLogic.getPedidos();
        Assert.assertEquals(data.size(), list.size());
        for (PedidoEntity entity : list) {
            boolean found = false;
            for (PedidoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Pedido.
     */
    @Test
    public void getPedidoTest() {
        PedidoEntity entity = data.get(0);
        PedidoEntity resultEntity = pedidoLogic.getPedido(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getAceptado(), entity.getAceptado());
        Assert.assertEquals(resultEntity.getFechaGeneracion(), entity.getFechaGeneracion());
        Assert.assertEquals(resultEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para actualizar un Pedido.
     */
    @Test
    public void updatePedidoTest() throws BusinessLogicException {
        PedidoEntity entity = data.get(0);
        
        entity.setAceptado(false);
        pedidoLogic.updatePedido(entity.getId(), entity);

        PedidoEntity resultEntity = em.find(PedidoEntity.class, entity.getId());

        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getAceptado(), entity.getAceptado());
        Assert.assertEquals(resultEntity.getFechaGeneracion(), entity.getFechaGeneracion());
        Assert.assertEquals(resultEntity.getTipo(), entity.getTipo());
    }
    
    /**
     * Prueba para eliminar un Pedido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deletePedidoTest() throws BusinessLogicException {
        PedidoEntity entity = data.get(2);
        System.out.println(entity);
        entity.setAceptado(true);
        pedidoLogic.deletePedido(entity.getId());
    }
}
