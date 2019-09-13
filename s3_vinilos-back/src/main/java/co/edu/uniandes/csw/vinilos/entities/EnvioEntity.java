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
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
       
    private boolean enviado;
    
  /*  @PodamExclude
    @OneToOne
    private PedidoEntity pedido;*/
    
    public Date getFecha() {
        return fecha;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

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
