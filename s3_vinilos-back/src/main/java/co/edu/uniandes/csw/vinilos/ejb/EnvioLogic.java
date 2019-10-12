/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.EnvioEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.EnvioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author juan gonzalez
 */
@Stateless
public class EnvioLogic {
     @Inject
    private EnvioPersistence persistence;
    
    public EnvioEntity createEnvio(EnvioEntity envio) throws BusinessLogicException
    {
      if(envio.getFecha()==null){
            throw new BusinessLogicException("La fecha no existe");
        }
       envio = persistence.create(envio);
       return envio;  
    }
    
      public List<EnvioEntity> getEnvio() 
   {       
       List<EnvioEntity> envio = persistence.findAll();
       return envio;
   }
   
   public EnvioEntity getEnvio(Long envioId) 
   {
       EnvioEntity envio = persistence.find(envioId);
       return envio;
   }

    
   public EnvioEntity updateEnvio( EnvioEntity envioEntity) throws BusinessLogicException 
   {
       if(null==envioEntity.getFecha()){
            throw new BusinessLogicException("La fehca no existe");
        }
       EnvioEntity newEntity = persistence.update(envioEntity);
       return newEntity;
   }
   
    public void deleteEnvio(Long envioId) throws BusinessLogicException {
       
       persistence.delete(envioId);
    } 
}
