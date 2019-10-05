
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.MetodoDePagoDTO;
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
public class MetodoDePagoResource {
   
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoResource.class.getName());
    
    @POST
    public MetodoDePagoDTO createMetodoDePago(MetodoDePagoDTO metodoDePago){
       return metodoDePago; 
    }
}
