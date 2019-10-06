/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import java.util.List;

/**
 *
 * @author Estudiante
 */
public class ViniloDetail {
    
    private List<ViniloDTO> vinilosIntercambio;

    /**
     * @return the vinilosIntercambio
     */
    public List<ViniloDTO> getVinilosIntercambio() {
        return vinilosIntercambio;
    }

    /**
     * @param vinilosIntercambio the vinilosIntercambio to set
     */
    public void setVinilosIntercambio(List<ViniloDTO> vinilosIntercambio) {
        this.vinilosIntercambio = vinilosIntercambio;
    }
    
    
    
    
}
