/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;

import co.edu.uniandes.csw.vinilos.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Manuel Sosa
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    ///////  Atributos 
    
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
    *contraseña
    */
    private String contraseña;
    
    //// RELACIONES
    /**
    *Relacion artistas favoritos
    */
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<ArtistaEntity> artistasFav = new ArrayList<ArtistaEntity>();
    
    /**
    *Relacion generos favoritos
    */
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<GeneroEntity> generosFav = new ArrayList<GeneroEntity>();
    
    /**
    *Relacion carrito
    */
    @PodamExclude
    @OneToMany(mappedBy = "carrito")
    private List<ViniloEntity> carrito = new ArrayList<ViniloEntity>();
    
    /**
    *Relacion lista de deseos
    */
    @PodamExclude
    @OneToMany(mappedBy = "deseos")
    private List<ViniloEntity> listaDeseos = new ArrayList<ViniloEntity>();
    
    /**
    *Relacion pedidos
    */
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<PedidoEntity> pedidos = new ArrayList<PedidoEntity>();
    
    /**
    *Relacion vinilos en venta
    */
    @PodamExclude
    @OneToMany(mappedBy = "duenio")
    private List<ViniloEntity> vinilosVenta = new ArrayList<ViniloEntity>();
    
    /////// Gets
    /**
     *Da el nombre
     * @return nombre
     */
    public String getName(){
        return nombre;
    }
    
    /**
    *Da el correo
    * @return correo
    */
    public String getCorreo(){
        return correo;
    }
    
    /**
    *Da la fecha de nacimiento
    * @return fechaNacim
    */
    public Date getFechaNacim() {
        return fechaNacim;
    }
    
    /**
    *Da el numero de celular
    * @return celular
    */
    public Integer getCelular(){
        return celular;
    }
    
    /**
    *Da el pais
    * @return pais
    */
    public String getPais(){
        return pais;
    }
    
    /**
    *Da la direccion
    * @return direccion
    */
    public String getDireccion(){
        return direccion;
    }
    
    /**
    *Da la contraseña
    * @return contraseña
    */
    public String getContraseña(){
        return contraseña;
    }
        
    /**
    *Da el nombre
    * @return nombre
    */
    public String getNombre() {
        return nombre;
    }
    
    /// Relaciones
    /**
     * Da los vinilos en venta
     * @return vinilosVenta
     */
    public List<ViniloEntity> getVinilosVenta() {
        return vinilosVenta;
    }
    
    /**
    *Da los artistas favoritos
    * @return artistasFav
    */
    public List<ArtistaEntity> getArtistasFav() {
        return artistasFav;
    }

    /*
    *Da los generos favoritos
    @return generosFav
    */
    public List<GeneroEntity> getGenerosFav() {
        return generosFav;
    }

    /**
    *Da el carrito
    * @return carrito
    */
    public List<ViniloEntity> getCarrito() {
        return carrito;
    }
    
    /**
    *Da la lista de deseos
    * @return listaDeseos
    */
    public List<ViniloEntity> getListaDeseos() {
        return listaDeseos;
    }
    
    /**
    *Da los pedidos
    * @return pedidos
    */
    public List<PedidoEntity> getPedidos() {
        return pedidos;
    }
    
    ////// Sets 
    
    /**
     * Cambia el nombre
     * @param pName nombre
     */
    public void setName(String pName){
        this.nombre = pName;
    }
    
    /**
     * Cambia el correo
     * @param pCorreo correo
     */
    public void setCorreo(String pCorreo){
        this.correo = pCorreo;
    }
    
    /**
     * Cambia la fecha de nacimiento
     * @param fecha fecha de nacimiento
     */
    public void setFechaNacim(Date fecha){
        this.fechaNacim = fecha;
    }
    
    /**
     * Cambia el celular
     * @param cel celular
     */
    public void setCelular(Integer cel){
        this.celular = cel;
    }
    
    /**
     * Cambia el pais
     * @param pPais pais
     */
    public void setPais(String pPais){
        this.pais = pPais;
    }
    
    /**
     * Cambia la direccion
     * @param pDireccion direccion
     */
    public void setDireccion(String pDireccion){
        this.direccion = pDireccion;
    }
    
    /**
     * Cambia la contraseña
     * @param pContraseña contraseña
     */
    public void setContraseña(String pContraseña){
        this.contraseña = pContraseña;
    }
    
    /**
     * Cambia el conmbre
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /// Relaciones 
    /**
     * Cambia los artistas favoritos
     * @param artistasFav artistas favoritos
     */
    public void setArtistasFav(List<ArtistaEntity> artistasFav) {
        this.artistasFav = artistasFav;
    }

    /**
     * Cambia los generos favoritos
     * @param genero generos favoritos
     */
    public void setGenerosFav(List<GeneroEntity> genero) {
        this.generosFav = genero;
    }

    /**
     * Cambia el carrito
     * @param carrito carrito
     */
    public void setCarrito(List<ViniloEntity> carrito) {
        this.carrito = carrito;
    }

    /**
     * Cambia la lista de deseos
     * @param listaDeseos lista de deseos
     */
    public void setListaDeseos(List<ViniloEntity> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * Cambia los vinilos en venta
     * @param vinilos vinilos en venta
     */
    public void setVinilosVenta(List<ViniloEntity> vinilos) {
        this.vinilosVenta = vinilos;
    }

    /**
     * Cambia los pedidos
     * @param pedidos pedidos
     */
    public void setPedidos(List<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }
    
    /**
    *constructor
    */
    public UsuarioEntity(){
        
    }

    //// ADDS
    /**
     * Agrega un pedido
     * @param pedido pedido
     */
    public void addPedido(PedidoEntity pedido){
        pedidos.add(pedido);
    }
    
    /**
     * Agrega un vinilo para venta
     * @param vinilo vinilo para venta
     */
    public void addViniloVenta(ViniloEntity vinilo){
        vinilosVenta.add(vinilo);
    }

    /**
     * Agrega un vinilo a la lista de deseos
     * @param vinilo vinilo
     */
    public void addListaDeseos(ViniloEntity vinilo){
        listaDeseos.add(vinilo);
    }
    
    /**
     * Agrega un vinilo al carrito
     * @param vinilo vinilo
     */
    public void addCarrito(ViniloEntity vinilo){
        carrito.add(vinilo);
    }
    
    /**
     * Agrega un artista como favorito
     * @param artista artista
     */
    public void addArtistasFav(ArtistaEntity artista){
        artistasFav.add(artista);
    }
    
    /**
     * Agrega un genero como favorito
     * @param genero genero
     */
    public void addGenerosFav(GeneroEntity genero){
        generosFav.add(genero);
    }
    
}
