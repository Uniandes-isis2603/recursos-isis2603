/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.ejb;

import co.edu.uniandes.csw.cities.entities.CitizenEntity;
import co.edu.uniandes.csw.cities.entities.CityEntity;
import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.cities.persistence.CitizenPersistence;
import co.edu.uniandes.csw.cities.persistence.CityPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class CityLogic {

    private static final Logger LOGGER = Logger.getLogger(CityLogic.class.getName());
    
    @Inject
    private CityPersistence cityPersistence;
    
    @Inject
    private CitizenPersistence citizenPersistence;

    public CityEntity createCity(CityEntity cityEntity) throws BusinessLogicException {

        if (cityPersistence.findByName(cityEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una ciudad con el nombre " + cityEntity.getName());
        }
        CityEntity newCityEntity = cityPersistence.create(cityEntity);
        return newCityEntity;
    }

    public CityEntity addCitizen(Long citizenId, Long cityId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una ciudad al ciudadano con  id = {0}", citizenId);
        CityEntity cityEntity = cityPersistence.find(cityId);
        CitizenEntity citizenEntity = citizenPersistence.find(citizenId);
        cityEntity.setMayor(citizenEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una ciudad al ciudadano con id = {0}", citizenId);
        return cityPersistence.find(cityId);
    }

    public List<CityEntity> getCities() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las ciudades");
        List<CityEntity> lista = cityPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las ciudades");
        return lista;
    }

    public CityEntity getCity(Long cityId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la ciudad con id = {0}", cityId);
        CityEntity cityEntity = cityPersistence.find(cityId);
        if (cityEntity == null) {
            LOGGER.log(Level.SEVERE, "La ciudad con el id = {0} no existe", cityId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la ciudad con id = {0}", cityId);
        return cityEntity;
    }

    public CityEntity updateCity(Long cityId, CityEntity cityEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ciudad con id = {0}", cityId);
        CityEntity newCityEntity = cityPersistence.update(cityEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ciudad con id = {0}", cityId);
        return newCityEntity;
    }

    public void deleteCity(Long cityId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la ciudad con id = {0}", cityId);
        cityPersistence.delete(cityId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la ciudad con id = {0}", cityId);
    }

}
