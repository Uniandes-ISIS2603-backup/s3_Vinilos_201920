/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author juan gonzalez
 */
public class GeneroDetailDTO extends GeneroDTO {
     private List<ViniloDTO> vinilos;

    public List<ViniloDTO> getVinilos() {
        return vinilos;
    }

    public void setVinilos(List<ViniloDTO> vinilos) {
        this.vinilos = vinilos;
    }

    /**
     * Constructor por defecto
     */
    public GeneroDetailDTO (){
        
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param generoEntity La entidad de la genero para transformar a DTO.
     */
    public GeneroDetailDTO(GeneroEntity generoEntity) {
        super(generoEntity);
        if (generoEntity != null) {
            if (generoEntity.getVinilos() != null) {
               vinilos = new ArrayList<>();
                for(ViniloEntity entityVinilo: generoEntity.getVinilos()){
                    vinilos.add(new ViniloDTO(entityVinilo));
                }
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la genero para transformar a Entity
     */
    @Override
    public GeneroEntity toEntity() {
        GeneroEntity generoEntity = super.toEntity();
        if(vinilos != null){
            List<ViniloEntity> viniloEntity = new ArrayList();
            for(ViniloDTO dtoVinilo: vinilos){
                viniloEntity.add(dtoVinilo.toEntity());
            }
            generoEntity.setVinilos(viniloEntity);
        }
        return generoEntity;
    }

   

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
