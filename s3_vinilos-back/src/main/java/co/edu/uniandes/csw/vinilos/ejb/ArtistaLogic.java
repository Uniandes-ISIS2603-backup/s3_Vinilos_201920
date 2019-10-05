/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.ArtistaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Bogotá
 */
@Stateless
public class ArtistaLogic {
   
    @Inject
    private ArtistaPersistence persistence;
    
    public ArtistaEntity createArtista(ArtistaEntity artista) throws BusinessLogicException
    {
      if(artista.getName()==null){
            throw new BusinessLogicException("El nombre del artista está vacío");
        }
       artista = persistence.create(artista);
       return artista;  
    }
    
      public List<ArtistaEntity> getArtistas() 
   {       
       List<ArtistaEntity> artistas = persistence.findAll();
       return artistas;
   }
   
   public ArtistaEntity getArtista(Long artistaId) 
   {
       ArtistaEntity artista = persistence.find(artistaId);
       return artista;
   }

    
   public ArtistaEntity updateArtista(Long artistaId, ArtistaEntity artistaEntity) throws BusinessLogicException 
   {
       if(artistaEntity.getName()==null){
            throw new BusinessLogicException("El nombre del artista está vacío");
        }
       ArtistaEntity newEntity = persistence.update(artistaEntity);
       return newEntity;
   }
   
    public void deleteArtista(Long artistaId) throws BusinessLogicException {
       
       persistence.delete(artistaId);
    } 
}
