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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "accesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accesos.findAll", query = "SELECT a FROM Accesos a"),
    @NamedQuery(name = "Accesos.findByIdUsuario", query = "SELECT a FROM Accesos a WHERE a.accesosPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Accesos.findByFechaHoraReg", query = "SELECT a FROM Accesos a WHERE a.accesosPK.fechaHoraReg = :fechaHoraReg"),
    @NamedQuery(name = "Accesos.findByIp", query = "SELECT a FROM Accesos a WHERE a.accesosPK.ip = :ip"),
    @NamedQuery(name = "Accesos.findByHost", query = "SELECT a FROM Accesos a WHERE a.host = :host"),
    @NamedQuery(name = "Accesos.findByLatitud", query = "SELECT a FROM Accesos a WHERE a.latitud = :latitud"),
    @NamedQuery(name = "Accesos.findByLongitud", query = "SELECT a FROM Accesos a WHERE a.longitud = :longitud")})
public class Accesos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccesosPK accesosPK;
    @Basic(optional = false)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @Column(name = "latitud")
    private double latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private double longitud;

    public Accesos() {
    }

    public Accesos(AccesosPK accesosPK) {
        this.accesosPK = accesosPK;
    }

    public Accesos(AccesosPK accesosPK, String host, double latitud, double longitud) {
        this.accesosPK = accesosPK;
        this.host = host;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Accesos(int idUsuario, Date fechaHoraReg, String ip) {
        this.accesosPK = new AccesosPK(idUsuario, fechaHoraReg, ip);
    }

    public AccesosPK getAccesosPK() {
        return accesosPK;
    }

    public void setAccesosPK(AccesosPK accesosPK) {
        this.accesosPK = accesosPK;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accesosPK != null ? accesosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesos)) {
            return false;
        }
        Accesos other = (Accesos) object;
        if ((this.accesosPK == null && other.accesosPK != null) || (this.accesosPK != null && !this.accesosPK.equals(other.accesosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.Accesos[ accesosPK=" + accesosPK + " ]";
    }
    
}
