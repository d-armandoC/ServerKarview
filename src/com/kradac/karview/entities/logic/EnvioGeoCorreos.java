/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "envio_geo_correos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnvioGeoCorreos.findAll", query = "SELECT e FROM EnvioGeoCorreos e"),
    @NamedQuery(name = "EnvioGeoCorreos.findByIdPersona", query = "SELECT e FROM EnvioGeoCorreos e WHERE e.envioGeoCorreosPK.idPersona = :idPersona"),
    @NamedQuery(name = "EnvioGeoCorreos.findByIdGeocerca", query = "SELECT e FROM EnvioGeoCorreos e WHERE e.envioGeoCorreosPK.idGeocerca = :idGeocerca"),
    @NamedQuery(name = "EnvioGeoCorreos.findByEstado", query = "SELECT e FROM EnvioGeoCorreos e WHERE e.envioGeoCorreosPK.estado = :estado")})
public class EnvioGeoCorreos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvioGeoCorreosPK envioGeoCorreosPK;

    public EnvioGeoCorreos() {
    }

    public EnvioGeoCorreos(EnvioGeoCorreosPK envioGeoCorreosPK) {
        this.envioGeoCorreosPK = envioGeoCorreosPK;
    }

    public EnvioGeoCorreos(int idPersona, int idGeocerca, short estado) {
        this.envioGeoCorreosPK = new EnvioGeoCorreosPK(idPersona, idGeocerca, estado);
    }

    public EnvioGeoCorreosPK getEnvioGeoCorreosPK() {
        return envioGeoCorreosPK;
    }

    public void setEnvioGeoCorreosPK(EnvioGeoCorreosPK envioGeoCorreosPK) {
        this.envioGeoCorreosPK = envioGeoCorreosPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (envioGeoCorreosPK != null ? envioGeoCorreosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioGeoCorreos)) {
            return false;
        }
        EnvioGeoCorreos other = (EnvioGeoCorreos) object;
        if ((this.envioGeoCorreosPK == null && other.envioGeoCorreosPK != null) || (this.envioGeoCorreosPK != null && !this.envioGeoCorreosPK.equals(other.envioGeoCorreosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.EnvioGeoCorreos[ envioGeoCorreosPK=" + envioGeoCorreosPK + " ]";
    }
    
}
