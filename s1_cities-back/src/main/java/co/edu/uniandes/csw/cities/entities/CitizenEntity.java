/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CitizenEntity extends BaseEntity implements Serializable {
    private String name;
    
    private String address;

    @PodamExclude  
    @OneToOne(
        mappedBy = "mayor",
    	fetch = FetchType.LAZY
    )
    private CityEntity manage;
    
    @PodamExclude
    @OneToMany(
        mappedBy = "citizen",
    	cascade = CascadeType.PERSIST,
    	fetch = FetchType.EAGER,
    	orphanRemoval = true
    )
    private List<DriverLicenseEntity> driverLicenses = 
        new ArrayList<DriverLicenseEntity>();

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the manage
     */
    public CityEntity getManage() {
        return manage;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param manage the manage to set
     */
    public void setManage(CityEntity manage) {
        this.manage = manage;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the driverLicenses
     */
    public List<DriverLicenseEntity> getDriverLicenses() {
        return driverLicenses;
    }

    /**
     * @param driverLicenses the driverLicenses to set
     */
    public void setDriverLicenses(List<DriverLicenseEntity> driverLicenses) {
        this.driverLicenses = driverLicenses;
    }
    
    
}

