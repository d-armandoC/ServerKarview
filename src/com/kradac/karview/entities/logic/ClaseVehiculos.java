/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "clase_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaseVehiculos.findAll", query = "SELECT c FROM ClaseVehiculos c"),
    @NamedQuery(name = "ClaseVehiculos.findByIdClaseVehiculo", query = "SELECT c FROM ClaseVehiculos c WHERE c.idClaseVehiculo = :idClaseVehiculo"),
    @NamedQuery(name = "ClaseVehiculos.findByClaseVehiculo", query = "SELECT c FROM ClaseVehiculos c WHERE c.claseVehiculo = :claseVehiculo")})
public class ClaseVehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clase_vehiculo")
    private Integer idClaseVehiculo;
    @Basic(optional = false)
    @Column(name = "clase_vehiculo")
    private String claseVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClaseVehiculo")
    private Collection<Vehiculos> vehiculosCollection;

    public ClaseVehiculos() {
    }

    public ClaseVehiculos(Integer idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public ClaseVehiculos(Integer idClaseVehiculo, String claseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
        this.claseVehiculo = claseVehiculo;
    }

    public Integer getIdClaseVehiculo() {
        return idClaseVehiculo;
    }

    public void setIdClaseVehiculo(Integer idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public String getClaseVehiculo() {
        return claseVehiculo;
    }

    public void setClaseVehiculo(String claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    @XmlTransient
    public Collection<Vehiculos> getVehiculosCollection() {
        return vehiculosCollection;
    }

    public void setVehiculosCollection(Collection<Vehiculos> vehiculosCollection) {
        this.vehiculosCollection = vehiculosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClaseVehiculo != null ? idClaseVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseVehiculos)) {
            return false;
        }
        ClaseVehiculos other = (ClaseVehiculos) object;
        if ((this.idClaseVehiculo == null && other.idClaseVehiculo != null) || (this.idClaseVehiculo != null && !this.idClaseVehiculo.equals(other.idClaseVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.ClaseVehiculos[ idClaseVehiculo=" + idClaseVehiculo + " ]";
    }
    
}
