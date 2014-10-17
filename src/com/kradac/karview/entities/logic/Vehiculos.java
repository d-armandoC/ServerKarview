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
@Table(name = "vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v"),
    @NamedQuery(name = "Vehiculos.findByIdVehiculo", query = "SELECT v FROM Vehiculos v WHERE v.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Vehiculos.findByPlaca", query = "SELECT v FROM Vehiculos v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculos.findByMarca", query = "SELECT v FROM Vehiculos v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculos.findByModelo", query = "SELECT v FROM Vehiculos v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculos.findByYear", query = "SELECT v FROM Vehiculos v WHERE v.year = :year"),
    @NamedQuery(name = "Vehiculos.findByVehiculo", query = "SELECT v FROM Vehiculos v WHERE v.vehiculo = :vehiculo"),
    @NamedQuery(name = "Vehiculos.findByNumMotor", query = "SELECT v FROM Vehiculos v WHERE v.numMotor = :numMotor"),
    @NamedQuery(name = "Vehiculos.findByNumChasis", query = "SELECT v FROM Vehiculos v WHERE v.numChasis = :numChasis"),
    @NamedQuery(name = "Vehiculos.findByImagen", query = "SELECT v FROM Vehiculos v WHERE v.imagen = :imagen"),
    @NamedQuery(name = "Vehiculos.findByIcono", query = "SELECT v FROM Vehiculos v WHERE v.icono = :icono"),
    @NamedQuery(name = "Vehiculos.findByFechaHoraRegistro", query = "SELECT v FROM Vehiculos v WHERE v.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Vehiculos.findByFechaHoraModificacion", query = "SELECT v FROM Vehiculos v WHERE v.fechaHoraModificacion = :fechaHoraModificacion"),
    @NamedQuery(name = "Vehiculos.findByObservacion", query = "SELECT v FROM Vehiculos v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "Vehiculos.findByComentario", query = "SELECT v FROM Vehiculos v WHERE v.comentario = :comentario"),
    @NamedQuery(name = "Vehiculos.findByFechaHoraComentario", query = "SELECT v FROM Vehiculos v WHERE v.fechaHoraComentario = :fechaHoraComentario"),
    @NamedQuery(name = "Vehiculos.findByIdUsuario", query = "SELECT v FROM Vehiculos v WHERE v.idUsuario = :idUsuario")})
public class Vehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @Column(name = "vehiculo")
    private String vehiculo;
    @Basic(optional = false)
    @Column(name = "num_motor")
    private String numMotor;
    @Basic(optional = false)
    @Column(name = "num_chasis")
    private String numChasis;
    @Basic(optional = false)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @Column(name = "icono")
    private String icono;
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Column(name = "fecha_hora_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_hora_comentario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraComentario;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @JoinColumn(name = "id_clase_vehiculo", referencedColumnName = "id_clase_vehiculo")
    @ManyToOne(optional = false)
    private ClaseVehiculos idClaseVehiculo;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresas idEmpresa;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos idEquipo;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;

    public Vehiculos() {
    }

    public Vehiculos(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Vehiculos(Integer idVehiculo, String placa, String marca, String modelo, int year, String vehiculo, String numMotor, String numChasis, String imagen, String icono) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.vehiculo = vehiculo;
        this.numMotor = numMotor;
        this.numChasis = numChasis;
        this.imagen = imagen;
        this.icono = icono;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public String getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ClaseVehiculos getIdClaseVehiculo() {
        return idClaseVehiculo;
    }

    public void setIdClaseVehiculo(ClaseVehiculos idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Equipos getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipos idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Vehiculos[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
