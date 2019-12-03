/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.UsuarioDTO;
import co.edu.uniandes.csw.vinilos.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Manuel Sosa
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    /**
     * Logica del usuario
     */
    @Inject
    private UsuarioLogic logica;
    
    /**
     * crea un usuariodto
     * @param usuario usuario
     * @return usuarioDTO
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException{
        UsuarioEntity usuarioEntity = usuario.toEntity();
        usuarioEntity = logica.createUsuario(usuarioEntity);
        return new UsuarioDTO(usuarioEntity);
    }
    
    /**
     * Retorna un usuario por su id
     * @param usuariosId id del usuario
     * @return usuarioDetailDTO
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosId") Long usuariosId)throws WebApplicationException{
       UsuarioEntity entidad = logica.getUsuario(usuariosId);
       if(entidad == null){
           throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "no existe.", 404);
       }
       return new UsuarioDetailDTO(entidad);
    }
    
    /**
     * Retorna un usuario por su id
     * @param usuariosCorreo correo del usuario
     * @return usuarioDetailDTO
     
    @GET
    @Path("{usuariosCorreo: \\d+}")
    public UsuarioDetailDTO getUsuarioPorCorreo(@PathParam("usuariosCorreo") String usuariosCorreo)throws WebApplicationException{
       UsuarioEntity entidad = logica.getUsuarioPorCorreo(usuariosCorreo);
       if(entidad == null){
           throw new WebApplicationException("El recurso /usuarios/" + usuariosCorreo + "no existe.", 404);
       }
       return new UsuarioDetailDTO(entidad);
    }*/
    
    /**
     * Da los usuarios
     * @return List<UsuarioDetailDTO>
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios(){
         return usuarioListEntity2DTO(logica.getUsuarios());
    }
    
    /**
     * Update de usurio
     * @param usuariosId id del usuario
     * @param usuario usuarioDeatilDTO
     * @return UsuarioDetailDTO
     * @throws WebApplicationException
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO updateUsuarios(@PathParam("usuariosId") Long usuariosId, UsuarioDetailDTO usuario) throws WebApplicationException, BusinessLogicException {
        if(logica.getUsuario(usuariosId) == null){
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(logica.updateUsuario(usuariosId, usuario.toEntity()));
        return detailDTO;
    }
    
    /**
     * Elimina un usuario 
     * @param usuariosId id del usuario
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {
    if (logica.getUsuario(usuariosId) == null) {
        throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
    }
    logica.deleteUsuario(usuariosId);
}
    
    /**
     * Crea una lista de UsuarioDetailDTO segun una lista de UsuarioEntity
     * @param entityList lista de UsuarioEntity
     * @return List<UsuarioDetailDTO>
     */
    public List<UsuarioDetailDTO> usuarioListEntity2DTO(List<UsuarioEntity> entityList){
    List<UsuarioDetailDTO> list = new ArrayList<>();
    for(UsuarioEntity entity: entityList){
        list.add(new UsuarioDetailDTO(entity));
    }
    return list;
    }
}
