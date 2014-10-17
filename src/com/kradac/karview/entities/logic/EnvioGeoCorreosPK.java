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
public class EnvioGeoCorreosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_persona")
    private int idPersona;
    @Basic(optional = false)
    @Column(name = "id_geocerca")
    private int idGeocerca;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;

    public EnvioGeoCorreosPK() {
    }

    public EnvioGeoCorreosPK(int idPersona, int idGeocerca, short estado) {
        this.idPersona = idPersona;
        this.idGeocerca = idGeocerca;
        this.estado = estado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdGeocerca() {
        return idGeocerca;
    }

    public void setIdGeocerca(int idGeocerca) {
        this.idGeocerca = idGeocerca;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idGeocerca;
        hash += (int) estado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioGeoCorreosPK)) {
            return false;
        }
        EnvioGeoCorreosPK other = (EnvioGeoCorreosPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idGeocerca != other.idGeocerca) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.EnvioGeoCorreosPK[ idPersona=" + idPersona + ", idGeocerca=" + idGeocerca + ", estado=" + estado + " ]";
    }
    
}
