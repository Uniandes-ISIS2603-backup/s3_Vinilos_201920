/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Gonzalez
 */
public class EnvioDTO implements Serializable 
{
       /**
        * id
        */
    private Long id;
    /**
     * fecha de envio
     */
    private Date fecha;
    /**
     * si se envio
     */
    private boolean fueEnviado;
    /**
     * get de la id
     * @return id de la clase
     */
    public Long getId() {
        return id;
    }
/**
 * dar fecha
 * @return la fecha
 */
    public Date getFecha() {
        return fecha;
    }
/**
 * boolean de si fue enviado
 * @return si el envio se envio.
 */
    public boolean isFueEnviado() {
        return fueEnviado;
    }
/**
 * set de la id
 * @param id 
 */
    public void setId(Long id) {
        this.id = id;
    }
/**
 * set de la fecha
 * @param fecha 
 */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @param fueEnviado 
     */
    public void setFueEnviado(boolean fueEnviado) {
        this.fueEnviado = fueEnviado;
    }
 /**
     * Constructor a partir de la entidad
     *
     * @param EnvioDTO La entidad del envio
     */
    public EnvioDTO(EnvioEntity envioEntity) 
    {
        this.id = envioEntity.getId();
        this.fecha = envioEntity.getFecha();
        this.fueEnviado = envioEntity.isEnviado();
    }
   /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del envío asociado.
     */
    public EnvioEntity toEntity() 
    {
        
        EnvioEntity envioEntity = new EnvioEntity();
        envioEntity.setEnviado(this.isFueEnviado());
        envioEntity.setFecha(this.getFecha());
        envioEntity.setId(this.getId());
       
        return envioEntity;
    }

    /**
     * to String
     * @return To String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
