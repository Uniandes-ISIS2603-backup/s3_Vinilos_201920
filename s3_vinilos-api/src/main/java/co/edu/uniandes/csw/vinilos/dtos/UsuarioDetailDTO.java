/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.PedidoEntity;
import co.edu.uniandes.csw.vinilos.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Sosa
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    private List<ViniloDTO> carrito;
    
    private List<ViniloDTO> listaDeseos;
    
    private List<ArtistaDTO> artistasFav;
    
    private List<GeneroDTO> generoFav;
    
    private List<PedidoDTO> pedidos;

    public void setCarrito(List<ViniloDTO> carrito) {
        this.carrito = carrito;
    }

    public void setListaDeseos(List<ViniloDTO> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    public void setArtistasFav(List<ArtistaDTO> artistasFav) {
        this.artistasFav = artistasFav;
    }

    public void setGeneroFav(List<GeneroDTO> generoFav) {
        this.generoFav = generoFav;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ViniloDTO> getCarrito() {
        return carrito;
    }

    public List<ViniloDTO> getListaDeseos() {
        return listaDeseos;
    }

    public List<ArtistaDTO> getArtistasFav() {
        return artistasFav;
    }

    public List<GeneroDTO> getGeneroFav() {
        return generoFav;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }
    
    public UsuarioDetailDTO(){
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuario){
        super(usuario);
        if(usuario != null){
            if(usuario.getPedidos() != null){
                pedidos = new ArrayList<>();
                for(PedidoEntity pedido: usuario.getPedidos()){
                    pedidos.add(new PedidoDTO(pedido));
                }
            }
            
        }
    }
}