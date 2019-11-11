/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.EnvioDTO;
import co.edu.uniandes.csw.vinilos.ejb.EnvioLogic;
import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan gonzalez
 */
@Path("envio")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EnvioResource {
    private static final Logger LOGGER = Logger.getLogger(EnvioResource.class.getName());
     @Inject
    private EnvioLogic logica;
   
       @POST
       @Path("{enviosId: \\d+}")
     public EnvioDTO crearMetodoDePago(@PathParam("enviosId")EnvioDTO envio) throws BusinessLogicException{
    EnvioEntity envioEntity = envio.toEntity();
    envioEntity = logica.createEnvio(envioEntity);
    return new EnvioDTO(envioEntity);
   
    
     }
     @GET
     @Path("{enviosId: \\d+}")
    public List<EnvioDTO> getEnvios() {
        List<EnvioDTO> envios = listEntityEnvios(logica.getEnvio());
        return envios;
    }
    @DELETE
@Path("{enviosId: \\d+}")
public void deleteArtista(@PathParam("enviosId") Long envioId) throws BusinessLogicException {
    
    if (logica.getEnvio(envioId) == null) {
        throw new WebApplicationException("El recurso /envios/" + envioId + " no existe.", 404);
    }
    logica.deleteEnvio(envioId);
}
    
    private List<EnvioDTO> listEntityEnvios(List<EnvioEntity> entityList) {
        List<EnvioDTO> list = new ArrayList<>();
        for (EnvioEntity entity : entityList) {
            list.add(new EnvioDTO(entity));
        }
        return list;
    }
}
