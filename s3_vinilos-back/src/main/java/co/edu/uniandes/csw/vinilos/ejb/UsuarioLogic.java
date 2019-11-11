/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Manuel Sosa
 */
@Stateless
public class UsuarioLogic {
    
    /**
    *Usuario persistence
    */
    @Inject
    private UsuarioPersistence persistence;
    
    /**
     * Crea un usuario entity definiendo las reglas de negocio
     * @param usuario Usuario a verificar
     * @return usuario que cumple las reglas
     * @throws BusinessLogicException 
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException{
        
        if(persistence.findbyEMail(usuario.getCorreo()) != null){
            throw new BusinessLogicException("Ya existe un usuario con ese correo");
        }
        if(usuario.getNombre() == null){
            throw new BusinessLogicException("El nombre no puede ser nulo");
        }
        if(usuario.getCelular() == null){
            throw new BusinessLogicException("El celular no puede ser nulo");
        }
        if(usuario.getCorreo() == null){
            throw new BusinessLogicException("El correo no puede ser nulo");
        }
        if(usuario.getDireccion() == null){
            throw new BusinessLogicException("La direccion no puede ser nulo");
        }
        if(usuario.getPais() == null){
            throw new BusinessLogicException("El pais no puede ser nulo");
        }
        if(usuario.getFechaNacim() == null){
            throw new BusinessLogicException("La fecha no puede ser nulo");
        }
        if(usuario.getContrasena() == null){
            throw new BusinessLogicException("La contraseña no puede ser nulo");
        }
        usuario = persistence.create(usuario);
        return usuario;
    }
    
    /**
     * Retorna todos los usuarios
     * @return lista de usuarios
     */
    public List<UsuarioEntity> getUsuarios(){
        List<UsuarioEntity> usuarios = persistence.findAll();
        return usuarios;
    }
    
    /**
     * Retorna el usuario según su id
     * @param usuarioId id del usaurio
     * @return usuario deseado
     */
    public UsuarioEntity getUsuario(Long usuarioId){
        UsuarioEntity usuario = persistence.find(usuarioId);
        return usuario;
    }
    
    /**
     * Actualiza un usuario según su id
     * @param usuarioId id del usuario a actualizar
     * @param usuarioEntity usuario nuevo
     * @return el usuario actualizado
     * @throws BusinessLogicException 
     */
    public UsuarioEntity updateUsuario(Long usuarioId, UsuarioEntity usuarioEntity) throws BusinessLogicException {   
        
        UsuarioEntity newEntity = persistence.update(usuarioEntity);
        return newEntity;
    }
    
    /**
     * Elimina un usuario según su id
     * @param usuarioId id del usuario
     * @throws BusinessLogicException 
     */
    public void deleteUsuario(Long usuarioId) throws BusinessLogicException {
        /*
        if (persistence.find(usuarioId).getVinilosVenta().get(0) != null) {
            throw new BusinessLogicException("No se puede borrar el usuario con id = " + usuarioId + " porque tiene vinilos en venta");
        }
        if (persistence.find(usuarioId).getPedidos().get(0) != null) {
            throw new BusinessLogicException("No se puede borrar el usuario con id = " + usuarioId + " porque tiene pedidos en proceso");
        }
*/
        persistence.delete(usuarioId);
        
     } 
}
