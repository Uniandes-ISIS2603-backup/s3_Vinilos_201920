/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.GeneroDTO;
import co.edu.uniandes.csw.vinilos.dtos.GeneroDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.GeneroLogic;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
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
 * @author Juan Gonzalez
 */
@Path("genero")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class GeneroResource {
    
      private static final Logger LOGGER = Logger.getLogger(GeneroResource.class.getName());
   
        @Inject
    private GeneroLogic logica;
    
     /*Crea el genero*/
    @POST
    public GeneroDTO crearVinilo(GeneroDTO genero) throws BusinessLogicException{
    GeneroEntity generoEntity = genero.toEntity();
    generoEntity = logica.createGenero(generoEntity);
    return new GeneroDTO(generoEntity);
   }
    
    /*Retorna al genero*/
   @GET
   @Path("(generosId: \\d+)")
   public GeneroDetailDTO getGenero(@PathParam("generosId") Long generosId){
   
       GeneroEntity entidad = logica.getGenero(generosId);
       if(entidad == null){
           throw new WebApplicationException("El recurso /genero/" + generosId + "no existe.", 404);
       }
       
        return new GeneroDetailDTO(entidad);
   }
    

/*Actualiza el genero*/
@PUT
@Path("{generosId: \\d+}")
public GeneroDetailDTO updateGeneros(@PathParam("generosId") Long generoId, GeneroDetailDTO genero) throws WebApplicationException, BusinessLogicException {
    if(logica.getGenero(generoId) == null){
        throw new WebApplicationException("El recurso /genero/" + generoId + " no existe.", 404);
    }
    
    GeneroDetailDTO detailDTO = new GeneroDetailDTO(logica.updateGenero(generoId, genero.toEntity()));
    return detailDTO;
}
/*Elimina el genero*/
@DELETE
@Path("{generosId: \\d+}")
public void deleteGenero(@PathParam("generosId") Long generoId) throws BusinessLogicException {
    
    if (logica.getGenero(generoId) == null) {
        throw new WebApplicationException("El recurso /genero/" + generoId + " no existe.", 404);
    }
    logica.deleteGenero(generoId);
}
 @GET
public List<GeneroDetailDTO> generoListEntityListaDTO(List<GeneroEntity> entityList){
    List<GeneroDetailDTO> list = new ArrayList();
    for(GeneroEntity entity: entityList){
        list.add(new GeneroDetailDTO(entity));
    }
    return list;

}
}
