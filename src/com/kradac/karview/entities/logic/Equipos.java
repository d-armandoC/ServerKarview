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
@Table(name = "equipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipos.findAll", query = "SELECT e FROM Equipos e"),
    @NamedQuery(name = "Equipos.findByIdEquipo", query = "SELECT e FROM Equipos e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipos.findByIdTipoEquipo", query = "SELECT e FROM Equipos e WHERE e.idTipoEquipo = :idTipoEquipo"),
    @NamedQuery(name = "Equipos.findByEquipo", query = "SELECT e FROM Equipos e WHERE e.equipo = :equipo"),
    @NamedQuery(name = "Equipos.findBySerie", query = "SELECT e FROM Equipos e WHERE e.serie = :serie"),
    @NamedQuery(name = "Equipos.findByNumeroChip", query = "SELECT e FROM Equipos e WHERE e.numeroChip = :numeroChip"),
    @NamedQuery(name = "Equipos.findByImeiChip", query = "SELECT e FROM Equipos e WHERE e.imeiChip = :imeiChip"),
    @NamedQuery(name = "Equipos.findByObservacion", query = "SELECT e FROM Equipos e WHERE e.observacion = :observacion"),
    @NamedQuery(name = "Equipos.findByComentario", query = "SELECT e FROM Equipos e WHERE e.comentario = :comentario"),
    @NamedQuery(name = "Equipos.findByFechaHoraComentario", query = "SELECT e FROM Equipos e WHERE e.fechaHoraComentario = :fechaHoraComentario"),
    @NamedQuery(name = "Equipos.findByFechaHoraRegistro", query = "SELECT e FROM Equipos e WHERE e.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Equipos.findByFechaHoraModificacion", query = "SELECT e FROM Equipos e WHERE e.fechaHoraModificacion = :fechaHoraModificacion"),
    @NamedQuery(name = "Equipos.findByIdUsuario", query = "SELECT e FROM Equipos e WHERE e.idUsuario = :idUsuario"),
    @NamedQuery(name = "Equipos.findByEstado", query = "SELECT e FROM Equipos e WHERE e.estado = :estado")})
public class Equipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private Integer idEquipo;
    @Basic(optional = false)
    @Column(name = "id_tipo_equipo")
    private int idTipoEquipo;
    @Basic(optional = false)
    @Column(name = "equipo")
    private String equipo;
    @Basic(optional = false)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "numero_chip")
    private String numeroChip;
    @Basic(optional = false)
    @Column(name = "imei_chip")
    private String imeiChip;
    @Basic(optional = false)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "fecha_hora_comentario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraComentario;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @Column(name = "id_usuario")
    private String idUsuario;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private Collection<Vehiculos> vehiculosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private Collection<UltimoDatoSkps> ultimoDatoSkpsCollection;

    public Equipos() {
    }

    public Equipos(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Equipos(Integer idEquipo, int idTipoEquipo, String equipo, String serie, String numeroChip, String imeiChip, String observacion, String comentario, Date fechaHoraComentario, Date fechaHoraRegistro, Date fechaHoraModificacion, int estado) {
        this.idEquipo = idEquipo;
        this.idTipoEquipo = idTipoEquipo;
        this.equipo = equipo;
        this.serie = serie;
        this.numeroChip = numeroChip;
        this.imeiChip = imeiChip;
        this.observacion = observacion;
        this.comentario = comentario;
        this.fechaHoraComentario = fechaHoraComentario;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.fechaHoraModificacion = fechaHoraModificacion;
        this.estado = estado;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(int idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }

    public String getImeiChip() {
        return imeiChip;
    }

    public void setImeiChip(String imeiChip) {
        this.imeiChip = imeiChip;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaHoraComentario() {
        return fechaHoraComentario;
    }

    public void setFechaHoraComentario(Date fechaHoraComentario) {
        this.fechaHoraComentario = fechaHoraComentario;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Vehiculos> getVehiculosCollection() {
        return vehiculosCollection;
    }

    public void setVehiculosCollection(Collection<Vehiculos> vehiculosCollection) {
        this.vehiculosCollection = vehiculosCollection;
    }

    @XmlTransient
    public Collection<UltimoDatoSkps> getUltimoDatoSkpsCollection() {
        return ultimoDatoSkpsCollection;
    }

    public void setUltimoDatoSkpsCollection(Collection<UltimoDatoSkps> ultimoDatoSkpsCollection) {
        this.ultimoDatoSkpsCollection = ultimoDatoSkpsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipos)) {
            return false;
        }
        Equipos other = (Equipos) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Equipos[ idEquipo=" + idEquipo + " ]";
    }
    
}
