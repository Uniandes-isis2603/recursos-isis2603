/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.test.logic;

import co.edu.uniandes.csw.cities.ejb.DriverLicenseLogic;
import co.edu.uniandes.csw.cities.entities.CitizenEntity;
import co.edu.uniandes.csw.cities.entities.DriverLicenseEntity;
import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.cities.persistence.DriverLicensePersistence;
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
public class DriverLicenseLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DriverLicenseLogic licenseLogic;
    
    @Inject
    private UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em;
    
    private CitizenEntity citizen = new CitizenEntity();
    
    @Deployment
    public static JavaArchive createDeplyment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DriverLicenseEntity.class.getPackage())
                .addPackage(DriverLicensePersistence.class.getPackage())
                .addPackage(DriverLicenseLogic.class.getPackage())
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
    
    private void insertData() {
        citizen = factory.manufacturePojo(CitizenEntity.class);
        em.persist(citizen);
    }
    
    @Test
    public void createLicenseTest() throws BusinessLogicException {
        DriverLicenseEntity newEntity = factory.manufacturePojo(DriverLicenseEntity.class);
        DriverLicenseEntity result = licenseLogic.createLicense(citizen.getId(), newEntity);
        Assert.assertNotNull(result);
        
        DriverLicenseEntity entity = em.find(DriverLicenseEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCategory(), entity.getCategory());
        Assert.assertEquals(newEntity.getDueDate(), entity.getDueDate());
    }
}
