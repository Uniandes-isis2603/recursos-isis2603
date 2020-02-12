/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.persistence;

import co.edu.uniandes.csw.cities.entities.CityEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class CityPersistence {

    private static final Logger LOGGER = Logger.getLogger(CityPersistence.class.getName());

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Crea una ciudad en la base de datos
     *
     * @param cityEntity objeto city que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CityEntity create(CityEntity cityEntity) {
        LOGGER.log(Level.INFO, "Creando una ciudad nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la ciudad en la base de datos.
            Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(cityEntity);
        LOGGER.log(Level.INFO, "Ciudad creada");
        return cityEntity;
    }

    /**
     * Devuelve todas las ciudades de la base de datos.
     *
     * @return una lista con todas las ciudades que encuentre en la base de
     * datos. "select u from CityEntity u" es como un "select * from
     * CityEntity;" - "SELECT * FROM table_name" en SQL
     */
    public List<CityEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las ciudades");
        // Se crea un query para buscar todas las ciudades en la base de datos.
        TypedQuery query = em.createQuery("select u from CityEntity u", CityEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de ciudades.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna ciudad con el id que se envía de argumento
     *
     * @param cityId: id correspondiente a la ciudad buscada.
     * @return una ciudad.
     */
    public CityEntity find(Long cityId) {
        LOGGER.log(Level.INFO, "Consultando la ciudad con id={0}", cityId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
    el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
    Suponga que es algo similar a "select * from CityEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CityEntity.class, cityId);
    }

    /**
     * Actualiza una ciudad.
     *
     * @param cityEntity: la ciudad que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una ciudad con los cambios aplicados.
     */
    public CityEntity update(CityEntity cityEntity) {
        LOGGER.log(Level.INFO, "Actualizando la ciudad con id={0}", cityEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
    la ciudad con los cambios. Esto es similar a 
    "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(cityEntity);
    }

    /**
     * Borra una ciudad de la base de datos recibiendo como argumento el id de
     * la ciudad
     *
     * @param cityId: id correspondiente a la ciudad a borrar.
     */
    public void delete(Long cityId) {
        LOGGER.log(Level.INFO, "Borrando la ciudad con id={0}", cityId);
        // Se hace uso de mismo método que esta explicado en public CityEntity find(Long id) para obtener la ciudad a borrar.
        CityEntity cityEntity = em.find(CityEntity.class, cityId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "cityEntity", volvemos hacer uso de un método propio del
    EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
    Es similar a "delete from CityEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(cityEntity);
    }

    public CityEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando ciudades por nombre ", name);
        // Se crea un query para buscar ciudades con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CityEntity e where e.name = :name", CityEntity.class);
        // Se remplaza el placeholder ":isbn" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<CityEntity> sameName = query.getResultList();
        CityEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar ciudades por nombre ", name);
        return result;
    }
}