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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Stephania Otalora
 */
@Entity
public class PedidoEntity extends BaseEntity implements Serializable{
    
/*Enumeracion TipoPedio*/    
public enum TipoPedido
    {
        INTERCAMBIO, COMPRA
    }

//Atributos de la clase
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaGeneracion;
    private boolean aceptado;
    private String observacion;
    private TipoPedido tipo;
    
//Relaciones con las demas clases
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

       
    @PodamExclude
    @OneToOne(mappedBy = "pedidoCompra", fetch=FetchType.LAZY)
    private ViniloEntity viniloCompra;

    @PodamExclude
    @OneToMany(mappedBy = "pedidoIntercambio")
    private List<ViniloEntity> vinilosIntercambio = new ArrayList<ViniloEntity>();
    
    @PodamExclude
    @OneToOne(mappedBy = "pedido", fetch=FetchType.LAZY)
    private EnvioEntity envio;
    
    @PodamExclude
    @OneToOne(mappedBy = "pedido", fetch=FetchType.LAZY)
    private EnvioEntity metodoPago;

    /**
     * Devuelve el usuario.
     *
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * Modifica el usuario.
     *
     * @param usuario the usaurio to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

     /**
     * Devuelve el envio.
     *
     * @return the envio
     */
    public EnvioEntity getEnvio() {
        return envio;
    }

    /**
     * Modifica el envio.
     *
     * @param envio the envio to set
     */
    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }
    
     /**
     * Devuelve la fecha de generacion.
     *
     * @return the fechaGeneracion
     */
    public Date getFechaGeneracion()
    {
        return fechaGeneracion;
    }
    
    /**
     * Modifica la fecha de generacion.
     *
     * @param pFecha the fechaGeneracion to set
     */
     public void setFechaGeneracion(Date pFecha)
    {
         fechaGeneracion = pFecha;
    }
    
     /**
     * Devuelve la si es Aceptado.
     *
     * @return the aceptado
     */
    public boolean getAceptado()
    {
        return aceptado;
    }
    
    /**
     * Modifica si es Aceptado el pedido.
     *
     * @param pAceptado the aceptado to set
     */
    public void setAceptado(boolean pAceptado)
    {
        aceptado = pAceptado;
    }
    
    /**
     * Devuelve la Observacion.
     *
     * @return the observacion
     */
    public String getObservacion()
    {
        return observacion;
    }
    
    /**
     * Modifica la observacion.
     *
     * @param pObservacion the observacion to set
     */
    public void setObservacion(String pObservacion)
    {
        observacion = pObservacion;
    } 
    
    /**
     * Devuelve el TipoPedido.
     *
     * @return the tipo
     */
    public TipoPedido getTipo()
    {
        return tipo;
    }
    
    /**
     * Modifica el TipoPedido.
     *
     * @param pTipo the tipo to set
     */
    public void setTipo(TipoPedido pTipo)
    {
        tipo = pTipo;
    }

    /**
     * Devuelve el viniloCompra.
     *
     * @return the viniloCompra
     */
    public ViniloEntity getViniloCompra() {
        return viniloCompra;
    }

    /**
     * Modifica el viniloCompra.
     *
     * @param viniloCompra the viniloCompra to set
     */
    public void setViniloCompra(ViniloEntity viniloCompra) {
        this.viniloCompra = viniloCompra;
    }

    /**
     * Devuelve la lista de vinilosIntercambio.
     *
     * @return the list vinilosIntercambio
     */
    public List<ViniloEntity> getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    /**
     * Modifica el vinilosIntercambio.
     *
     * @param vinilosIntercambio the list vinilosIntercambio to set
     */
    public void setVinilosIntercambio(List<ViniloEntity> vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }
    
    /**
     * Modifica los vinilosIntercambio -Agrega uno-.
     *
     * @param pedido the pedido to add
     */
    public void addViniloIntercambio(ViniloEntity pedido)
    {
        vinilosIntercambio.add(pedido);
    }
        
}
