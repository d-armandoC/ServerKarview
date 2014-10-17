/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego C
 */
@Embeddable
public class GeocercaVehiculosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_geocerca")
    private int idGeocerca;
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private int idVehiculo;

    public GeocercaVehiculosPK() {
    }

    public GeocercaVehiculosPK(int idGeocerca, int idVehiculo) {
        this.idGeocerca = idGeocerca;
        this.idVehiculo = idVehiculo;
    }

    public int getIdGeocerca() {
        return idGeocerca;
    }

    public void setIdGeocerca(int idGeocerca) {
        this.idGeocerca = idGeocerca;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idGeocerca;
        hash += (int) idVehiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeocercaVehiculosPK)) {
            return false;
        }
        GeocercaVehiculosPK other = (GeocercaVehiculosPK) object;
        if (this.idGeocerca != other.idGeocerca) {
            return false;
        }
        if (this.idVehiculo != other.idVehiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.GeocercaVehiculosPK[ idGeocerca=" + idGeocerca + ", idVehiculo=" + idVehiculo + " ]";
    }
    
}
