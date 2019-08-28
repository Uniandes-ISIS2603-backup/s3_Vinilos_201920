/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
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
public class ArtistaPersistence {
    
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    public ArtistaEntity create(ArtistaEntity artista) {
        em.persist(artista);
        return artista;//To change body of generated methods, choose Tools | Templates.
    }
    
      public ArtistaEntity find(Long nombreArtista){
        return em.find(ArtistaEntity.class, nombreArtista);
    }
    
    public List<ArtistaEntity> findAll(){
        TypedQuery<ArtistaEntity> query = em.createQuery("select u from ArtistaEntity u", ArtistaEntity.class);
        return query.getResultList();
    }
    
    public ArtistaEntity update(ArtistaEntity artistaActu){      
        return em.merge(artistaActu);
    }
  
    public void delete(Long nombreArtista){
        em.remove(em.find(ArtistaEntity.class, nombreArtista));
    }
    
}
