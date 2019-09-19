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
import javax.persistence.ManyToOne;
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

     /*@PodamExclude
    @OneToOne(mappedBy = "vinilosCompra", fetch=FetchType.LAZY)
    private ViniloEntity vinilosCompra;
    
    @PodamExclude
    @OneToOne(mappedBy = "vinilosIntercambio", fetch=FetchType.LAZY)
    private ViniloEntity vinilosIntercambio;
    
    @PodamExclude
    @OneToOne(mappedBy = "envio", fetch=FetchType.LAZY)
    private EnvioEntity envio;*/

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /*public ViniloEntity getVinilosCompra() {
        return vinilosCompra;
    }

    public void setVinilosCompra(ViniloEntity vinilosCompra) {
        this.vinilosCompra = vinilosCompra;
    }

    public ViniloEntity getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    public void setVinilosIntercambio(ViniloEntity vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }

    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }*/
    
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
    
}
