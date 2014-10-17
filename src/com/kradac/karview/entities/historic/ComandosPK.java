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
public class ComandosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private int idEquipo;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;

    public ComandosPK() {
    }

    public ComandosPK(int idUsuario, int idEquipo, Date fechaHoraRegistro) {
        this.idUsuario = idUsuario;
        this.idEquipo = idEquipo;
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
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
        hash += (int) idUsuario;
        hash += (int) idEquipo;
        hash += (fechaHoraRegistro != null ? fechaHoraRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComandosPK)) {
            return false;
        }
        ComandosPK other = (ComandosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idEquipo != other.idEquipo) {
            return false;
        }
        if ((this.fechaHoraRegistro == null && other.fechaHoraRegistro != null) || (this.fechaHoraRegistro != null && !this.fechaHoraRegistro.equals(other.fechaHoraRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.ComandosPK[ idUsuario=" + idUsuario + ", idEquipo=" + idEquipo + ", fechaHoraRegistro=" + fechaHoraRegistro + " ]";
    }
    
}
