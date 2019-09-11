/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Manuel Sosa
 */
@Stateless
public class UsuarioLogic {
    
    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException{
        
        if(persistence.findbyEMail(usuario.getCorreo()) != null){
            throw new BusinessLogicException("Ya existe un usuario con ese correo \""+ usuario.getCorreo() + "\"");
        }
        if(usuario.getName() == null){
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
        if(usuario.getfechaNacim() == null){
            throw new BusinessLogicException("La fecha no puede ser nulo");
        }
        usuario = persistence.create(usuario);
        return usuario;
    }
}
