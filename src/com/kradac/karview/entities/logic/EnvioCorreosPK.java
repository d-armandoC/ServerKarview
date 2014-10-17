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
public class EnvioCorreosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_persona")
    private int idPersona;
    @Basic(optional = false)
    @Column(name = "id_sky_evento")
    private int idSkyEvento;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private int idEmpresa;

    public EnvioCorreosPK() {
    }

    public EnvioCorreosPK(int idPersona, int idSkyEvento, int idEmpresa) {
        this.idPersona = idPersona;
        this.idSkyEvento = idSkyEvento;
        this.idEmpresa = idEmpresa;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdSkyEvento() {
        return idSkyEvento;
    }

    public void setIdSkyEvento(int idSkyEvento) {
        this.idSkyEvento = idSkyEvento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idSkyEvento;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioCorreosPK)) {
            return false;
        }
        EnvioCorreosPK other = (EnvioCorreosPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idSkyEvento != other.idSkyEvento) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.EnvioCorreosPK[ idPersona=" + idPersona + ", idSkyEvento=" + idSkyEvento + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
