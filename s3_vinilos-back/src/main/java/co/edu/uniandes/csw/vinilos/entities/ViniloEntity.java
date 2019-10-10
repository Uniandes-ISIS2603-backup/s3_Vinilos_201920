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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Jhoan Sebastian Diaz Romero
 */
@Entity
public class ViniloEntity extends BaseEntity implements Serializable{
    private String nombre;
    private Integer anio;
    private String coleccion;
    private Double precio;
    private String informacion;
    private String estado;
    private Boolean disponible;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity duenio;
    
    @PodamExclude
    @OneToMany
    private List<ViniloEntity> vinilosIntercambio = new ArrayList<ViniloEntity>();;

    @PodamExclude
    @OneToOne
    private PedidoEntity pedidoCompra;
    
    @PodamExclude
    @ManyToOne
    private PedidoEntity pedidoIntercambio;

    public ArtistaEntity getVinilosArtista() {
        return vinilosArtista;
    }

    public void setVinilosArtista(ArtistaEntity vinilosArtista) {
        this.vinilosArtista = vinilosArtista;
    }
    
    @PodamExclude
    @ManyToOne
    private ArtistaEntity vinilosArtista;
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
        this.setAnio((Integer) anio);
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
        this.setPrecio((Double) precio);
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
        return getDisponible();
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.setDisponible((Boolean) disponible);
    }

    public PedidoEntity getPedidoCompra() {
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
    }    

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the disponible
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the duenio
     */
    public UsuarioEntity getDuenio() {
        return duenio;
    }

    /**
     * @param duenio the duenio to set
     */
    public void setDuenio(UsuarioEntity duenio) {
        this.duenio = duenio;
    }

    /**
     * @return the vinilosIntercambio
     */
    public List<ViniloEntity> getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    /**
     * @param vinilosIntercambio the vinilosIntercambio to set
     */
    public void setVinilosIntercambio(List<ViniloEntity> vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }
   
}
