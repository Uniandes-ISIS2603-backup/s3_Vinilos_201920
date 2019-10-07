/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.ViniloPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jhoan Sebastian Díaz Romero
 */
@Stateless
public class ViniloLogic {
    
    @Inject
    private ViniloPersistence persistence; 
    
    public ViniloEntity crearVinilo(ViniloEntity vinilo) throws BusinessLogicException{
        
        if(vinilo.getNombre()==null){
            throw new BusinessLogicException("El nombre del vinilo está vacío");
        }
        
        if(vinilo.getAnio() < 1850) {
            throw new BusinessLogicException("La fecha del vinilo es inválida");
        }
        
        if(vinilo.getPrecio() <= 0) {
            throw new BusinessLogicException("El precio no puede ser menor o igual a 0");
        }
        
        vinilo = persistence.create(vinilo);
        return vinilo;
    }
    
    public List<ViniloEntity> getVinilos(){
        List<ViniloEntity> lista = persistence.findAll();
        return lista;
    }
    
    public ViniloEntity getVinilo(Long vinilosId){
        ViniloEntity viniloEntity = persistence.find(vinilosId);
        if(viniloEntity == null){
            
        }
        return viniloEntity;
    }
    
    public ViniloEntity updateVinilo(Long vinilosId, ViniloEntity viniloEntity){
        ViniloEntity newViniloEntity = persistence.update(viniloEntity);
        return newViniloEntity;
    }
    
    public void deleteVinilo(Long vinilosId){
        persistence.delete(vinilosId);
    }
    
}
