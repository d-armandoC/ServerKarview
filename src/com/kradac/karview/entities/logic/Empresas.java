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
@Table(name = "empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e"),
    @NamedQuery(name = "Empresas.findByIdEmpresa", query = "SELECT e FROM Empresas e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresas.findByAcronimo", query = "SELECT e FROM Empresas e WHERE e.acronimo = :acronimo"),
    @NamedQuery(name = "Empresas.findByEmpresa", query = "SELECT e FROM Empresas e WHERE e.empresa = :empresa"),
    @NamedQuery(name = "Empresas.findByDireccion", query = "SELECT e FROM Empresas e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresas.findByTelefono", query = "SELECT e FROM Empresas e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresas.findByCorreo", query = "SELECT e FROM Empresas e WHERE e.correo = :correo"),
    @NamedQuery(name = "Empresas.findByFechaHoraRegistro", query = "SELECT e FROM Empresas e WHERE e.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Empresas.findByFechaHoraModificacion", query = "SELECT e FROM Empresas e WHERE e.fechaHoraModificacion = :fechaHoraModificacion"),
    @NamedQuery(name = "Empresas.findByIdusuarioAsignado", query = "SELECT e FROM Empresas e WHERE e.idusuarioAsignado = :idusuarioAsignado")})
public class Empresas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Basic(optional = false)
    @Column(name = "acronimo")
    private String acronimo;
    @Basic(optional = false)
    @Column(name = "empresa")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @Basic(optional = false)
    @Column(name = "id_usuarioAsignado")
    private int idusuarioAsignado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresas")
    private Collection<EnvioCorreos> envioCorreosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<Personas> personasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<Vehiculos> vehiculosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<Geocercas> geocercasCollection;

    public Empresas() {
    }

    public Empresas(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresas(Integer idEmpresa, String acronimo, String empresa, String direccion, String telefono, String correo, Date fechaHoraRegistro, Date fechaHoraModificacion, int idusuarioAsignado) {
        this.idEmpresa = idEmpresa;
        this.acronimo = acronimo;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.fechaHoraModificacion = fechaHoraModificacion;
        this.idusuarioAsignado = idusuarioAsignado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public int getIdusuarioAsignado() {
        return idusuarioAsignado;
    }

    public void setIdusuarioAsignado(int idusuarioAsignado) {
        this.idusuarioAsignado = idusuarioAsignado;
    }

    @XmlTransient
    public Collection<EnvioCorreos> getEnvioCorreosCollection() {
        return envioCorreosCollection;
    }

    public void setEnvioCorreosCollection(Collection<EnvioCorreos> envioCorreosCollection) {
        this.envioCorreosCollection = envioCorreosCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<Personas> getPersonasCollection() {
        return personasCollection;
    }

    public void setPersonasCollection(Collection<Personas> personasCollection) {
        this.personasCollection = personasCollection;
    }

    @XmlTransient
    public Collection<Vehiculos> getVehiculosCollection() {
        return vehiculosCollection;
    }

    public void setVehiculosCollection(Collection<Vehiculos> vehiculosCollection) {
        this.vehiculosCollection = vehiculosCollection;
    }

    @XmlTransient
    public Collection<Geocercas> getGeocercasCollection() {
        return geocercasCollection;
    }

    public void setGeocercasCollection(Collection<Geocercas> geocercasCollection) {
        this.geocercasCollection = geocercasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Empresas[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
