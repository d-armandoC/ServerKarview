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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "dato_spks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoSpks.findAll", query = "SELECT d FROM DatoSpks d"),
    @NamedQuery(name = "DatoSpks.findByIdEquipo", query = "SELECT d FROM DatoSpks d WHERE d.datoSpksPK.idEquipo = :idEquipo"),
    @NamedQuery(name = "DatoSpks.findByFecha", query = "SELECT d FROM DatoSpks d WHERE d.datoSpksPK.fecha = :fecha"),
    @NamedQuery(name = "DatoSpks.findByHora", query = "SELECT d FROM DatoSpks d WHERE d.datoSpksPK.hora = :hora"),
    @NamedQuery(name = "DatoSpks.findByFechaHoraRegistro", query = "SELECT d FROM DatoSpks d WHERE d.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "DatoSpks.findByIdSkyEvento", query = "SELECT d FROM DatoSpks d WHERE d.datoSpksPK.idSkyEvento = :idSkyEvento"),
    @NamedQuery(name = "DatoSpks.findByLatitud", query = "SELECT d FROM DatoSpks d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "DatoSpks.findByLongitud", query = "SELECT d FROM DatoSpks d WHERE d.longitud = :longitud"),
    @NamedQuery(name = "DatoSpks.findByVelocidad", query = "SELECT d FROM DatoSpks d WHERE d.velocidad = :velocidad"),
    @NamedQuery(name = "DatoSpks.findByRumbo", query = "SELECT d FROM DatoSpks d WHERE d.rumbo = :rumbo"),
    @NamedQuery(name = "DatoSpks.findByG1", query = "SELECT d FROM DatoSpks d WHERE d.g1 = :g1"),
    @NamedQuery(name = "DatoSpks.findByG2", query = "SELECT d FROM DatoSpks d WHERE d.g2 = :g2"),
    @NamedQuery(name = "DatoSpks.findBySal", query = "SELECT d FROM DatoSpks d WHERE d.sal = :sal"),
    @NamedQuery(name = "DatoSpks.findByBateria", query = "SELECT d FROM DatoSpks d WHERE d.bateria = :bateria"),
    @NamedQuery(name = "DatoSpks.findByV1", query = "SELECT d FROM DatoSpks d WHERE d.v1 = :v1"),
    @NamedQuery(name = "DatoSpks.findByV2", query = "SELECT d FROM DatoSpks d WHERE d.v2 = :v2"),
    @NamedQuery(name = "DatoSpks.findByGsm", query = "SELECT d FROM DatoSpks d WHERE d.gsm = :gsm"),
    @NamedQuery(name = "DatoSpks.findByGps", query = "SELECT d FROM DatoSpks d WHERE d.gps = :gps"),
    @NamedQuery(name = "DatoSpks.findByIgn", query = "SELECT d FROM DatoSpks d WHERE d.ign = :ign"),
    @NamedQuery(name = "DatoSpks.findByOdometro", query = "SELECT d FROM DatoSpks d WHERE d.odometro = :odometro"),
    @NamedQuery(name = "DatoSpks.findByDireccion", query = "SELECT d FROM DatoSpks d WHERE d.direccion = :direccion")})
public class DatoSpks implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatoSpksPK datoSpksPK;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Basic(optional = false)
    @Column(name = "latitud")
    private double latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private double longitud;
    @Basic(optional = false)
    @Column(name = "velocidad")
    private double velocidad;
    @Basic(optional = false)
    @Column(name = "rumbo")
    private double rumbo;
    @Basic(optional = false)
    @Column(name = "g1")
    private short g1;
    @Basic(optional = false)
    @Column(name = "g2")
    private short g2;
    @Basic(optional = false)
    @Column(name = "sal")
    private short sal;
    @Basic(optional = false)
    @Column(name = "bateria")
    private short bateria;
    @Basic(optional = false)
    @Column(name = "v1")
    private short v1;
    @Basic(optional = false)
    @Column(name = "v2")
    private short v2;
    @Basic(optional = false)
    @Column(name = "gsm")
    private short gsm;
    @Basic(optional = false)
    @Column(name = "gps")
    private short gps;
    @Basic(optional = false)
    @Column(name = "ign")
    private short ign;
    @Basic(optional = false)
    @Column(name = "odometro")
    private int odometro;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    public DatoSpks() {
    }

    public DatoSpks(DatoSpksPK datoSpksPK) {
        this.datoSpksPK = datoSpksPK;
    }

    public DatoSpks(DatoSpksPK datoSpksPK, Date fechaHoraRegistro, double latitud, double longitud, double velocidad, double rumbo, short g1, short g2, short sal, short bateria, short v1, short v2, short gsm, short gps, short ign, int odometro, String direccion) {
        this.datoSpksPK = datoSpksPK;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.latitud = latitud;
        this.longitud = longitud;
        this.velocidad = velocidad;
        this.rumbo = rumbo;
        this.g1 = g1;
        this.g2 = g2;
        this.sal = sal;
        this.bateria = bateria;
        this.v1 = v1;
        this.v2 = v2;
        this.gsm = gsm;
        this.gps = gps;
        this.ign = ign;
        this.odometro = odometro;
        this.direccion = direccion;
    }

    public DatoSpks(int idEquipo, Date fecha, Date hora, int idSkyEvento) {
        this.datoSpksPK = new DatoSpksPK(idEquipo, fecha, hora, idSkyEvento);
    }

    public DatoSpksPK getDatoSpksPK() {
        return datoSpksPK;
    }

    public void setDatoSpksPK(DatoSpksPK datoSpksPK) {
        this.datoSpksPK = datoSpksPK;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
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

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getRumbo() {
        return rumbo;
    }

    public void setRumbo(double rumbo) {
        this.rumbo = rumbo;
    }

    public short getG1() {
        return g1;
    }

    public void setG1(short g1) {
        this.g1 = g1;
    }

    public short getG2() {
        return g2;
    }

    public void setG2(short g2) {
        this.g2 = g2;
    }

    public short getSal() {
        return sal;
    }

    public void setSal(short sal) {
        this.sal = sal;
    }

    public short getBateria() {
        return bateria;
    }

    public void setBateria(short bateria) {
        this.bateria = bateria;
    }

    public short getV1() {
        return v1;
    }

    public void setV1(short v1) {
        this.v1 = v1;
    }

    public short getV2() {
        return v2;
    }

    public void setV2(short v2) {
        this.v2 = v2;
    }

    public short getGsm() {
        return gsm;
    }

    public void setGsm(short gsm) {
        this.gsm = gsm;
    }

    public short getGps() {
        return gps;
    }

    public void setGps(short gps) {
        this.gps = gps;
    }

    public short getIgn() {
        return ign;
    }

    public void setIgn(short ign) {
        this.ign = ign;
    }

    public int getOdometro() {
        return odometro;
    }

    public void setOdometro(int odometro) {
        this.odometro = odometro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datoSpksPK != null ? datoSpksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoSpks)) {
            return false;
        }
        DatoSpks other = (DatoSpks) object;
        if ((this.datoSpksPK == null && other.datoSpksPK != null) || (this.datoSpksPK != null && !this.datoSpksPK.equals(other.datoSpksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.DatoSpks[ datoSpksPK=" + datoSpksPK + " ]";
    }
    
}
