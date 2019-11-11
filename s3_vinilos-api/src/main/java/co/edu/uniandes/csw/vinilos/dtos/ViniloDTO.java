/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class ViniloDTO implements Serializable{

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    private Long id;
    private String nombre;
    private Integer anio;
    private String coleccion;
    private Double precio;
    private String informacion;
    private String estado;
    private Boolean disponible;
    private UsuarioDTO duenio;
    private String imagen;
    
    public ViniloDTO(){
        
    }
    
    public ViniloDTO(ViniloEntity entidad){
        setId(entidad.getId());
        setNombre(entidad.getNombre());
        setAnio(entidad.getAnio());
        setColeccion(entidad.getColeccion());
        setPrecio(entidad.getPrecio());
        setInformacion(entidad.getInformacion());
        setEstado(entidad.getEstado());
        setDisponible(entidad.isDisponible());
        setImagen(entidad.getImagen());
    }
    
    public ViniloEntity toEntity() {
        ViniloEntity entidad = new ViniloEntity();
        entidad.setId(this.getId());
        entidad.setNombre(this.getNombre());
        entidad.setAnio(this.getAnio());
        entidad.setColeccion(this.getColeccion());
        entidad.setPrecio(this.getPrecio());
        entidad.setInformacion(this.getInformacion());
        entidad.setEstado(this.getEstado());
        entidad.setDisponible(this.isDisponible());
        entidad.setImagen(this.getImagen());
        return entidad;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the coleccion
     */
    public String getColeccion() {
        return coleccion;
    }

    /**
     * @param coleccion the coleccion to set
     */
    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the informacion
     */
    public String getInformacion() {
        return informacion;
    }

    /**
     * @param informacion the informacion to set
     */
    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the disponible
     */
    public Boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the duenio
     */
    public UsuarioDTO getDuenio() {
        return duenio;
    }

    /**
     * @param duenio the duenio to set
     */
    public void setDuenio(UsuarioDTO duenio) {
        this.duenio = duenio;
    }
    
}
