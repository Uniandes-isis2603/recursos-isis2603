/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.test.persistence;

import co.edu.uniandes.csw.cities.entities.CityEntity;
import co.edu.uniandes.csw.cities.persistence.CitizenPersistence;
import co.edu.uniandes.csw.cities.persistence.CityPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CityPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeplyment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CityEntity.class.getPackage())
                .addPackage(CityPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
             
    }
    
    @Inject
    CityPersistence cp;
    
    @Inject
    CitizenPersistence ctp;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CityEntity city = factory.manufacturePojo(CityEntity.class);
        CityEntity resultCity = cp.create(city);
        Assert.assertNotNull(resultCity);
    }
}
