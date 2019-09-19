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
    
    
    public PedidoEntity create (PedidoEntity pPedido)
    {
        em.persist(pPedido);
        return pPedido;
    }
    
    public List<PedidoEntity> findAll() 
    {
         TypedQuery query = em.createQuery("select u from PedidoEntity u", PedidoEntity.class);
         return query.getResultList();         
    }
    
    public PedidoEntity find(Long pedidoId) 
    {
        return em.find(PedidoEntity.class, pedidoId);
    }
    
    public PedidoEntity update(PedidoEntity pedidoEntity)
    {
        return em.merge(pedidoEntity);
    }

    public void delete(Long pedidoId) 
    {
        PedidoEntity pedidoEntity = em.find(PedidoEntity.class, pedidoId);
        em.remove(pedidoEntity);
    }
}
