/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.EnvioDTO;
import co.edu.uniandes.csw.vinilos.ejb.EnvioLogic;
import co.edu.uniandes.csw.vinilos.ejb.PedidoLogic;
import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.GET;
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
    private EnvioLogic envioLogic;
     /**
     * Retorna un envio DTO
     *
     * @param EnvioDto
     * @param booksId El ID del libro que se asocia
     * @return El envio dto.
     */
      @POST
      public EnvioDTO createEnvioDTO(EnvioDTO envio) throws BusinessLogicException
      {
           EnvioEntity envioEntity = envio.toEntity();
        // Invoca la lógica para crear el pedido nueva
        EnvioEntity nuevoEnvioEntity = envioLogic.createEnvio(envioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EnvioDTO(nuevoEnvioEntity);
        
      }
      
      @GET
    public List<EnvioDTO> getPedidos() {
        //LOGGER.info("PedidoResource getPedidos: input: void");
        List<EnvioDTO> listaEnvios = listEntityPedidos(envioLogic.getEnvio());
        //LOGGER.log(Level.INFO, "PedidoResource getPedidos: output: {0}", listaEnvios);
        return listaEnvios;
    }
    
    @GET
    @Path("{envioId:\\d+}")
    public EnvioDTO getEnvio(@PathParam("envioId") long id )
    {
        EnvioEntity envioEntity = envioLogic.getEnvio(id);
        if(envioEntity ==null)
        {
            throw new WebApplicationException("El recurso /envio/"+ id+ "no existe.", 404);
        }
        EnvioDTO envioDTO = new EnvioDTO(envioEntity);
        return envioDTO;
    }
    
    private List<EnvioDTO> listEntityPedidos(List<EnvioEntity> entityList) {
        List<EnvioDTO> list = new ArrayList<>();
        for (EnvioEntity entity : entityList) {
            list.add(new EnvioDTO(entity));
        }
        return list;
    }
}
