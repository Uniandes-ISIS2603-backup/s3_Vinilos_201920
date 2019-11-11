/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity.Pago;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Bogotá
 */
public class MetodoDePagoDTO implements Serializable{
    
    /*Numero de la tarjeta*/
    private long numeroTarjeta;
    
    /*Cuenta PSE*/
    private String cuentaPSE;
    
    /*Monto pagado*/
    private double montoPagado;
    
    private Pago pago; 
    
    public MetodoDePagoDTO(){
        
    }
   
    /*Retorna el numero de la tarjeta*/
    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }
 /*Cambia el numero de la tarjeta*/
   
    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
 /*Retorna la cuenta PSE*/
   
    public String getCuentaPSE() {
        return cuentaPSE;
    }
/*RCambia la cuenta PSE*/
    public void setCuentaPSE(String cuentaPSE) {
        this.cuentaPSE = cuentaPSE;
    }
/*Retorna el monto pagado*/
    public double getMontoPagado() {
        return montoPagado;
    }
/*Cambia el monto pagado*/
    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
     
  
/*Metodo constructor de la clase */
 public MetodoDePagoDTO(MetodoDePagoEntity metodoDePagoEntity) {
    if (metodoDePagoEntity != null) {
        this.numeroTarjeta = metodoDePagoEntity.getNumeroTarjeta();
        this.cuentaPSE = metodoDePagoEntity.getCuentaPSE();
        this.montoPagado = metodoDePagoEntity.getMontoPagado();
     //   this.pago = metodoDePagoEntity.getPago();
    }
}

 /*Crea un MetodoDePagoEntity*/
public MetodoDePagoEntity toEntity() {
    MetodoDePagoEntity metodoDePagoEntity = new MetodoDePagoEntity();
    metodoDePagoEntity.setNumeroTarjeta(this.numeroTarjeta);
    metodoDePagoEntity.setCuentaPSE(this.cuentaPSE);
    metodoDePagoEntity.setMontoPagado(this.montoPagado);
    metodoDePagoEntity.setPago(this.pago);
    return metodoDePagoEntity;
}

@Override
public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}
}
