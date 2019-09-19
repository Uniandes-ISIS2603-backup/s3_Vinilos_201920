/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.entities;

import co.edu.uniandes.csw.vinilos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
    
    
public enum TipoPedido
    {
        INTERCAMBIO, COMPRA
    }
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaGeneracion;
    private boolean aceptado;
    private String observacion;
    private TipoPedido tipo;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

       
    @PodamExclude
    @OneToOne(mappedBy = "pedidoCompra", fetch=FetchType.LAZY)
    private ViniloEntity viniloCompra;

    @PodamExclude
    @OneToOne(mappedBy = "pedidoIntercambio", fetch=FetchType.LAZY)
    private ViniloEntity vinilosIntercambio;
    
    @PodamExclude
    @OneToOne(mappedBy = "pedido", fetch=FetchType.LAZY)
    private EnvioEntity envio;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }
    
    public Date getFechaGeneracion()
    {
        return fechaGeneracion;
    }
    
    public boolean getAceptado()
    {
        return aceptado;
    }
    
    public String getObservacion()
    {
        return observacion;
    }
    
    public TipoPedido getTipo()
    {
        return tipo;
    }
    
     public void setFechaGeneracion(Date pFecha)
    {
         fechaGeneracion = pFecha;
    }
    
    public void setAceptado(boolean pAceptado)
    {
        aceptado = pAceptado;
    }
    
    public void setObservacion(String pObservacion)
    {
        observacion = pObservacion;
    }   
    
    public void setTipo(TipoPedido pTipo)
    {
        tipo = pTipo;
    }

    public ViniloEntity getViniloCompra() {
        return viniloCompra;
    }

    public void setViniloCompra(ViniloEntity viniloCompra) {
        this.viniloCompra = viniloCompra;
    }

    public ViniloEntity getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    public void setVinilosIntercambio(ViniloEntity vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }
    
    
}
