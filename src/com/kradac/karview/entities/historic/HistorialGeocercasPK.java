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
public class HistorialGeocercasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_geocerca")
    private int idGeocerca;
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;

    public HistorialGeocercasPK() {
    }

    public HistorialGeocercasPK(int idGeocerca, int idVehiculo, short estado, Date fechaHoraRegistro) {
        this.idGeocerca = idGeocerca;
        this.idVehiculo = idVehiculo;
        this.estado = estado;
        this.fechaHoraRegistro = fechaHoraRegistro;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idGeocerca;
        hash += (int) idVehiculo;
        hash += (int) estado;
        hash += (fechaHoraRegistro != null ? fechaHoraRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialGeocercasPK)) {
            return false;
        }
        HistorialGeocercasPK other = (HistorialGeocercasPK) object;
        if (this.idGeocerca != other.idGeocerca) {
            return false;
        }
        if (this.idVehiculo != other.idVehiculo) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if ((this.fechaHoraRegistro == null && other.fechaHoraRegistro != null) || (this.fechaHoraRegistro != null && !this.fechaHoraRegistro.equals(other.fechaHoraRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.HistorialGeocercasPK[ idGeocerca=" + idGeocerca + ", idVehiculo=" + idVehiculo + ", estado=" + estado + ", fechaHoraRegistro=" + fechaHoraRegistro + " ]";
    }
    
}
