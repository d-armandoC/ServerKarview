/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "estado_geocerca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoGeocerca.findAll", query = "SELECT e FROM EstadoGeocerca e"),
    @NamedQuery(name = "EstadoGeocerca.findByIdVehiculo", query = "SELECT e FROM EstadoGeocerca e WHERE e.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "EstadoGeocerca.findByEstado", query = "SELECT e FROM EstadoGeocerca e WHERE e.estado = :estado")})
public class EstadoGeocerca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_Vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;

    public EstadoGeocerca() {
    }

    public EstadoGeocerca(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public EstadoGeocerca(Integer idVehiculo, int estado) {
        this.idVehiculo = idVehiculo;
        this.estado = estado;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoGeocerca)) {
            return false;
        }
        EstadoGeocerca other = (EstadoGeocerca) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.logic.EstadoGeocerca[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
