/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "geocerca_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeocercaPuntos.findAll", query = "SELECT g FROM GeocercaPuntos g"),
    @NamedQuery(name = "GeocercaPuntos.findByIdGeocerca", query = "SELECT g FROM GeocercaPuntos g WHERE g.geocercaPuntosPK.idGeocerca = :idGeocerca"),
    @NamedQuery(name = "GeocercaPuntos.findByOrden", query = "SELECT g FROM GeocercaPuntos g WHERE g.geocercaPuntosPK.orden = :orden"),
    @NamedQuery(name = "GeocercaPuntos.findByLatitud", query = "SELECT g FROM GeocercaPuntos g WHERE g.latitud = :latitud"),
    @NamedQuery(name = "GeocercaPuntos.findByLongitud", query = "SELECT g FROM GeocercaPuntos g WHERE g.longitud = :longitud")})
public class GeocercaPuntos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeocercaPuntosPK geocercaPuntosPK;
    @Basic(optional = false)
    @Column(name = "latitud")
    private double latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private double longitud;
    @JoinColumn(name = "id_geocerca", referencedColumnName = "id_geocerca", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Geocercas geocercas;

    public GeocercaPuntos() {
    }

    public GeocercaPuntos(GeocercaPuntosPK geocercaPuntosPK) {
        this.geocercaPuntosPK = geocercaPuntosPK;
    }

    public GeocercaPuntos(GeocercaPuntosPK geocercaPuntosPK, double latitud, double longitud) {
        this.geocercaPuntosPK = geocercaPuntosPK;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public GeocercaPuntos(int idGeocerca, int orden) {
        this.geocercaPuntosPK = new GeocercaPuntosPK(idGeocerca, orden);
    }

    public GeocercaPuntosPK getGeocercaPuntosPK() {
        return geocercaPuntosPK;
    }

    public void setGeocercaPuntosPK(GeocercaPuntosPK geocercaPuntosPK) {
        this.geocercaPuntosPK = geocercaPuntosPK;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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
        hash += (geocercaPuntosPK != null ? geocercaPuntosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeocercaPuntos)) {
            return false;
        }
        GeocercaPuntos other = (GeocercaPuntos) object;
        if ((this.geocercaPuntosPK == null && other.geocercaPuntosPK != null) || (this.geocercaPuntosPK != null && !this.geocercaPuntosPK.equals(other.geocercaPuntosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.GeocercaPuntos[ geocercaPuntosPK=" + geocercaPuntosPK + " ]";
    }
    
}
