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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ultimo_dato_skps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UltimoDatoSkps.findAll", query = "SELECT u FROM UltimoDatoSkps u"),
    @NamedQuery(name = "UltimoDatoSkps.findByIdUltimoDatoSkp", query = "SELECT u FROM UltimoDatoSkps u WHERE u.idUltimoDatoSkp = :idUltimoDatoSkp"),
    @NamedQuery(name = "UltimoDatoSkps.findByFechaHoraConex", query = "SELECT u FROM UltimoDatoSkps u WHERE u.fechaHoraConex = :fechaHoraConex"),
    @NamedQuery(name = "UltimoDatoSkps.findByFechaHoraUltDato", query = "SELECT u FROM UltimoDatoSkps u WHERE u.fechaHoraUltDato = :fechaHoraUltDato"),
    @NamedQuery(name = "UltimoDatoSkps.findByIdRuta", query = "SELECT u FROM UltimoDatoSkps u WHERE u.idRuta = :idRuta"),
    @NamedQuery(name = "UltimoDatoSkps.findByIdPunto", query = "SELECT u FROM UltimoDatoSkps u WHERE u.idPunto = :idPunto"),
    @NamedQuery(name = "UltimoDatoSkps.findByLatitud", query = "SELECT u FROM UltimoDatoSkps u WHERE u.latitud = :latitud"),
    @NamedQuery(name = "UltimoDatoSkps.findByLongitud", query = "SELECT u FROM UltimoDatoSkps u WHERE u.longitud = :longitud"),
    @NamedQuery(name = "UltimoDatoSkps.findByVelocidad", query = "SELECT u FROM UltimoDatoSkps u WHERE u.velocidad = :velocidad"),
    @NamedQuery(name = "UltimoDatoSkps.findByRumbo", query = "SELECT u FROM UltimoDatoSkps u WHERE u.rumbo = :rumbo"),
    @NamedQuery(name = "UltimoDatoSkps.findByG1", query = "SELECT u FROM UltimoDatoSkps u WHERE u.g1 = :g1"),
    @NamedQuery(name = "UltimoDatoSkps.findByG2", query = "SELECT u FROM UltimoDatoSkps u WHERE u.g2 = :g2"),
    @NamedQuery(name = "UltimoDatoSkps.findBySal", query = "SELECT u FROM UltimoDatoSkps u WHERE u.sal = :sal"),
    @NamedQuery(name = "UltimoDatoSkps.findByBateria", query = "SELECT u FROM UltimoDatoSkps u WHERE u.bateria = :bateria"),
    @NamedQuery(name = "UltimoDatoSkps.findByV1", query = "SELECT u FROM UltimoDatoSkps u WHERE u.v1 = :v1"),
    @NamedQuery(name = "UltimoDatoSkps.findByV2", query = "SELECT u FROM UltimoDatoSkps u WHERE u.v2 = :v2"),
    @NamedQuery(name = "UltimoDatoSkps.findByGsm", query = "SELECT u FROM UltimoDatoSkps u WHERE u.gsm = :gsm"),
    @NamedQuery(name = "UltimoDatoSkps.findByGps", query = "SELECT u FROM UltimoDatoSkps u WHERE u.gps = :gps"),
    @NamedQuery(name = "UltimoDatoSkps.findByIgn", query = "SELECT u FROM UltimoDatoSkps u WHERE u.ign = :ign"),
    @NamedQuery(name = "UltimoDatoSkps.findByDireccion", query = "SELECT u FROM UltimoDatoSkps u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "UltimoDatoSkps.findByEstado", query = "SELECT u FROM UltimoDatoSkps u WHERE u.estado = :estado"),
    @NamedQuery(name = "UltimoDatoSkps.findByIdUsuario", query = "SELECT u FROM UltimoDatoSkps u WHERE u.idUsuario = :idUsuario")})
public class UltimoDatoSkps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ultimo_dato_skp")
    private Integer idUltimoDatoSkp;
    @Basic(optional = false)
    @Column(name = "fecha_hora_conex")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraConex;
    @Basic(optional = false)
    @Column(name = "fecha_hora_ult_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUltDato;
    @Basic(optional = false)
    @Column(name = "id_ruta")
    private int idRuta;
    @Basic(optional = false)
    @Column(name = "id_punto")
    private int idPunto;
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
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos idEquipo;
    @JoinColumn(name = "id_sky_evento", referencedColumnName = "id_sky_evento")
    @ManyToOne(optional = false)
    private SkyEventos idSkyEvento;

    public UltimoDatoSkps() {
    }

    public UltimoDatoSkps(Integer idUltimoDatoSkp) {
        this.idUltimoDatoSkp = idUltimoDatoSkp;
    }

    public UltimoDatoSkps(Integer idUltimoDatoSkp, Date fechaHoraConex, Date fechaHoraUltDato, int idRuta, int idPunto, double latitud, double longitud, double velocidad, double rumbo, short g1, short g2, short sal, short bateria, short v1, short v2, short gsm, short gps, short ign, short estado, int idUsuario) {
        this.idUltimoDatoSkp = idUltimoDatoSkp;
        this.fechaHoraConex = fechaHoraConex;
        this.fechaHoraUltDato = fechaHoraUltDato;
        this.idRuta = idRuta;
        this.idPunto = idPunto;
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
        this.estado = estado;
        this.idUsuario = idUsuario;
    }
    
       public UltimoDatoSkps(Date fechaHoraConex, Date fechaHoraUltDato, double latitud, double longitud, double velocidad, double rumbo, short g1, short g2, short sal, short bateria, short v1, short v2, short gsm, short gps, short ign, String direccion, int cantidadPasajeros, Equipos idEquipo, SkyEventos idSkyEvento) {
        this.fechaHoraConex = fechaHoraConex;
        this.fechaHoraUltDato = fechaHoraUltDato;
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
        this.direccion = direccion;
        this.idEquipo = idEquipo;
        this.idSkyEvento = idSkyEvento;
    }
       
       
       


    public Integer getIdUltimoDatoSkp() {
        return idUltimoDatoSkp;
    }

    public void setIdUltimoDatoSkp(Integer idUltimoDatoSkp) {
        this.idUltimoDatoSkp = idUltimoDatoSkp;
    }

    public Date getFechaHoraConex() {
        return fechaHoraConex;
    }

    public void setFechaHoraConex(Date fechaHoraConex) {
        this.fechaHoraConex = fechaHoraConex;
    }

    public Date getFechaHoraUltDato() {
        return fechaHoraUltDato;
    }

    public void setFechaHoraUltDato(Date fechaHoraUltDato) {
        this.fechaHoraUltDato = fechaHoraUltDato;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(int idPunto) {
        this.idPunto = idPunto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Equipos getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipos idEquipo) {
        this.idEquipo = idEquipo;
    }

    public SkyEventos getIdSkyEvento() {
        return idSkyEvento;
    }

    public void setIdSkyEvento(SkyEventos idSkyEvento) {
        this.idSkyEvento = idSkyEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUltimoDatoSkp != null ? idUltimoDatoSkp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UltimoDatoSkps)) {
            return false;
        }
        UltimoDatoSkps other = (UltimoDatoSkps) object;
        if ((this.idUltimoDatoSkp == null && other.idUltimoDatoSkp != null) || (this.idUltimoDatoSkp != null && !this.idUltimoDatoSkp.equals(other.idUltimoDatoSkp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.UltimoDatoSkps[ idUltimoDatoSkp=" + idUltimoDatoSkp + " ]";
    }
    
}
