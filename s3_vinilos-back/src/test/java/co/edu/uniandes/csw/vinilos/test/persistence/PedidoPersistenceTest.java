/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.persistence.PedidoPersistence;
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
 * @author Stephania Otalora
 */
@RunWith(Arquillian.class)
public class PedidoPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(PedidoEntity.class.getPackage()).addPackage(PedidoPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    PedidoPersistence pp;
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PedidoEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from PedidoEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PedidoEntity entity = factory.manufacturePojo(PedidoEntity.class);
            /*UsuarioEntity usuEntity = factory.manufacturePojo(UsuarioEntity.class);

            /*entity.setUsuario(usuEntity);
            usuEntity.addPedido(entity)*/
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PedidoEntity pPedido = factory.manufacturePojo(PedidoEntity.class);
        PedidoEntity result = pp.create(pPedido);
        Assert.assertNotNull(result);
        
        //Verssion mejorada
        PedidoEntity pedido = em.find(PedidoEntity.class, result.getId());
        Assert.assertEquals(pedido.getFechaGeneracion(), pPedido.getFechaGeneracion());
    }
    
    @Test
    public void getPedidosTest() {
        List<PedidoEntity> list = pp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PedidoEntity ent : list) {
            boolean found = false;
            for (PedidoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPedidoTest() {
        PedidoEntity entity = data.get(0);
        PedidoEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFechaGeneracion(), newEntity.getFechaGeneracion());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
    }
    
    @Test
    public void updatePedidoTest() {
        PedidoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        PedidoEntity resp = em.find(PedidoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFechaGeneracion(), resp.getFechaGeneracion());
        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
        
    }
    
}
