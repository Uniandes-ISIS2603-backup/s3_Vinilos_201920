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
 * @author Estudiante
 */
public class EnvioDTO implements Serializable 
{

       
    private Long id;
    
    private Date fecha;
    
    private boolean fueEnviado;
    
    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isFueEnviado() {
        return fueEnviado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFueEnviado(boolean fueEnviado) {
        this.fueEnviado = fueEnviado;
    }

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

    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
