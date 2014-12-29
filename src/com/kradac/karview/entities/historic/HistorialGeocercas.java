/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.historic;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "historial_geocercas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialGeocercas.findAll", query = "SELECT h FROM HistorialGeocercas h"),
    @NamedQuery(name = "HistorialGeocercas.findByIdGeocerca", query = "SELECT h FROM HistorialGeocercas h WHERE h.historialGeocercasPK.idGeocerca = :idGeocerca"),
    @NamedQuery(name = "HistorialGeocercas.findByIdVehiculo", query = "SELECT h FROM HistorialGeocercas h WHERE h.historialGeocercasPK.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "HistorialGeocercas.findByEstado", query = "SELECT h FROM HistorialGeocercas h WHERE h.historialGeocercasPK.estado = :estado"),
    @NamedQuery(name = "HistorialGeocercas.findByFechaHoraRegistro", query = "SELECT h FROM HistorialGeocercas h WHERE h.historialGeocercasPK.fechaHoraRegistro = :fechaHoraRegistro")})
public class HistorialGeocercas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistorialGeocercasPK historialGeocercasPK;

    public HistorialGeocercas() {
    }

    public HistorialGeocercas(HistorialGeocercasPK historialGeocercasPK) {
        this.historialGeocercasPK = historialGeocercasPK;
    }

    public HistorialGeocercas(int idGeocerca, int idVehiculo, short estado, Date fechaHoraRegistro) {
        this.historialGeocercasPK = new HistorialGeocercasPK(idGeocerca, idVehiculo, estado, fechaHoraRegistro);
    }

    public HistorialGeocercasPK getHistorialGeocercasPK() {
        return historialGeocercasPK;
    }

    public void setHistorialGeocercasPK(HistorialGeocercasPK historialGeocercasPK) {
        this.historialGeocercasPK = historialGeocercasPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialGeocercasPK != null ? historialGeocercasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialGeocercas)) {
            return false;
        }
        HistorialGeocercas other = (HistorialGeocercas) object;
        if ((this.historialGeocercasPK == null && other.historialGeocercasPK != null) || (this.historialGeocercasPK != null && !this.historialGeocercasPK.equals(other.historialGeocercasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.HistorialGeocercas[ historialGeocercasPK=" + historialGeocercasPK + " ]";
    }
    
}
