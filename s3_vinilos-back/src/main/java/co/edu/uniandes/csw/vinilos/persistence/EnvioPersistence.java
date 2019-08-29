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
 * @author Estudiante
 */
@Stateless
public class EnvioPersistence {
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(EnvioPersistence.class.getName());

    public EnvioEntity create(EnvioEntity envio)
    {
        LOGGER.log(Level.INFO, "Creando un envio nuevo");
        em.persist(envio);
        LOGGER.log(Level.INFO, "envio creado");
        return envio;
    }

     

     public EnvioEntity update(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Actualizando envio con id = {0}", envioEntity.getId());
        return em.merge(envioEntity);
    }
     
     
     public void delete(Long envioId) {
        LOGGER.log(Level.INFO, "Borrando envio con id = {0}", envioId);
        EnvioEntity envioEntity = em.find(EnvioEntity.class, envioId);
        em.remove(envioEntity);
       // LOGGER.log(Level.INFO, "Saliendo de borrar El genero con id = {0}", generoId);
    }
      public EnvioEntity find(Long envioId) {
        LOGGER.log(Level.INFO, "Consultando envio con id = {0}", envioId);
        return em.find(EnvioEntity.class, envioId);
      }
       public List<EnvioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los envios");
        // Se crea un query para buscar todas las authores en la base de datos.
        TypedQuery query = em.createQuery("select u from EnvioEntity u", EnvioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de authores.
        return query.getResultList();
    }
    
}
