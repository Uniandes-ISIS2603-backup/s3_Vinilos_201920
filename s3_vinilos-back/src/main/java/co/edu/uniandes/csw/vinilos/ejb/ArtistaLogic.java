/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.ArtistaEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.ArtistaPersistence;
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
}
