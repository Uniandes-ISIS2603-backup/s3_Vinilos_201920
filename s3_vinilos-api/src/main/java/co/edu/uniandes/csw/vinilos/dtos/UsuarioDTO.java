/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Manuel Sosa
 */
public class UsuarioDTO implements Serializable {
    
    private Long id;
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
    @PodamStrategyValue(DateStrategy.class)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaNacim(Date fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFechaNacim() {
        return fechaNacim;
    }

    public Integer getCelular() {
        return celular;
    }

    public String getPais() {
        return pais;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(UsuarioEntity personaje) {
        this.nombre = personaje.getName();
        this.celular = personaje.getCelular();
        this.correo = personaje.getCorreo();
        this.direccion = personaje.getDireccion();
        this.fechaNacim = personaje.getfechaNacim();
        this.pais = personaje.getPais();
        this.id = personaje.getId();
    }
    
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);    
        entity.setCelular(this.celular);
        entity.setCorreo(this.correo);
        entity.setDireccion(this.direccion);
        entity.setFechaNacim(this.fechaNacim);
        entity.setPais(this.pais);
        return entity;
    }
}
