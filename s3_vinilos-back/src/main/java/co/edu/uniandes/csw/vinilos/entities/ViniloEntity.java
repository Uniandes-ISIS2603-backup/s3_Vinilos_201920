/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Jhoan Sebastian Diaz Romero
 */
@Entity
public class ViniloEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String anio;
    private String coleccion;
    private double precio;
    private String informacion;
    private String estado;
    private boolean disponible;

    /*@PodamExclude
    @OneToOne
    private PedidoEntity pedidoCompra;
    
    @PodamExclude
    @OneToOne
    private PedidoEntity pedidoIntercambio ;*/
    
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
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
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
    public double getPrecio() {
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
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /*public PedidoEntity getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(PedidoEntity pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public PedidoEntity getPedidoIntercambio() {
        return pedidoIntercambio;
    }

    public void setPedidoIntercambio(PedidoEntity pedidoIntercambio) {
        this.pedidoIntercambio = pedidoIntercambio;
    }*/

    
   
}
