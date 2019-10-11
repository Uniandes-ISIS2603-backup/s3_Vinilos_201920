/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stephania Otalora
 */
@Stateless
public class PedidoPersistence {
    
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param pPedido objeto pedido que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PedidoEntity create (PedidoEntity pPedido)
    {
        em.persist(pPedido);
        return pPedido;
    }
    
    /**
     * Devuelve todos los pedidos de la base de datos.
     *
     * @return una lista con todos los pedidos que encuentre en la base de datos
     */
    public List<PedidoEntity> findAll() 
    {
         TypedQuery query = em.createQuery("select u from PedidoEntity u", PedidoEntity.class);
         return query.getResultList();         
    }
    
    /**
     * Busca si hay algun pedido con el id que se envía de argumento
     *
     * @param pedidoId: id correspondiente al pedido buscado.
     * @return un pedido.
     */
    public PedidoEntity find(Long pedidoId) 
    {
        return em.find(PedidoEntity.class, pedidoId);
    }
    
     /**
     * Actualiza un pedido.
     *
     * @param pedidoEntity: el pedido que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un pedido con los cambios aplicados.
     */
    public PedidoEntity update(PedidoEntity pedidoEntity)
    {
        return em.merge(pedidoEntity);
    }

    /**
     *
     * Borra un pedido de la base de datos recibiendo como argumento el id del
     * pedido
     *
     * @param pedidoId: id correspondiente al pedido a borrar.
     */
    public void delete(Long pedidoId) 
    {
        PedidoEntity pedidoEntity = em.find(PedidoEntity.class, pedidoId);
        em.remove(pedidoEntity);
    }
}
