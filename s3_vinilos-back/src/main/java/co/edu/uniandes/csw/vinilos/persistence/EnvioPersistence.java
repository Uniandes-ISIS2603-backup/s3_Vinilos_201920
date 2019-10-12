/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
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
 * Crud
 */
@Stateless
public class EnvioPersistence {
    
    /**
     * Relacion ocn EntityManager
     */
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(EnvioPersistence.class.getName());

    /**
     * creacion del envio
     * @param envio
     * @return crea el envio
     */
    public EnvioEntity create(EnvioEntity envio)
    {
        LOGGER.log(Level.INFO, "Creando un envio nuevo");
        em.persist(envio);
        LOGGER.log(Level.INFO, "envio creado");
        return envio;
    }

     

    /**
     * cambia el envio
     * @param envioEntity
     * @return el envio actualizado
     */
     public EnvioEntity update(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Actualizando envio con id = {0}", envioEntity.getId());
        return em.merge(envioEntity);
    }
     
     /**
      * borra el envio por la id
      * @param envioId 
      */
     public void delete(Long envioId) {
        LOGGER.log(Level.INFO, "Borrando envio con id = {0}", envioId);
        EnvioEntity envioEntity = em.find(EnvioEntity.class, envioId);
        em.remove(envioEntity);
       // LOGGER.log(Level.INFO, "Saliendo de borrar El genero con id = {0}", generoId);
    }
     /**
      * encuentra un envio por la id del parametro
      * @param envioId
      * @return envio que encontro
      */
      public EnvioEntity find(Long envioId) {
        LOGGER.log(Level.INFO, "Consultando envio con id = {0}", envioId);
        return em.find(EnvioEntity.class, envioId);
      }
      
      /**
       * busca todos los envios
       * @return todos los envios
       */
       public List<EnvioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los envios");
     
        TypedQuery query = em.createQuery("select u from EnvioEntity u", EnvioEntity.class);
        
        return query.getResultList();
    }
    
}
