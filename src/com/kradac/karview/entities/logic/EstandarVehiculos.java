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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "estandar_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstandarVehiculos.findAll", query = "SELECT e FROM EstandarVehiculos e"),
    @NamedQuery(name = "EstandarVehiculos.findByIdEstandarVehiculo", query = "SELECT e FROM EstandarVehiculos e WHERE e.idEstandarVehiculo = :idEstandarVehiculo"),
    @NamedQuery(name = "EstandarVehiculos.findByEstandarVehiculo", query = "SELECT e FROM EstandarVehiculos e WHERE e.estandarVehiculo = :estandarVehiculo"),
    @NamedQuery(name = "EstandarVehiculos.findByTiempo", query = "SELECT e FROM EstandarVehiculos e WHERE e.tiempo = :tiempo"),
    @NamedQuery(name = "EstandarVehiculos.findByKilometro", query = "SELECT e FROM EstandarVehiculos e WHERE e.kilometro = :kilometro")})
public class EstandarVehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estandar_vehiculo")
    private Integer idEstandarVehiculo;
    @Basic(optional = false)
    @Column(name = "estandar_vehiculo")
    private String estandarVehiculo;
    @Basic(optional = false)
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @Column(name = "kilometro")
    private int kilometro;

    public EstandarVehiculos() {
    }

    public EstandarVehiculos(Integer idEstandarVehiculo) {
        this.idEstandarVehiculo = idEstandarVehiculo;
    }

    public EstandarVehiculos(Integer idEstandarVehiculo, String estandarVehiculo, int tiempo, int kilometro) {
        this.idEstandarVehiculo = idEstandarVehiculo;
        this.estandarVehiculo = estandarVehiculo;
        this.tiempo = tiempo;
        this.kilometro = kilometro;
    }

    public Integer getIdEstandarVehiculo() {
        return idEstandarVehiculo;
    }

    public void setIdEstandarVehiculo(Integer idEstandarVehiculo) {
        this.idEstandarVehiculo = idEstandarVehiculo;
    }

    public String getEstandarVehiculo() {
        return estandarVehiculo;
    }

    public void setEstandarVehiculo(String estandarVehiculo) {
        this.estandarVehiculo = estandarVehiculo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getKilometro() {
        return kilometro;
    }

    public void setKilometro(int kilometro) {
        this.kilometro = kilometro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstandarVehiculo != null ? idEstandarVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstandarVehiculos)) {
            return false;
        }
        EstandarVehiculos other = (EstandarVehiculos) object;
        if ((this.idEstandarVehiculo == null && other.idEstandarVehiculo != null) || (this.idEstandarVehiculo != null && !this.idEstandarVehiculo.equals(other.idEstandarVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.EstandarVehiculos[ idEstandarVehiculo=" + idEstandarVehiculo + " ]";
    }
    
}
