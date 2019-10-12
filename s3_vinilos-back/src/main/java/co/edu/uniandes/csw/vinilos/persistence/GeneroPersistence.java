/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan gonzalez
 * el crud
 */
@Stateless
public class GeneroPersistence {

    /**
     * La relacion con EntityManager
     */
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(GeneroPersistence.class.getName());

    /**
     * crea el genero
     * @param genero
     * @return el nuevo genero
     */
    public GeneroEntity create(GeneroEntity genero)
    {
        LOGGER.log(Level.INFO, "Creando un genero nuevo");
        em.persist(genero);
        LOGGER.log(Level.INFO, "genero creado");
        return genero;
    }

     

    /**
     * Cambia genero ya creado
     * @param generoEntity
     * @return retorna el genero nuevo
     */
     public GeneroEntity update(GeneroEntity generoEntity) {
        LOGGER.log(Level.INFO, "Actualizando genero con id = {0}", generoEntity.getId());
        return em.merge(generoEntity);
    }
     
     /**
      * borra el genero por id
      * @param generoId 
      */
     public void delete(Long generoId) {
        LOGGER.log(Level.INFO, "Borrando genero con id = {0}", generoId);
        GeneroEntity reviewEntity = em.find(GeneroEntity.class, generoId);
        em.remove(reviewEntity);
       // LOGGER.log(Level.INFO, "Saliendo de borrar El genero con id = {0}", generoId);
    }
     
     /**
      * busca un genero con la id
      * @param prizeId
      * @return el genero por la id
      */
      public GeneroEntity find(Long prizeId) {
        LOGGER.log(Level.INFO, "Consultando genero con id = {0}", prizeId);
        return em.find(GeneroEntity.class, prizeId);
      }
      
      /**
       * busca todos los generos
       * @return retorna todos los generos
       */
       public List<GeneroEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los generos");
        // Se crea un query para buscar todas las authores en la base de datos.
        TypedQuery query = em.createQuery("select u from GeneroEntity u", GeneroEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de authores.
        return query.getResultList();
    }
}
    