/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.persistence;

import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manuel Sosa
 */
@Stateless
public class UsuarioPersistence {
    
    @PersistenceContext(unitName = "vinilosPU")
    protected EntityManager em;
    
    /*
    Crea un usuario en la base de datos
    */
    public UsuarioEntity create(UsuarioEntity usuario){
        //throw new java.lang.UnsupportedOperationException("Not supperted yet.");
        em.persist(usuario);
        
        return usuario;
    }
    
    /*
    Busca un usuario en la base de datos
    */
    public UsuarioEntity find(Long usuarioId){
        return em.find(UsuarioEntity.class, usuarioId);
    }
    
    /*
    Devuelve todos los usuarios del la base de datos
    */
    public List<UsuarioEntity> findAll(){
        TypedQuery<UsuarioEntity> query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
    
    public UsuarioEntity findbyEMail(String correo){
        TypedQuery<UsuarioEntity> query = em.createQuery("select e from UsuarioEntity e where e.correo = :correo", UsuarioEntity.class);
        query = query.setParameter("correo", correo);
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result;
        if(sameName == null){
            result = null;
        } else if(sameName.isEmpty()){
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
    
    /*
    Actualiza un usuario en la base de datos
    */
    public UsuarioEntity update(UsuarioEntity newUsuario){      
        return em.merge(newUsuario);
    }
    
    /*
    Elimina el usuario de la base datos
    */
    public void delete(Long usuarioId){
        em.remove(em.find(UsuarioEntity.class, usuarioId));
    }
    
}
