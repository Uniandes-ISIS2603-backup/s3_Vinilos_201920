/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan gonzalez
 */
public class GeneroDTO implements Serializable 
{
     /**
        * id del genero
        */
    private Long id;
    /**
     * Nombre del envio
     */
    private String nombre;
    /**
     * id del genero
     * @return la Id del genero
     */
    public Long getId() {
        return id;
    }
/**
 * get del nombre
 * @return nombre del genero
 */
    public String getNombre() {
        return nombre;
    }
/**
 * set del id
 * @param id 
 */
    public void setId(Long id) {
        this.id = id;
    }
/**
 * Set del nombre
 * @param nombre 
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * 
 * 
 * Método para transformar el DTO a una entidad.
     *
 * @param generoEntity 
 */
    public GeneroDTO(GeneroEntity generoEntity) 
    {
        this.id = generoEntity.getId();
        this.nombre = generoEntity.getNombre();
    }
    public GeneroDTO()
    {
    
    }
  /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del envío asociado.
     */
    public GeneroEntity toEntity(){
         GeneroEntity generoEntity = new GeneroEntity();
         generoEntity.setId(this.getId());
         generoEntity.setNombre(this.getNombre());
         return generoEntity;
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
