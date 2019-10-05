/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.ArtistaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Diego Bogotá
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArtistaResource {
   
    private static final Logger LOGGER = Logger.getLogger(ArtistaResource.class.getName());
    
    @POST
    public ArtistaDTO createArtista(ArtistaDTO artista){
       return artista; 
    }
}

