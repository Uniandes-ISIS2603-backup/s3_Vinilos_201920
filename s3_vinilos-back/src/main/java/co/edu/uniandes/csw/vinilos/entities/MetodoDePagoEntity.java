/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Diego Bogotá
 */
@Entity
public class MetodoDePagoEntity extends BaseEntity implements Serializable {
    
    public enum Pago{
    PSE, TAJETACREDITO
}   
    private long numeroTarjeta;
    
    private String cuentaPSE;
    
    private double montoPagado;
    
    private Pago pago;
    
    @PodamExclude
    @OneToOne(mappedBy = "metodoPago", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    private PedidoEntity pedido;

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCuentaPSE() {
        return cuentaPSE;
    }

    public void setCuentaPSE(String cuentaPSE) {
        this.cuentaPSE = cuentaPSE;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
    
    
}
