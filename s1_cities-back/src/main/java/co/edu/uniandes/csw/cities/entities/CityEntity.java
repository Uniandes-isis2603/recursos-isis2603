/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.cities.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CityEntity extends BaseEntity implements Serializable{
    
    private String name;
    
    @PodamExclude
    @OneToOne
    private CitizenEntity mayor;
    /**
     * @return the mayor
     */
    public CitizenEntity getMayor() {
        return mayor;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param mayor the mayor to set
     */
    public void setMayor(CitizenEntity mayor) {
        this.mayor = mayor;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
   
}
