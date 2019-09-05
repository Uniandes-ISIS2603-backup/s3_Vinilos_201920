/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;
import co.edu.uniandes.csw.vinilos.entities.ViniloEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.ViniloPersistence;
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
        
        vinilo = persistence.create(vinilo);
        return vinilo;
    }
    
}
