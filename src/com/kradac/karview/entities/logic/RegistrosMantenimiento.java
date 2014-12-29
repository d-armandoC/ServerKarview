/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.logic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "registros_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistrosMantenimiento.findAll", query = "SELECT r FROM RegistrosMantenimiento r"),
    @NamedQuery(name = "RegistrosMantenimiento.findByIdVehiculo", query = "SELECT r FROM RegistrosMantenimiento r WHERE r.registrosMantenimientoPK.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "RegistrosMantenimiento.findByIdRegistro", query = "SELECT r FROM RegistrosMantenimiento r WHERE r.registrosMantenimientoPK.idRegistro = :idRegistro"),
    @NamedQuery(name = "RegistrosMantenimiento.findByDescripcion", query = "SELECT r FROM RegistrosMantenimiento r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RegistrosMantenimiento.findByFechaRegistro", query = "SELECT r FROM RegistrosMantenimiento r WHERE r.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "RegistrosMantenimiento.findByFechaVencimiento", query = "SELECT r FROM RegistrosMantenimiento r WHERE r.fechaVencimiento = :fechaVencimiento")})
public class RegistrosMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrosMantenimientoPK registrosMantenimientoPK;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    public RegistrosMantenimiento() {
    }

    public RegistrosMantenimiento(RegistrosMantenimientoPK registrosMantenimientoPK) {
        this.registrosMantenimientoPK = registrosMantenimientoPK;
    }

    public RegistrosMantenimiento(RegistrosMantenimientoPK registrosMantenimientoPK, String descripcion, Date fechaRegistro, Date fechaVencimiento) {
        this.registrosMantenimientoPK = registrosMantenimientoPK;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaVencimiento = fechaVencimiento;
    }

    public RegistrosMantenimiento(int idVehiculo, int idRegistro) {
        this.registrosMantenimientoPK = new RegistrosMantenimientoPK(idVehiculo, idRegistro);
    }

    public RegistrosMantenimientoPK getRegistrosMantenimientoPK() {
        return registrosMantenimientoPK;
    }

    public void setRegistrosMantenimientoPK(RegistrosMantenimientoPK registrosMantenimientoPK) {
        this.registrosMantenimientoPK = registrosMantenimientoPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrosMantenimientoPK != null ? registrosMantenimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrosMantenimiento)) {
            return false;
        }
        RegistrosMantenimiento other = (RegistrosMantenimiento) object;
        if ((this.registrosMantenimientoPK == null && other.registrosMantenimientoPK != null) || (this.registrosMantenimientoPK != null && !this.registrosMantenimientoPK.equals(other.registrosMantenimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.logic.RegistrosMantenimiento[ registrosMantenimientoPK=" + registrosMantenimientoPK + " ]";
    }
    
}
