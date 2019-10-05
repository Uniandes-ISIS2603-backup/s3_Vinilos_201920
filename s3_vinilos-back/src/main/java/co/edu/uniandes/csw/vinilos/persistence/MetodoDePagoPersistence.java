/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Diego Bogotá
 */
@Stateless
public class MetodoDePagoPersistence {
    
    @PersistenceContext(unitName = "vinilosPU")
protected EntityManager em;

public MetodoDePagoEntity create(MetodoDePagoEntity metodoDePago) {
    em.persist(metodoDePago);
    return metodoDePago;//To change body of generated methods, choose Tools | Templates.
}

  public MetodoDePagoEntity find(Long nombreMetodoDePago){
    return em.find(MetodoDePagoEntity.class, nombreMetodoDePago);
}
  
public List<MetodoDePagoEntity> findAll(){
    TypedQuery<MetodoDePagoEntity> query = em.createQuery("select u from MetodoDePagoEntity u", MetodoDePagoEntity.class);
    return query.getResultList();
}

public MetodoDePagoEntity update(MetodoDePagoEntity metodoDePagoActu){      
    return em.merge(metodoDePagoActu);
}

public void delete(Long nombreMetodoDePago){
    em.remove(em.find(MetodoDePagoEntity.class, nombreMetodoDePago));
}
}
