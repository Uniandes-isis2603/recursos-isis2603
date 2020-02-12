/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.persistence;

import co.edu.uniandes.csw.cities.entities.DriverLicenseEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class DriverLicensePersistence {

    private static final Logger LOGGER = Logger.getLogger(CityPersistence.class.getName());

    @PersistenceContext(unitName = "citiesPU")
    protected EntityManager em;

    public DriverLicenseEntity create(DriverLicenseEntity licenseEntity) {
        LOGGER.log(Level.INFO, "Creando una licencia nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la ciudad en la base de datos.
            Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(licenseEntity);
        LOGGER.log(Level.INFO, "Licencia creada");
        return licenseEntity;
    }

}
