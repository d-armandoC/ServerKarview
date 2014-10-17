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
public class GeocercaPuntosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_geocerca")
    private int idGeocerca;
    @Basic(optional = false)
    @Column(name = "orden")
    private int orden;

    public GeocercaPuntosPK() {
    }

    public GeocercaPuntosPK(int idGeocerca, int orden) {
        this.idGeocerca = idGeocerca;
        this.orden = orden;
    }

    public int getIdGeocerca() {
        return idGeocerca;
    }

    public void setIdGeocerca(int idGeocerca) {
        this.idGeocerca = idGeocerca;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idGeocerca;
        hash += (int) orden;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeocercaPuntosPK)) {
            return false;
        }
        GeocercaPuntosPK other = (GeocercaPuntosPK) object;
        if (this.idGeocerca != other.idGeocerca) {
            return false;
        }
        if (this.orden != other.orden) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.GeocercaPuntosPK[ idGeocerca=" + idGeocerca + ", orden=" + orden + " ]";
    }
    
}
