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
    
    /**
     * id
     */
    private Long id;
    
    /**
    *nombre del usuario
    */
    private String nombre;
    
    /**
    *correo del usuario
    */
    private String correo;
    
    /**
    *fecha de nacimiento del usuario
    */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaNacim;
    
    /**
    *numero de celular del usuario
    */
    private Integer celular;
    
    /**
    *pais del usuario
    */
    private String pais;
    
    /**
    *direccion del usuario
    */
    private String direccion;

    /**
     * contraseña
     */
    private String contrasena;
    
    ///////SETS
    /**
     * Cambia el id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Cambia el nombre
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Cambia el correo
     * @param correo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Cambia la fecha de nacimiento
     * @param fechaNacim fecha de nacimiento
     */
    public void setFechaNacim(Date fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    /**
     * Cambia el celular
     * @param celular celular
     */
    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    /**
     * Cambia el pais
     * @param pais pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Cambia la direccion
     * @param direccion direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Cambia la contraseña
     * @param contrasena contraseña
     * 
     */
    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }
 
    /////////////GETS
    /**
    * Da el id 
    * @return id
    */
    public Long getId() {
        return id;
    }

    /**
     * Da el nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Da el correo
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Da la fecha de nacimiento
     * @return fechaNacim
     * /
     */
    public Date getFechaNacim() {
        return fechaNacim;
    }

    /**
     * Da el celular
     * @return celular
     */
    public Integer getCelular() {
        return celular;
    }

    /**
     * Da el pais
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Da la direccion
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Da la contraseña 
     * @return contraseña
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Constructor
     */
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(UsuarioEntity personaje) {
        if(personaje != null) {
            this.nombre = personaje.getNombre();
            this.celular = personaje.getCelular();
            this.correo = personaje.getCorreo();
            this.direccion = personaje.getDireccion();
            this.fechaNacim = personaje.getFechaNacim();
            this.pais = personaje.getPais();
            this.contrasena = personaje.getContrasena();
            this.id = personaje.getId();
        }
    }
    
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);    
        entity.setCelular(this.celular);
        entity.setCorreo(this.correo);
        entity.setDireccion(this.direccion);
        entity.setFechaNacim(this.fechaNacim);
        entity.setPais(this.pais);
        entity.setContrasena(this.contrasena);
        return entity;
    }
}
