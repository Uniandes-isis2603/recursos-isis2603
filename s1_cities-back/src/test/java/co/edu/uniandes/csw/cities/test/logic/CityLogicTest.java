/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.test.logic;

import co.edu.uniandes.csw.cities.ejb.CityLogic;
import co.edu.uniandes.csw.cities.entities.CitizenEntity;
import co.edu.uniandes.csw.cities.entities.CityEntity;
import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.cities.persistence.CityPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CityLogicTest {

    @Inject
    CityLogic cityLogic;

    @Inject
    private UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    private CitizenEntity citizen = new CitizenEntity();
    
    private List<CityEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeplyment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CityEntity.class.getPackage())
                .addPackage(CitizenEntity.class.getPackage())
                .addPackage(CityPersistence.class.getPackage())
                .addPackage(CityLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void insertData(){
        citizen = factory.manufacturePojo(CitizenEntity.class);
        em.persist(citizen);
    }
    
    @Test
    public void createCityTest() throws BusinessLogicException {
        CityEntity newEntity = factory.manufacturePojo(CityEntity.class);
        CityEntity result = cityLogic.createCity(newEntity);
        Assert.assertNotNull(result);
        
        CityEntity entity = em.find(CityEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }
    
    @Test
    public void prueba() throws BusinessLogicException {
        CityEntity newCity = factory.manufacturePojo(CityEntity.class);
        cityLogic.createCity(newCity);

        CityEntity city = cityLogic.addCitizen(citizen.getId(), newCity.getId());
        Assert.assertNotNull(city);
    }
}
