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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByClave", query = "SELECT u FROM Usuarios u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuarios.findByIp", query = "SELECT u FROM Usuarios u WHERE u.ip = :ip"),
    @NamedQuery(name = "Usuarios.findByHost", query = "SELECT u FROM Usuarios u WHERE u.host = :host"),
    @NamedQuery(name = "Usuarios.findByFechaHoraConexion", query = "SELECT u FROM Usuarios u WHERE u.fechaHoraConexion = :fechaHoraConexion"),
    @NamedQuery(name = "Usuarios.findByConectado", query = "SELECT u FROM Usuarios u WHERE u.conectado = :conectado"),
    @NamedQuery(name = "Usuarios.findByLatitud", query = "SELECT u FROM Usuarios u WHERE u.latitud = :latitud"),
    @NamedQuery(name = "Usuarios.findByLongitud", query = "SELECT u FROM Usuarios u WHERE u.longitud = :longitud"),
    @NamedQuery(name = "Usuarios.findByFechaHoraRegistro", query = "SELECT u FROM Usuarios u WHERE u.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Usuarios.findByFechaHoraModificacion", query = "SELECT u FROM Usuarios u WHERE u.fechaHoraModificacion = :fechaHoraModificacion")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @Column(name = "fecha_hora_conexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraConexion;
    @Basic(optional = false)
    @Column(name = "conectado")
    private short conectado;
    @Basic(optional = false)
    @Column(name = "latitud")
    private double latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private double longitud;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresas idEmpresa;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario")
    @ManyToOne(optional = false)
    private RolUsuarios idRolUsuario;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String usuario, String clave, String ip, String host, Date fechaHoraConexion, short conectado, double latitud, double longitud, Date fechaHoraRegistro, Date fechaHoraModificacion) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.ip = ip;
        this.host = host;
        this.fechaHoraConexion = fechaHoraConexion;
        this.conectado = conectado;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.fechaHoraModificacion = fechaHoraModificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getFechaHoraConexion() {
        return fechaHoraConexion;
    }

    public void setFechaHoraConexion(Date fechaHoraConexion) {
        this.fechaHoraConexion = fechaHoraConexion;
    }

    public short getConectado() {
        return conectado;
    }

    public void setConectado(short conectado) {
        this.conectado = conectado;
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

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public RolUsuarios getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(RolUsuarios idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
