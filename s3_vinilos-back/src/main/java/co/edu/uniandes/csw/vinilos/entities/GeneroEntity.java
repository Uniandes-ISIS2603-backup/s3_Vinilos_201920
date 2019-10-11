/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan gonzalez
 */
@Entity
public class GeneroEntity extends BaseEntity implements Serializable {
   /**
    * nombre del genero
    */
    private String nombre;
/**
 * relacion con artista
 */
    @PodamExclude
    @ManyToOne
    private ArtistaEntity generosArtista;
/**
 * da el genero del artista
 * @return el artista de los generos
 */
    public ArtistaEntity getGenerosArtista() {
        return generosArtista;
    }
/**
 * cambia el artista
 * @param generosArtista 
 */
    public void setGenerosArtista(ArtistaEntity generosArtista) {
        this.generosArtista = generosArtista;
    }
    /**
     * Cambia el nombre del artista
     * @param nombre 
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    /**
     * cambia el nombre 
     * @return nombre del genero
     */
    public String getNombre() 
    {
        return nombre;
    }
}
