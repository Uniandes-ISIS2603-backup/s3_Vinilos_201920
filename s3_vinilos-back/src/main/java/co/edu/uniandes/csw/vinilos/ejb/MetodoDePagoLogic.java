/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vinilos.ejb;

import co.edu.uniandes.csw.vinilos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.vinilos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vinilos.persistence.MetodoDePagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Bogotá
 */
@Stateless
public class MetodoDePagoLogic {
   
    @Inject
    private MetodoDePagoPersistence persistence;
    
    public MetodoDePagoEntity createMetodoDePago(MetodoDePagoEntity metodoDePago) throws BusinessLogicException
    {
      if(metodoDePago.getCuentaPSE()==null){
            throw new BusinessLogicException("El nombre del artista está vacío");
        }   
       metodoDePago = persistence.create(metodoDePago);
       return metodoDePago;  
    }
    
      public List<MetodoDePagoEntity> getMetodoDePagos() 
   {       
       List<MetodoDePagoEntity> metodoDePagos = persistence.findAll();
       return metodoDePagos;
   }
   
   public MetodoDePagoEntity getMetodoDePago(Long metodoDePagoId) 
   {
       MetodoDePagoEntity metodoDePago = persistence.find(metodoDePagoId);
       return metodoDePago;
   }

    
   public MetodoDePagoEntity updateMetodoDePago(Long metodoDePagoId, MetodoDePagoEntity metodoDePagoEntity) throws BusinessLogicException 
   {
        if(metodoDePagoEntity.getCuentaPSE()==null){
            throw new BusinessLogicException("El nombre del artista está vacío");
        }
       MetodoDePagoEntity newEntity = persistence.update(metodoDePagoEntity);
       return newEntity;
   }
   
    public void deleteMetodoDePago(Long metodoDePagoId) throws BusinessLogicException {
       
       persistence.delete(metodoDePagoId);
    } 
}