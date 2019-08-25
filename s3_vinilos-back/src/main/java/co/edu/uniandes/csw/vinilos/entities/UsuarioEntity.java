/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Manuel Sosa
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    ///////  Atributos 
    
    /*
    nombre del usuario
    */
    private String nombre;
    
    /*
    correo del usuario
    */
    private String correo;
    
    /*
    fecha de nacimiento del usuario
    */
    @Temporal(TemporalType.DATE)
    private Date fechaNacim;
    
    /*
    numero de celular del usuario
    */
    private Integer celular;
    
    /*
    pais del usuario
    */
    private String pais;
    
    /*
    direccion del usuario
    */
    private String direccion;
    
    /////// Gets
    /*
    Da el nombre
    */
    public String getName(){
        return nombre;
    }
    
    /*
    Da el correo
    */
    public String getCorreo(){
        return correo;
    }
    
    /*
    Da la fecha de nacimiento
    */
    public Date getfechaNacim(){
        return fechaNacim;
    }
    
    /*
    Da el numero de celular
    */
    public Integer getCelular(){
        return celular;
    }
    
    /*
    Da el pais
    */
    public String getPais(){
        return pais;
    }
    
    /*
    Da la direccion
    */
    public String getDireccion(){
        return direccion;
    }
    
    ////// Sets 
    
    /*
    Cambia el nombre
    */
    public void setName(String pName){
        this.nombre = pName;
    }
    
    /*
    Cambia el correo
    */
    public void setCorreo(String pCorreo){
        this.correo = pCorreo;
    }
    
    /*
    Cambia la fecha de nacimiento
    */
    public void setFechaNacim(Date fecha){
        this.fechaNacim = fecha;
    }
    
    /*
    Cambia el celular
    */
    public void setCelular(Integer cel){
        this.celular = cel;
    }
    
    /*
    Cambia el pais
    */
    public void setPais(String pPais){
        this.pais = pPais;
    }
    
    /*
    Cambia la direccion
    */
    public void setDireccion(String pDireccion){
        this.direccion = pDireccion;
    }
    
    public UsuarioEntity(){
        
    }
    
}
