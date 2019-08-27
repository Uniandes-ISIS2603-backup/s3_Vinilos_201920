package co.edu.uniandes.csw.vinilos.persistence;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @Vinilo Jhoan Sebastian Diaz Romero
 */
@Stateless
public class ViniloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ViniloPersistence.class.getName());
    
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    public ViniloEntity create(ViniloEntity pVinilo){
        LOGGER.log(Level.INFO, "Creando un vinilo nuevo");
        em.persist(pVinilo);
        return pVinilo;
    }
    
    /**
     * Devuelve todas los vinilos de la base de datos.
     *
     * @return una lista con todos los vinilos que encuentre en la base de
     * datos, "select u from ViniloEntity u" es como un "select * from
     * ViniloEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ViniloEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los vinilos");
        // Se crea un query para buscar todas las Viniloes en la base de datos.
        TypedQuery query = em.createQuery("select u from ViniloEntity u", ViniloEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de vinilos.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna Vinilo con el id que se envía de argumento
     *
     * @param ViniloId: id correspondiente a el Vinilo buscado.
     * @return un Vinilo.
     */
    public ViniloEntity find(Long viniloId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", viniloId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ViniloEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ViniloEntity.class, viniloId);
    }
    
    /**
     * Actualiza un Vinilo.
     *
     * @param viniloEntity: el Vinilo que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un Vinilo con los cambios aplicados.
     */
    public ViniloEntity update(ViniloEntity viniloEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", viniloEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Vinilo con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(viniloEntity);
    }
    
    /**
     * Borra un vinilo de la base de datos recibiendo como argumento el id de
     * el vinilo
     *
     * @param viniloId: id correspondiente a el Vinilo a borrar.
     */
    public void delete(Long viniloId) {
        LOGGER.log(Level.INFO, "Borrando el author con id={0}", viniloId);
        // Se hace uso de mismo método que esta explicado en public ViniloEntity find(Long id) para obtener la Vinilo a borrar.
        ViniloEntity viniloEntity = em.find(ViniloEntity.class, viniloId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from ViniloEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(viniloEntity);
    }
}
