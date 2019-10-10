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
 * @author Estudiante
 */
public class GeneroDTO implements Serializable 
{
    private Long id;
    
    private String nombre;
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GeneroDTO(GeneroEntity generoEntity) 
    {
        this.id = generoEntity.getId();
        this.nombre = generoEntity.getNombre();
    }
 
    public GeneroEntity toEntity(){
         GeneroEntity generoEntity = new GeneroEntity();
         generoEntity.setId(this.getId());
         generoEntity.setNombre(this.getNombre());
         return generoEntity;
    }
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
}
