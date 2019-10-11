/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.GeneroDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
       /**
     * Retorna un Genero DTO
     *
     * @param EnvioDto
     * @return El genero dto.
     */
      @POST
      public GeneroDTO createGeneroDTO(GeneroDTO generos)
      {
          return generos;
      }
}
