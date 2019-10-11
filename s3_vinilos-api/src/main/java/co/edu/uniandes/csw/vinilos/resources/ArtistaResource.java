
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.ArtistaDTO;
import co.edu.uniandes.csw.vinilos.dtos.ArtistaDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.ArtistaLogic;
import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Diego Bogotá
 */
@Path("artistas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArtistaResource {
   
    @Inject
    private ArtistaLogic logica;
    
    private static final Logger LOGGER = Logger.getLogger(ArtistaResource.class.getName());
    
    /*Crea al artista*/
    @POST
    public ArtistaDTO crearVinilo(ArtistaDTO artista) throws BusinessLogicException{
    ArtistaEntity artistaEntity = artista.toEntity();
    artistaEntity = logica.createArtista(artistaEntity);
    return new ArtistaDTO(artistaEntity);
   }
    
    /*Retorna al artista*/
   @GET
   @Path("(artistasId: \\d+)")
   public ArtistaDetailDTO getArtista(@PathParam("artistasId") Long artistasId){
   
       ArtistaEntity entidad = logica.getArtista(artistasId);
       if(entidad == null){
           throw new WebApplicationException("El recurso /artistas/" + artistasId + "no existe.", 404);
       }
       
       return new ArtistaDetailDTO(entidad);
    }
   
   /*Retorna a los artistas*/
   @GET
public List<ArtistaDetailDTO> getArtistas(){
     return artistaListEntity2DTO(logica.getArtistas());
 }

/*Actualiza al artista*/
@PUT
@Path("{artistasId: \\d+}")
public ArtistaDetailDTO updateArtistas(@PathParam("artistasId") Long artistasId, ArtistaDetailDTO artista) throws WebApplicationException, BusinessLogicException {
    if(logica.getArtista(artistasId) == null){
        throw new WebApplicationException("El recurso /artistas/" + artistasId + " no existe.", 404);
    }
    
    ArtistaDetailDTO detailDTO = new ArtistaDetailDTO(logica.updateArtista(artistasId, artista.toEntity()));
    return detailDTO;
}
/*Elimina al artista*/
@DELETE
@Path("{artistasId: \\d+}")
public void deleteArtista(@PathParam("artistasId") Long artistasId) throws BusinessLogicException {
    
    if (logica.getArtista(artistasId) == null) {
        throw new WebApplicationException("El recurso /artistas/" + artistasId + " no existe.", 404);
    }
    logica.deleteArtista(artistasId);
}

public List<ArtistaDetailDTO> artistaListEntity2DTO(List<ArtistaEntity> entityList){
    List<ArtistaDetailDTO> list = new ArrayList<>();
    for(ArtistaEntity entity: entityList){
        list.add(new ArtistaDetailDTO(entity));
    }
    return list;

}

}
 
