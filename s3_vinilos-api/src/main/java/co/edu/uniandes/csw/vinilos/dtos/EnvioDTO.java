/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.adapters.DateAdapter;
import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    /**
     * si se envio
     */
    private boolean fueEnviado;
    
    private PedidoDTO pedido;
    
    
    
    /**
     * get de la id
     * @return id de la clase
     */
    public Long getId() {
        return this.id;
    }
/**
 * dar fecha
 * @return la fecha
 */
    public Date getFecha() {
        return this.fecha;
    }
/**
 * boolean de si fue enviado
 * @return si el envio se envio.
 */
    public boolean isFueEnviado() {
        return this.fueEnviado;
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

    public PedidoDTO getPedido() {
        return this.pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }
    
    
    
    public EnvioDTO()
    {
        
    }
 /**
     * Constructor a partir de la entidad
     *
     * @param EnvioDTO La entidad del envio
     */
    public EnvioDTO(EnvioEntity envioEntity) 
    {
        if(envioEntity!=null)
        {
        this.id = envioEntity.getId();
        this.fecha = envioEntity.getFecha();
        this.fueEnviado = envioEntity.isEnviado();
        if(envioEntity.getPedido()!=null)
        {
            this.pedido= new PedidoDTO(envioEntity.getPedido());
        }
        else
        {
            this.pedido= null;
        }
        }
    }
   /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del envío asociado.
     */
    public EnvioEntity toEntity() 
    {        
        EnvioEntity envioEntity = new EnvioEntity();
        envioEntity.setEnviado(this.fueEnviado);
        envioEntity.setFecha(this.fecha);
        envioEntity.setPedido(this.pedido.toEntity());
        
       
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
