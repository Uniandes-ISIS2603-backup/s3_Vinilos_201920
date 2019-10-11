/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.PedidoPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Stephania Otalora
 */
@Stateless
public class PedidoLogic {
    
    @Inject
    private PedidoPersistence persistence;
    
    /**
     * Guardar un nuevo pedido
     *
     * @param pedido La entidad de tipo pedido del nuevo pedido a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el tipo no concuerda o la fecha es nula 
     */
    public PedidoEntity createPedido(PedidoEntity pedido) throws BusinessLogicException
    {
        if(pedido.getFechaGeneracion()== null )
        {
            throw  new BusinessLogicException("La fecha de generacion esta vacia");
        }
        if(pedido.getTipo()==PedidoEntity.TipoPedido.COMPRA)
        {
            if(pedido.getViniloCompra()==null)
            {
                throw new BusinessLogicException("No hay un vinilo de compra seleccionado");
            }
            if(pedido.getVinilosIntercambio()!=null)
            {
                throw new BusinessLogicException("Se realiza una compra pero se seleccionaron vinilos de intercambio");
            }
            if(!pedido.getViniloCompra().isDisponible())
            {
                throw new BusinessLogicException("El vinilo seleccionado no esta disponible para la compra");
            }
        }
        if(pedido.getTipo()==PedidoEntity.TipoPedido.INTERCAMBIO)
        {
            if(pedido.getVinilosIntercambio()==null)
            {
                throw new BusinessLogicException("No hay vinilos seleccionados para el intercambio");
            }
            if(pedido.getViniloCompra()!=null)
            {
                throw new BusinessLogicException("Se realiza un intercambio pero se seleccionó un vinilo de compra");
            }
            
            ArrayList<ViniloEntity> vinilosInter = (ArrayList<ViniloEntity>) pedido.getVinilosIntercambio();
            for(int i = 0; i<vinilosInter.size();i++)
            {
                if(!vinilosInter.get(i).isDisponible())
                {
                    throw new BusinessLogicException("El vinilo "+ vinilosInter.get(i)+" no esta disponible para hacer el intercambio");

                }
            }
            
        }
        pedido = persistence.create(pedido);
        return pedido;
    }
    
    /**
     * Devuelve todos los pedidos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo pedidos.
     */
     public List<PedidoEntity> getPedidos() 
    {       
        List<PedidoEntity> pedidos = persistence.findAll();
        return pedidos;
    }
    
     /**
     * Busca un pedido por ID
     *
     * @param pedidoId El id del pedido a buscar
     * @return El pedido encontrado, null si no lo encuentra.
     */
    public PedidoEntity getPedido(Long pedidoId) 
    {
        PedidoEntity pedido = persistence.find(pedidoId);
        return pedido;
    }

    /**
     * Actualizar un pedido por ID
     *
     * @param pedidoId El ID del pedido a actualizar
     * @param pedidoEntity La entidad del pedido con los cambios deseados
     * @return La entidad del pedido luego de actualizarla
     * @throws BusinessLogicException Si el no es aceptado y no tiene observacion
     * o si es aceptado y no tiene fecha de envio
     */
    public PedidoEntity updatePedido(Long pedidoId, PedidoEntity pedidoEntity) throws BusinessLogicException 
    {
        if(!pedidoEntity.getAceptado()&&pedidoEntity.getObservacion()==null)
        {
         throw new BusinessLogicException("No se agrego observación del porqué no se acepta el pedido");
        }
         if(pedidoEntity.getAceptado()&&pedidoEntity.getEnvio().getFecha()==null)
        {
         throw new BusinessLogicException("No se agrego una fecha estimada de envío del vinilo");
        }
        PedidoEntity newEntity = persistence.update(pedidoEntity);
        return newEntity;
    }
    
    /**
     * Eliminar un pedido por ID
     *
     * @param pedidoId El ID del pedido a eliminar
     * @throws BusinessLogicException si el pedido ya fue aceptado
     */
     public void deletePedido(Long pedidoId) throws BusinessLogicException {
        if (persistence.find(pedidoId).getAceptado()) {
            throw new BusinessLogicException("No se puede borrar el pedido con id = " + pedidoId + " porqueya ha sido aceptado");
        }
        persistence.delete(pedidoId);
     } 


}