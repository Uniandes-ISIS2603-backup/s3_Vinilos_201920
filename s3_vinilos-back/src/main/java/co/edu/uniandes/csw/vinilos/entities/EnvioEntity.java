/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;
import co.edu.uniandes.csw.vinilos.podam.DateStrategy;
import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan gonzalez
 */
@Entity
public class EnvioEntity extends BaseEntity implements Serializable 
{
    /**
     * Dia del envio
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
       /**
        * si el envio fue enviado
        */
    private boolean enviado;
    
    /**
     * Relacion con pedido
     */
    @PodamExclude
    @OneToOne
    private PedidoEntity pedido;

    /**
     *pedir pedido
     * @param pedido 
     */
    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
/**
 * dar la entidad de pedido
 * @return el pedido
 */
    public PedidoEntity getPedido() {
        return pedido;
    }
    /**
     * dar fecha
     * @return la fecha
     */
    public Date getFecha() {
        return fecha;
    }
/**
 * dice si fue enviado
 * @return si fue enviado
 */
    public boolean isEnviado() {
        return enviado;
    }
/**
 * cambia la fecha
 * @param fecha 
 */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
/**
 * Cambia el boolean 
 * @param enviado 
 */
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    /*public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }*/
   
    
}
