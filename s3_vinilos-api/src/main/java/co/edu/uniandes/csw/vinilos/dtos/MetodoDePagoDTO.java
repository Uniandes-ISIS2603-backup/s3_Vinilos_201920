/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Bogotá
 */
public class MetodoDePagoDTO implements Serializable{
    
    private long numeroTarjeta;
    
    private String cuentaPSE;
    
    private double montoPagado;
    
    public MetodoDePagoDTO(){
        
    }
   
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
 
  

 public MetodoDePagoDTO(MetodoDePagoEntity metodoDePagoEntity) {
    if (metodoDePagoEntity != null) {
        this.numeroTarjeta = metodoDePagoEntity.getNumeroTarjeta();
        this.cuentaPSE = metodoDePagoEntity.getCuentaPSE();
        this.montoPagado = metodoDePagoEntity.getMontoPagado();
    }
}

public MetodoDePagoEntity toEntity() {
    MetodoDePagoEntity metodoDePagoEntity = new MetodoDePagoEntity();
    metodoDePagoEntity.setNumeroTarjeta(this.numeroTarjeta);
    metodoDePagoEntity.setCuentaPSE(this.cuentaPSE);
    metodoDePagoEntity.setMontoPagado(this.montoPagado);
    return metodoDePagoEntity;
}

@Override
public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}
}
