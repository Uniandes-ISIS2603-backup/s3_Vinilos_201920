/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class ViniloDetailDTO extends ViniloDTO implements Serializable{
    
    private List<ViniloDTO> vinilosIntercambio;
    
    public ViniloDetailDTO() {
        super();
    }
    
    public ViniloDetailDTO(ViniloEntity viniloEntity) {
        
        super(viniloEntity);
        if(viniloEntity != null){
            if(viniloEntity.getVinilosIntercambio() != null){
                vinilosIntercambio = new ArrayList<>();
                for(ViniloEntity entityVinilo: viniloEntity.getVinilosIntercambio()){
                    vinilosIntercambio.add(new ViniloDTO(entityVinilo));
                }
            }
        }
        
    }
    
    public ViniloEntity toEntity(){
        ViniloEntity entidad = super.toEntity();
        
        if(vinilosIntercambio != null){
            List<ViniloEntity> viniloEntity = new ArrayList<ViniloEntity>();
            for(ViniloDTO dtoVinilo: vinilosIntercambio){
                viniloEntity.add(dtoVinilo.toEntity());
            }
            entidad.setVinilosIntercambio(viniloEntity);
        }
        return entidad;
    }

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
