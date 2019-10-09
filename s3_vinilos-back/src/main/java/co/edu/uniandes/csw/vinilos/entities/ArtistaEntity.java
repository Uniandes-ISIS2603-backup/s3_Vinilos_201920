
	/*
	 * To change this license header, choose License Headers in Project Properties.
	/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
/**
 *
 * @author Juan Diego Bogotá
 */
@Entity
public class ArtistaEntity extends BaseEntity implements Serializable{

    private String name;
    
    private GeneroEntity[] generos;
    
    private ViniloEntity[] vinilos;

    public ViniloEntity[] getVinilos() {
        return vinilos;
    }

    public void setVinilos(ViniloEntity[] vinilos) {
        this.vinilos = vinilos;
    }

   

    private String rutaFoto;

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
   
  
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public GeneroEntity[] getGeneros() {
        return generos;
    }

    public void setGeneros(GeneroEntity[] generos) {
        this.generos = generos;
    }
   
}
   