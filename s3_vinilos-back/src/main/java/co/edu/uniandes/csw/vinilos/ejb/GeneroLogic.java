/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;
import co.edu.uniandes.csw.vinilos.entities.GeneroEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.GeneroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author juan gonzalez
 */
@Stateless
public class GeneroLogic {
    @Inject
    private GeneroPersistence generos;
    
     public GeneroEntity createGenero(GeneroEntity genero) throws BusinessLogicException
    {
      if(genero.getNombre()==null){
            throw new BusinessLogicException("El nombre del genero está vacío");
        }
       genero = generos.create(genero);
       return genero;  
    }
     
       public List<GeneroEntity> getBooks() 
       {
        
        List<GeneroEntity> books = generos.findAll();
        
        return books;
    }
     
   public GeneroEntity getGenero(Long generoId) 
   {
       GeneroEntity genero = generos.find(generoId);
       return genero;
   }

    
   public GeneroEntity updateGenero( Long generoId,GeneroEntity generoEntity) throws BusinessLogicException 
   {
       if(generoEntity.getNombre()==null){
            throw new BusinessLogicException("El nombre del genero está vacío");
        }
       GeneroEntity newEntity = generos.update(generoEntity);
       return newEntity;
   }
   
    public void deleteGenero(Long generoId) throws BusinessLogicException {
       
       generos.delete(generoId);
    } 
}
