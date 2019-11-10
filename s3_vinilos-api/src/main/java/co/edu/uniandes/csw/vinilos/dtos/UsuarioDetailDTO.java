/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Sosa
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    /**
     * carrito
     */
    private List<ViniloDTO> carrito;
    
    /**
     * lista de deseos
     */
    private List<ViniloDTO> listaDeseos;
    
    /**
     * artistas favoritos
     */
    private List<ArtistaDTO> artistasFav;
    
    /**
     * generos favoritos
     */
    private List<GeneroDTO> generosFav;
    
    /**
     * pedidos
     */
    private List<PedidoDTO> pedidos;

    /**
     * vinilos en venta
     */
    private List<ViniloDTO> vinilosVenta;
    
    //////SETS
    /**
     * Cambia el carrito
     * @param carrito carrito
     */
    public void setCarrito(List<ViniloDTO> carrito) {
        this.carrito = carrito;
    }

    /**
     * Cambia la lista de deseos
     * @param listaDeseos lista de deseos
     */
    public void setListaDeseos(List<ViniloDTO> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * Cambia los artistas favoritos
     * @param artistasFav artistas favoritos
     */
    public void setArtistasFav(List<ArtistaDTO> artistasFav) {
        this.artistasFav = artistasFav;
    }

    /**
     * Cambia los generos favoritos
     * @param generosFav generos favoritos
     */
    public void setGenerosFav(List<GeneroDTO> generosFav) {
        this.generosFav = generosFav;
    }

    /**
     * Cambia los pedidos
     * @param pedidos pedidos
     */
    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * Cambia los vinilos en venta
     * @param vinilosVenta vinilos para vender
     */
    public void setVinilosVenta(List<ViniloDTO> vinilosVenta) {    
        this.vinilosVenta = vinilosVenta;
    }

    //////////GETS
    /**
     * Da el carrito
     * @return carrito
     */
    public List<ViniloDTO> getCarrito() {
        return carrito;
    }

    /**
     * Da la lista de deseos
     * @return listaDeseos
     */
    public List<ViniloDTO> getListaDeseos() {
        return listaDeseos;
    }

    /**
     * Da la lista de artistas favoritos
     * @return artistasFav
     */
    public List<ArtistaDTO> getArtistasFav() {
        return artistasFav;
    }

    /**
     * Da la lsita de generos favoritos
     * @return generosFav
     */
    public List<GeneroDTO> getGenerosFav() {
        return generosFav;
    }

    /**
     * Da los pedidos
     * @return pedidos
     */
    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    /**
     * Da los vinilos para vender
     * @return vinilosVenta
     */
    public List<ViniloDTO> getVinilosVenta() {
        return vinilosVenta;
    }
        
    /**
     * Constructor
     */
    public UsuarioDetailDTO(){
    }
    
    /**
     * Constructor
     * @param usuario usuario 
     */
    public UsuarioDetailDTO(UsuarioEntity usuario){
        super(usuario);
        if(usuario != null){
            if(usuario.getPedidos() != null){
                pedidos = new ArrayList<>();
                for(PedidoEntity pedido: usuario.getPedidos()){
                    pedidos.add(new PedidoDTO(pedido));
                }
            }
            if(usuario.getArtistasFav() != null){
                artistasFav = new ArrayList<>();
                for(ArtistaEntity artista: usuario.getArtistasFav()){
                    artistasFav.add(new ArtistaDTO(artista));
                }
            }
            if(usuario.getGenerosFav() != null){
                generosFav = new ArrayList<>();
                for(GeneroEntity genero: usuario.getGenerosFav()){
                    generosFav.add(new GeneroDTO(genero));
                }
            }
            if(usuario.getListaDeseos() != null){
                listaDeseos = new ArrayList<>();
                for(ViniloEntity deseos: usuario.getListaDeseos()){
                    listaDeseos.add(new ViniloDTO(deseos));
                }
            }
            if(usuario.getCarrito() != null){
                carrito = new ArrayList<>();
                for(ViniloEntity carritos: usuario.getCarrito()){
                    carrito.add(new ViniloDTO(carritos));
                }
            }
            if(usuario.getVinilosVenta() != null){
                vinilosVenta = new ArrayList<>();
                for(ViniloEntity venta: usuario.getVinilosVenta()){
                    vinilosVenta.add(new ViniloDTO(venta));
                }
            }
        }
    }
}