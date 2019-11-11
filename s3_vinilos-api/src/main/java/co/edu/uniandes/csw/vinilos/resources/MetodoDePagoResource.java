
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.MetodoDePagoDTO;
import co.edu.uniandes.csw.vinilos.dtos.PedidoDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.MetodoDePagoLogic;
import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Diego Bogotá
 */
@Path("metodosDePago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MetodoDePagoResource {
   
     @Inject
    private MetodoDePagoLogic logica;
     
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoResource.class.getName());
    
    /*Crea el metodo de pago*/
    @POST
     public MetodoDePagoDTO crearMetodoDePago(MetodoDePagoDTO metodoDePago) throws BusinessLogicException{
    MetodoDePagoEntity metodoDePagoEntity = metodoDePago.toEntity();
    metodoDePagoEntity = logica.createMetodoDePago(metodoDePagoEntity);
    return new MetodoDePagoDTO(metodoDePagoEntity);
   
    
     }
     @GET
    public List<MetodoDePagoDTO> getMetodosPago() {
        List<MetodoDePagoDTO> listaMetodos = listEntityMetodos(logica.getMetodoDePagos());
        return listaMetodos;
    }
    
    private List<MetodoDePagoDTO> listEntityMetodos(List<MetodoDePagoEntity> entityList) {
        List<MetodoDePagoDTO> list = new ArrayList<>();
        for (MetodoDePagoEntity entity : entityList) {
            list.add(new MetodoDePagoDTO(entity));
        }
        return list;
    }
}
