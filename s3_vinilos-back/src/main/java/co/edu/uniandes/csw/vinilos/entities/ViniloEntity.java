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
    private String nombre;
    private Integer anio;
    private String coleccion;
    private Double precio;
    private String informacion;
    private String estado;
    private Boolean disponible;
    private String imagen;
    
    /**
     * Dueño
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity duenio;
    
    /**
     * VinilosArtista
     */
    @PodamExclude
    @ManyToOne
    private ArtistaEntity vinilosArtista;
    
    /**
     * carrito
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity carrito;
    
    /**
     * lista de deseos
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity deseos;
    
    @PodamExclude
    @OneToMany
    private List<ViniloEntity> vinilosIntercambio = new ArrayList<ViniloEntity>();;

    @PodamExclude
    @ManyToOne
    private GeneroEntity vinilosGenero;

    public void setVinilosGenero(GeneroEntity vinilosGenero) {
        this.vinilosGenero = vinilosGenero;
    }

    public GeneroEntity getVinilosGenero() {
        return vinilosGenero;
    }
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
     * Da el carrito
     * @return carrito
     */
    public UsuarioEntity getCarrito() {
        return carrito;
    }

    /**
     * Da la lista de deseos
     * @return deseos
     */
    public UsuarioEntity getDeseos() {
        return deseos;
    }
    
    /**
     * Cambia el duenio
     * @param duenio the duenio to set
     */
    public void setDuenio(UsuarioEntity duenio) {
        this.duenio = duenio;
    }

    /**
     * Cambia el carrito
     * @param carrito carrito
     */
    public void setCarrito(UsuarioEntity carrito) {
        this.carrito = carrito;
    }

    /**
     * Cambia la lsita de deseos
     * @param deseos deseos
     */
    public void setDeseos(UsuarioEntity deseos) {
        this.deseos = deseos;
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
