/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.persistence;

import co.edu.uniandes.csw.cities.entities.CitizenEntity;
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
public class CitizenPersistence {
    private static final Logger LOGGER = Logger.getLogger(CitizenPersistence.class.getName());
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public CitizenEntity create(CitizenEntity citizenEntity) {
        em.persist(citizenEntity);
        return citizenEntity;
    }
    
    /**
     * Busca si hay alguna ciudad con el id que se envía de argumento
     *
     * @param cityId: id correspondiente a la ciudad buscada.
     * @return una ciudad.
     */
    public CitizenEntity find(Long citizenId) {
        LOGGER.log(Level.INFO, "Consultando el ciudadano con id={0}", citizenId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
    el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
    Suponga que es algo similar a "select * from CityEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CitizenEntity.class, citizenId);
    }
}
