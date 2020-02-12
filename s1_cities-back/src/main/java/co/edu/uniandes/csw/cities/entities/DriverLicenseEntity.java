/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.entities;

import co.edu.uniandes.csw.cities.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author estudiante
 */
@Entity
public class DriverLicenseEntity extends BaseEntity implements Serializable {
    private String category;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;
    
    @PodamExclude
    @ManyToOne
    private CitizenEntity citizen;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the citizen
     */
    public CitizenEntity getCitizen() {
        return citizen;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @param citizen the citizen to set
     */
    public void setCitizen(CitizenEntity citizen) {
        this.citizen = citizen;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    
    
}
