/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;
import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author Juan Diego Bogotá
 */
@Entity
public class ArtistaEntity extends BaseEntity implements Serializable{

    private String name;
    
    private int edad;
     
    private String disquera;
    
    private GeneroEntity genero;
    
    private int cantidadVinilos;

    public int getCantidadVinilos() {
        return cantidadVinilos;
    }

    public void setCantidadVinilos(int pCantidadVinilos) {
        this.cantidadVinilos = pCantidadVinilos;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity pGenero) {
        this.genero = pGenero;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }
   
     public int getEdad() {
        return edad;
    }

    public void setEdad(int pEdad) {
        this.edad = pEdad;
    }
    
     public String getDisquera() {
        return disquera;
    }

    public void setDisquera(String pDisquera) {
        this.disquera = pDisquera;
    }
   
}
