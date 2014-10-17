/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "geocerca_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeocercaVehiculos.findAll", query = "SELECT g FROM GeocercaVehiculos g"),
    @NamedQuery(name = "GeocercaVehiculos.findByIdGeocerca", query = "SELECT g FROM GeocercaVehiculos g WHERE g.geocercaVehiculosPK.idGeocerca = :idGeocerca"),
    @NamedQuery(name = "GeocercaVehiculos.findByIdVehiculo", query = "SELECT g FROM GeocercaVehiculos g WHERE g.geocercaVehiculosPK.idVehiculo = :idVehiculo")})
public class GeocercaVehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeocercaVehiculosPK geocercaVehiculosPK;
    @JoinColumn(name = "id_geocerca", referencedColumnName = "id_geocerca", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Geocercas geocercas;

    public GeocercaVehiculos() {
    }

    public GeocercaVehiculos(GeocercaVehiculosPK geocercaVehiculosPK) {
        this.geocercaVehiculosPK = geocercaVehiculosPK;
    }

    public GeocercaVehiculos(int idGeocerca, int idVehiculo) {
        this.geocercaVehiculosPK = new GeocercaVehiculosPK(idGeocerca, idVehiculo);
    }

    public GeocercaVehiculosPK getGeocercaVehiculosPK() {
        return geocercaVehiculosPK;
    }

    public void setGeocercaVehiculosPK(GeocercaVehiculosPK geocercaVehiculosPK) {
        this.geocercaVehiculosPK = geocercaVehiculosPK;
    }

    public Geocercas getGeocercas() {
        return geocercas;
    }

    public void setGeocercas(Geocercas geocercas) {
        this.geocercas = geocercas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geocercaVehiculosPK != null ? geocercaVehiculosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeocercaVehiculos)) {
            return false;
        }
        GeocercaVehiculos other = (GeocercaVehiculos) object;
        if ((this.geocercaVehiculosPK == null && other.geocercaVehiculosPK != null) || (this.geocercaVehiculosPK != null && !this.geocercaVehiculosPK.equals(other.geocercaVehiculosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.GeocercaVehiculos[ geocercaVehiculosPK=" + geocercaVehiculosPK + " ]";
    }
    
}
