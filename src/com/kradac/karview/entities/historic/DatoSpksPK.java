/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.historic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego C
 */
@Embeddable
public class DatoSpksPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private int idEquipo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "id_sky_evento")
    private int idSkyEvento;

    public DatoSpksPK() {
    }

    public DatoSpksPK(int idEquipo, Date fecha, Date hora, int idSkyEvento) {
        this.idEquipo = idEquipo;
        this.fecha = fecha;
        this.hora = hora;
        this.idSkyEvento = idSkyEvento;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getIdSkyEvento() {
        return idSkyEvento;
    }

    public void setIdSkyEvento(int idSkyEvento) {
        this.idSkyEvento = idSkyEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEquipo;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (hora != null ? hora.hashCode() : 0);
        hash += (int) idSkyEvento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoSpksPK)) {
            return false;
        }
        DatoSpksPK other = (DatoSpksPK) object;
        if (this.idEquipo != other.idEquipo) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.hora == null && other.hora != null) || (this.hora != null && !this.hora.equals(other.hora))) {
            return false;
        }
        if (this.idSkyEvento != other.idSkyEvento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.DatoSpksPK[ idEquipo=" + idEquipo + ", fecha=" + fecha + ", hora=" + hora + ", idSkyEvento=" + idSkyEvento + " ]";
    }
    
}
