/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Stephania Otalora
 */
public class PedidoDetailDTO extends PedidoDTO implements Serializable {

    /*
    * Esta lista de tipo ViniloDTO contiene los vinilos que se señallan para el intercambio
     */
    private List<ViniloDTO> vinilosIntercambio;

    /**
     * Constructor por defecto
     */
    public PedidoDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param pedidoEntity La entidad de la editorial para transformar a DTO.
     */
    public PedidoDetailDTO(PedidoEntity pedidoEntity) {
        super(pedidoEntity);
        if (pedidoEntity != null) {
            if (pedidoEntity.getVinilosIntercambio() != null) {
                vinilosIntercambio = new ArrayList<>();
                for (ViniloEntity entityPedido : pedidoEntity.getVinilosIntercambio()) {
                    vinilosIntercambio.add(new ViniloDTO(entityPedido));
                }
            }
        }
    }

     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO del pedido para transformar a Entity
     */
    @Override
    public PedidoEntity toEntity() {
        PedidoEntity pedidoEntity = super.toEntity();
        if (vinilosIntercambio != null) {
            List<ViniloEntity> vinilosEntity = new ArrayList<>();
            for (ViniloDTO dtoVinilo : vinilosIntercambio) {
                vinilosEntity.add(dtoVinilo.toEntity());
            }
            pedidoEntity.setVinilosIntercambio(vinilosEntity);
        }
        return pedidoEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public List<ViniloDTO> getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    public void setVinilosIntercambio(List<ViniloDTO> vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }
    
    
    
}
