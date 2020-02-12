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

    public void insertData() {
        citizen = factory.manufacturePojo(CitizenEntity.class);
        em.persist(citizen);

        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CityEntity entity = factory.manufacturePojo(CityEntity.class);

            em.persist(entity);
            data.add(entity);
        }
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
    public void getCitiesTest() {
        List<CityEntity> list = cityLogic.getCities();
        Assert.assertEquals(data.size(), list.size());
        for (CityEntity entity : list) {
            boolean found = false;
            for (CityEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCity() {
        CityEntity entity = data.get(0);
        CityEntity resultEntity = cityLogic.getCity(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }
    
    @Test
    public void updateCityTest() {
        CityEntity entity = data.get(0);
        CityEntity pojoEntity = factory.manufacturePojo(CityEntity.class);

        pojoEntity.setId(entity.getId());

        cityLogic.updateCity(pojoEntity.getId(), pojoEntity);

        CityEntity resp = em.find(CityEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteCityTest() throws BusinessLogicException {
        CityEntity entity = data.get(0);
        cityLogic.deleteCity(entity.getId());
        CityEntity deleted = em.find(CityEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void addCitizen() throws BusinessLogicException {
        CityEntity newCity = factory.manufacturePojo(CityEntity.class);
        cityLogic.createCity(newCity);

        CityEntity city = cityLogic.addCitizen(citizen.getId(), newCity.getId());
        Assert.assertNotNull(city);
        
        CitizenEntity newCitizen = cityLogic.getCity(newCity.getId()).getMayor();
        
        Assert.assertEquals(citizen.getId(), newCitizen.getId());
        Assert.assertEquals(citizen.getName(), newCitizen.getName());
        Assert.assertEquals(citizen.getAddress(), newCitizen.getAddress());
    }
}
