/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.dtos;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Bogotá
 */
public class ArtistaDetailDTO extends ArtistaDTO {
    
    //private List <GeneroDTO> generos;
    //private List <ViniloDTO> generos;
    
        /*
      /*
    * Esta lista de tipo ViniloDTO contiene los vinilos que estan asociados a una artista
     */
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
    public ArtistaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param artistaEntity La entidad de la artista para transformar a DTO.
     */
    public ArtistaDetailDTO(ArtistaEntity artistaEntity) {
        super(artistaEntity);
        if (artistaEntity != null) {
            if (artistaEntity.getVinilos() != null) {
               vinilos = new ArrayList<>();
                for(ViniloEntity entityVinilo: artistaEntity.getVinilos()){
                    vinilos.add(new ViniloDTO(entityVinilo));
                }
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la artista para transformar a Entity
     */
    @Override
    public ArtistaEntity toEntity() {
        ArtistaEntity artistaEntity = super.toEntity();
        if(vinilos != null){
            List<ViniloEntity> viniloEntity = new ArrayList<ViniloEntity>();
            for(ViniloDTO dtoVinilo: vinilos){
                viniloEntity.add(dtoVinilo.toEntity());
            }
            artistaEntity.setVinilos(viniloEntity);
        }
        return artistaEntity;
    }

   

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}