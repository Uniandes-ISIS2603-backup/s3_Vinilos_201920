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
    
    /*Id del artista */
    private long id;
    /*Nombre del artista */
    private String name;
    /*Ruta de la foto del artista */
    private String rutaFoto;

    /*Retorna la ruta de la foto del artista */
    public String getRutaFoto() {
        return rutaFoto;
    }

    /*Cambia la ruta de la foto del artista */
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public ArtistaDTO(){
        
    }
    
    /*Metodo constructor de la clase */
     public ArtistaDTO(ArtistaEntity artistaEntity) {
        if (artistaEntity != null) {
            this.id = artistaEntity.getId();
            this.name = artistaEntity.getName();
            this.rutaFoto = artistaEntity.getRutaFoto();
        }
    }
    
    /*
     Crea un artistaEntiry
     */
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

      /*Retorna el id del artista */
    public long getId() {
        return id;
    }

      /*Cambia el id del artista */
    public void setId(long id) {
        this.id = id;
    }
  /*Retorna el nombre del artista */
    public String getName() {
        return name;
    }
  /*Cambia el nombre del artista */
    public void setName(String name) {
        this.name = name;
    }
    
    //Arreglobase de datos
    
}
