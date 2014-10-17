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
public class AccesosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "fecha_hora_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraReg;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;

    public AccesosPK() {
    }

    public AccesosPK(int idUsuario, Date fechaHoraReg, String ip) {
        this.idUsuario = idUsuario;
        this.fechaHoraReg = fechaHoraReg;
        this.ip = ip;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaHoraReg() {
        return fechaHoraReg;
    }

    public void setFechaHoraReg(Date fechaHoraReg) {
        this.fechaHoraReg = fechaHoraReg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (fechaHoraReg != null ? fechaHoraReg.hashCode() : 0);
        hash += (ip != null ? ip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesosPK)) {
            return false;
        }
        AccesosPK other = (AccesosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if ((this.fechaHoraReg == null && other.fechaHoraReg != null) || (this.fechaHoraReg != null && !this.fechaHoraReg.equals(other.fechaHoraReg))) {
            return false;
        }
        if ((this.ip == null && other.ip != null) || (this.ip != null && !this.ip.equals(other.ip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.AccesosPK[ idUsuario=" + idUsuario + ", fechaHoraReg=" + fechaHoraReg + ", ip=" + ip + " ]";
    }
    
}
