
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
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan Diego Bogotá
 */
@Entity
public class ArtistaEntity extends BaseEntity implements Serializable{

    private String name;
    
    @PodamExclude
    @OneToMany(mappedBy = "generosArtista")
    private List<GeneroEntity> generos = new ArrayList<GeneroEntity>();

    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    public List<ViniloEntity> getVinilos() {
        return vinilos;
    }

    public void setVinilos(List<ViniloEntity> vinilos) {
        this.vinilos = vinilos;
    }
    
    @PodamExclude
    @OneToMany(mappedBy = "vinilosArtista")
    private List<ViniloEntity> vinilos = new ArrayList<ViniloEntity>();


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

   
   
}
   