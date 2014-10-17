/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "geocercas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geocercas.findAll", query = "SELECT g FROM Geocercas g"),
    @NamedQuery(name = "Geocercas.findByIdGeocerca", query = "SELECT g FROM Geocercas g WHERE g.idGeocerca = :idGeocerca"),
    @NamedQuery(name = "Geocercas.findByGeocerca", query = "SELECT g FROM Geocercas g WHERE g.geocerca = :geocerca"),
    @NamedQuery(name = "Geocercas.findByDescripcion", query = "SELECT g FROM Geocercas g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "Geocercas.findByArea", query = "SELECT g FROM Geocercas g WHERE g.area = :area"),
    @NamedQuery(name = "Geocercas.findByFechaHoraRegistro", query = "SELECT g FROM Geocercas g WHERE g.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Geocercas.findByFechaHoraModificacion", query = "SELECT g FROM Geocercas g WHERE g.fechaHoraModificacion = :fechaHoraModificacion"),
    @NamedQuery(name = "Geocercas.findByIdUsuarioModificaion", query = "SELECT g FROM Geocercas g WHERE g.idUsuarioModificaion = :idUsuarioModificaion")})
public class Geocercas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_geocerca")
    private Integer idGeocerca;
    @Basic(optional = false)
    @Column(name = "geocerca")
    private String geocerca;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "area")
    private double area;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @Basic(optional = false)
    @Column(name = "id_usuario_modificaion")
    private int idUsuarioModificaion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geocercas")
    private Collection<GeocercaPuntos> geocercaPuntosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geocercas")
    private Collection<GeocercaVehiculos> geocercaVehiculosCollection;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresas idEmpresa;

    public Geocercas() {
    }

    public Geocercas(Integer idGeocerca) {
        this.idGeocerca = idGeocerca;
    }

    public Geocercas(Integer idGeocerca, String geocerca, String descripcion, double area, Date fechaHoraRegistro, Date fechaHoraModificacion, int idUsuarioModificaion) {
        this.idGeocerca = idGeocerca;
        this.geocerca = geocerca;
        this.descripcion = descripcion;
        this.area = area;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.fechaHoraModificacion = fechaHoraModificacion;
        this.idUsuarioModificaion = idUsuarioModificaion;
    }

    public Integer getIdGeocerca() {
        return idGeocerca;
    }

    public void setIdGeocerca(Integer idGeocerca) {
        this.idGeocerca = idGeocerca;
    }

    public String getGeocerca() {
        return geocerca;
    }

    public void setGeocerca(String geocerca) {
        this.geocerca = geocerca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Date getFechaHoraModificacion() {
        return fechaHoraModificacion;
    }

    public void setFechaHoraModificacion(Date fechaHoraModificacion) {
        this.fechaHoraModificacion = fechaHoraModificacion;
    }

    public int getIdUsuarioModificaion() {
        return idUsuarioModificaion;
    }

    public void setIdUsuarioModificaion(int idUsuarioModificaion) {
        this.idUsuarioModificaion = idUsuarioModificaion;
    }

    @XmlTransient
    public Collection<GeocercaPuntos> getGeocercaPuntosCollection() {
        return geocercaPuntosCollection;
    }

    public void setGeocercaPuntosCollection(Collection<GeocercaPuntos> geocercaPuntosCollection) {
        this.geocercaPuntosCollection = geocercaPuntosCollection;
    }

    @XmlTransient
    public Collection<GeocercaVehiculos> getGeocercaVehiculosCollection() {
        return geocercaVehiculosCollection;
    }

    public void setGeocercaVehiculosCollection(Collection<GeocercaVehiculos> geocercaVehiculosCollection) {
        this.geocercaVehiculosCollection = geocercaVehiculosCollection;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGeocerca != null ? idGeocerca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Geocercas)) {
            return false;
        }
        Geocercas other = (Geocercas) object;
        if ((this.idGeocerca == null && other.idGeocerca != null) || (this.idGeocerca != null && !this.idGeocerca.equals(other.idGeocerca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Geocercas[ idGeocerca=" + idGeocerca + " ]";
    }
    
}
