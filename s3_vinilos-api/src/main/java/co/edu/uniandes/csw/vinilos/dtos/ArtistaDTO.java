/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Bogotá
 */
public class ArtistaDTO implements Serializable {
    
    private long id;
    private String name;
    private String rutaFoto;

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public ArtistaDTO(){
        
    }
    
     public ArtistaDTO(ArtistaEntity artistaEntity) {
        if (artistaEntity != null) {
            this.id = artistaEntity.getId();
            this.name = artistaEntity.getName();
            this.rutaFoto = artistaEntity.getRutaFoto();
        }
    }
    
    public ArtistaEntity toEntity() {
        ArtistaEntity artistaEntity = new ArtistaEntity();
        artistaEntity.setId(this.id);
        artistaEntity.setName(this.name);
        artistaEntity.setRutaFoto(this.rutaFoto);
        return artistaEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
