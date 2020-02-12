/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.ejb;

import co.edu.uniandes.csw.cities.entities.CitizenEntity;
import co.edu.uniandes.csw.cities.entities.DriverLicenseEntity;
import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.cities.persistence.CitizenPersistence;
import co.edu.uniandes.csw.cities.persistence.DriverLicensePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class DriverLicenseLogic {

    private static final Logger LOGGER = Logger.getLogger(DriverLicenseLogic.class.getName());
    
    @Inject
    private DriverLicensePersistence licensePersistence;
    
    @Inject
    private CitizenPersistence citizenPersistence;

    public DriverLicenseEntity createLicense(Long citizenId, DriverLicenseEntity licenseEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear licencia");
        CitizenEntity citizen = citizenPersistence.find(citizenId);

        //Agregamos el ciudadano a la licencia
        licenseEntity.setCitizen(citizen);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la licencia");
        return licensePersistence.create(licenseEntity);
    }
}
