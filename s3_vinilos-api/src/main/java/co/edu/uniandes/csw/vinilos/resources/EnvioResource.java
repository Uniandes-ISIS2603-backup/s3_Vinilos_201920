/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.EnvioDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
     /**
     * Retorna un envio DTO
     *
     * @param EnvioDto
     * @param booksId El ID del libro que se asocia
     * @return El envio dto.
     */
      @POST
      public EnvioDTO createEnviooDTO(EnvioDTO envio)
      {
          return envio;
      }
}
