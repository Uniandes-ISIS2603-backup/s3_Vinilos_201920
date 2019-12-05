    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.ViniloDTO;
import co.edu.uniandes.csw.vinilos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.ViniloLogic;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
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
 * @author Estudiante
 */
@Path("vinilos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViniloResource {
    
    @Inject
    private ViniloLogic logica;
    
   private static final Logger LOGGER = Logger.getLogger(ViniloResource.class.getName());
   
   @POST
   public ViniloDTO crearVinilo(ViniloDTO vinilo) throws BusinessLogicException{
    ViniloEntity viniloEntity = vinilo.toEntity();
    viniloEntity = logica.crearVinilo(viniloEntity);
    return new ViniloDTO(viniloEntity);
   }
   
   @GET
   @Path("{vinilosId: \\d+}")
  
   public ViniloDetailDTO getVinilo(@PathParam("vinilosId") Long vinilosId) throws WebApplicationException {
   
       ViniloEntity entidad = logica.getVinilo(vinilosId);
       if(entidad == null){
           throw new WebApplicationException("El recurso /vinilos/" + vinilosId + "no existe.", 404);
       }
       ViniloDetailDTO vinilo = new ViniloDetailDTO(entidad);
       return vinilo;
    }
   
   @GET
   public List<ViniloDetailDTO> getVinilos(){
        return viniloListEntity2DTO(logica.getVinilos());
    }
   
   @PUT
   @Path("{vinilosId: \\d+}")
   public ViniloDetailDTO updateVinilos(@PathParam("vinilosId") Long vinilosId, ViniloDetailDTO vinilo) throws WebApplicationException {
       if(logica.getVinilo(vinilosId) == null){
           throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
       }
       
       ViniloDetailDTO detailDTO = new ViniloDetailDTO(logica.updateVinilo(vinilosId, vinilo.toEntity()));
       return detailDTO;
   }
   
   @DELETE
   @Path("{vinilosId: \\d+}")
   public void deleteVinilo(@PathParam("vinilosId") Long vinilosId) throws BusinessLogicException {
       
       if (logica.getVinilo(vinilosId) == null) {
           throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
       }
       logica.deleteVinilo(vinilosId);
   }
   
   public List<ViniloDetailDTO> viniloListEntity2DTO(List<ViniloEntity> entityList){
       List<ViniloDetailDTO> list = new ArrayList<>();
       for(ViniloEntity entity: entityList){
           list.add(new ViniloDetailDTO(entity));
       }
       return list;
   }
   
    
}
