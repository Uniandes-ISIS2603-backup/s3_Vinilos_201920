/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.resources;

import co.edu.uniandes.csw.vinilos.dtos.PedidoDTO;
import co.edu.uniandes.csw.vinilos.dtos.PedidoDetailDTO;
import co.edu.uniandes.csw.vinilos.ejb.PedidoLogic;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Stephania Otalora
 */
@Path("pedidos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PedidoResource {
    
    private static final Logger LOGGER = Logger.getLogger(PedidoResource.class.getName());

    @Inject
    private PedidoLogic pedidoLogic;
    
     @POST
    public PedidoDTO createPedido(PedidoDTO pedido) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PedidoResource createPedido: input: {0}", pedido);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PedidoEntity pedidoEntity = pedido.toEntity();
        // Invoca la lógica para crear el pedido nueva
        PedidoEntity nuevoPedidoEntity = pedidoLogic.createPedido(pedidoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PedidoDTO nuevoPedidolDTO = new PedidoDTO(nuevoPedidoEntity);
        LOGGER.log(Level.INFO, "PedidoResource createPedido:: output: {0}", nuevoPedidolDTO);
        return nuevoPedidolDTO;
    }
    

    @GET
    public List<PedidoDetailDTO> getPedidos() {
        LOGGER.info("PedidoResource getPedidos: input: void");
        List<PedidoDetailDTO> listaEditoriales = listEntityPedidos(pedidoLogic.getPedidos());
        LOGGER.log(Level.INFO, "PedidoResource getPedidos: output: {0}", listaEditoriales);
        return listaEditoriales;
    }
    
    private List<PedidoDetailDTO> listEntityPedidos(List<PedidoEntity> entityList) {
        List<PedidoDetailDTO> list = new ArrayList<>();
        for (PedidoEntity entity : entityList) {
            list.add(new PedidoDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    @Path("{pedidosId: \\d+}")
    public PedidoDetailDTO getPedido(@PathParam("pedidosId") Long pedidosId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "PedidoResource getPedido: input: {0}", pedidosId);
        PedidoEntity pedidoEntity = pedidoLogic.getPedido(pedidosId);
        if (pedidoEntity == null) {
            throw new WebApplicationException("El recurso /pedidos/" + pedidosId + " no existe.", 404);
        }
        PedidoDetailDTO detailDTO = new PedidoDetailDTO(pedidoEntity);
        LOGGER.log(Level.INFO, "PedidoResource getPedido: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{pedidosId: \\d+}")
    public PedidoDetailDTO updatePedido(@PathParam("pedidosId") Long pedidosId, PedidoDetailDTO pedido) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "PedidoResource updatePedido: input: id:{0} , pedido: {1}", new Object[]{pedidosId, pedido});
        pedido.setId(pedidosId);
        if (pedidoLogic.getPedido(pedidosId) == null) {
            throw new WebApplicationException("El recurso /pedidos/" + pedidosId + " no existe.", 404);
        }
        PedidoDetailDTO detailDTO = new PedidoDetailDTO(pedidoLogic.updatePedido(pedidosId, pedido.toEntity()));
        LOGGER.log(Level.INFO, "PedidoResource updatePedido: output: {0}", detailDTO);
        return detailDTO;

    }
    
    @DELETE
    @Path("{pedidosId: \\d+}")
    public void deletePedido(@PathParam("pedidosId") Long pedidosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PedidoResource deletePedido: input: {0}", pedidosId);
        if (pedidoLogic.getPedido(pedidosId) == null) {
            throw new WebApplicationException("El recurso /pedidos/" + pedidosId + " no existe.", 404);
        }
        pedidoLogic.deletePedido(pedidosId);
        LOGGER.info("PedidoResource deletePedido: output: void");
    }
}
