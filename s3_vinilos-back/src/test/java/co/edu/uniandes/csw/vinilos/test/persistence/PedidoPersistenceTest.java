/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.test.persistence;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.persistence.PedidoPersistence;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<PedidoEntity> data = new ArrayList<>();
    
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
