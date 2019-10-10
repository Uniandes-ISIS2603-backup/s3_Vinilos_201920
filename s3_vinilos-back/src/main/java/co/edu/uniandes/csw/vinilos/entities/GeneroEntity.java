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
   
    private String nombre;

    @PodamExclude
    @ManyToOne
    private ArtistaEntity generosArtista;

    public ArtistaEntity getGenerosArtista() {
        return generosArtista;
    }

    public void setGenerosArtista(ArtistaEntity generosArtista) {
        this.generosArtista = generosArtista;
    }
    
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    
    public String getNombre() 
    {
        return nombre;
    }
}
