/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.adapters.DateAdapter;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity.TipoPedido;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * @author Stephania Otalora Giraldo
 */

public class PedidoDTO implements Serializable
{
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaGeneracion;
    private boolean aceptado;
    private String observacion;
    private TipoPedido tipo;
    private ViniloDTO viniloCompra;
    private EnvioDTO envio;
    private Long id;
    private List<ViniloDTO> vinilosIntercambio;
    private MetodoDePagoDTO metodoPago;
    private UsuarioDTO usuario;
    
    
    
    public PedidoDTO()
    {}

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public ViniloDTO getViniloCompra() {
        return viniloCompra;
    }

    public void setViniloCompra(ViniloDTO viniloCompra) {
        this.viniloCompra = viniloCompra;
    }

    public EnvioDTO getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioDTO envio) {
        this.envio = envio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ViniloDTO> getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    public void setVinilosIntercambio(List<ViniloDTO> vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }

    public MetodoDePagoDTO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoDePagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
    /**
     * Constructor a partir de la entidad
     *
     * @param pedidoEntity La entidad del pedido
     */
    public PedidoDTO(PedidoEntity pedidoEntity) {
        if (pedidoEntity != null) {
            this.aceptado = pedidoEntity.getAceptado();
            this.fechaGeneracion = pedidoEntity.getFechaGeneracion();
            this.observacion = pedidoEntity.getObservacion();
            this.tipo = pedidoEntity.getTipo();
            this.id = pedidoEntity.getId();
            if (pedidoEntity.getViniloCompra()!= null) {
                this.viniloCompra = new ViniloDTO(pedidoEntity.getViniloCompra());
            } else {
                this.viniloCompra = null;
            }
            if (pedidoEntity.getEnvio()!= null) {
                this.envio = new EnvioDTO(pedidoEntity.getEnvio());
            } else {
                this.envio = null;
            }
            if (pedidoEntity.getMetodoPago()!= null) {
                this.metodoPago = new MetodoDePagoDTO(pedidoEntity.getMetodoPago());
            } else {
                this.metodoPago = null;
            }
            if(pedidoEntity.getUsuario()!=null)
             {
                 this.usuario = new UsuarioDTO(pedidoEntity.getUsuario());
             } else
             {
                 this.usuario = null;
             }
                
        }
    }
    
     /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del pedido asociado.
     */
    public PedidoEntity toEntity() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setAceptado(this.aceptado);
        pedidoEntity.setTipo(this.tipo);
        pedidoEntity.setFechaGeneracion(this.fechaGeneracion);
        pedidoEntity.setObservacion(this.observacion);
        if (this.viniloCompra != null) {
            pedidoEntity.setViniloCompra(this.viniloCompra.toEntity());
        }
        if (this.envio!= null) {
            pedidoEntity.setEnvio(this.envio.toEntity());
        }
        if(this.usuario!= null)
        {
            pedidoEntity.setUsuario(this.usuario.toEntity());
        }
        if(this.metodoPago!=null)
        {
            pedidoEntity.setMetodoPago(this.metodoPago.toEntity());
        }
        return pedidoEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
