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
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
