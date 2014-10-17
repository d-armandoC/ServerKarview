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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "dato_invalidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoInvalidos.findAll", query = "SELECT d FROM DatoInvalidos d"),
    @NamedQuery(name = "DatoInvalidos.findByIdDatoInvalido", query = "SELECT d FROM DatoInvalidos d WHERE d.idDatoInvalido = :idDatoInvalido"),
    @NamedQuery(name = "DatoInvalidos.findByIdTipoDatoInvalido", query = "SELECT d FROM DatoInvalidos d WHERE d.idTipoDatoInvalido = :idTipoDatoInvalido"),
    @NamedQuery(name = "DatoInvalidos.findByFechaHoraRegistro", query = "SELECT d FROM DatoInvalidos d WHERE d.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "DatoInvalidos.findByEquipo", query = "SELECT d FROM DatoInvalidos d WHERE d.equipo = :equipo"),
    @NamedQuery(name = "DatoInvalidos.findByTrama", query = "SELECT d FROM DatoInvalidos d WHERE d.trama = :trama"),
    @NamedQuery(name = "DatoInvalidos.findByExcepcion", query = "SELECT d FROM DatoInvalidos d WHERE d.excepcion = :excepcion")})
public class DatoInvalidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dato_invalido")
    private Integer idDatoInvalido;
    @Basic(optional = false)
    @Column(name = "id_tipo_dato_invalido")
    private int idTipoDatoInvalido;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;
    @Column(name = "equipo")
    private String equipo;
    @Column(name = "trama")
    private String trama;
    @Column(name = "excepcion")
    private String excepcion;

    public DatoInvalidos() {
    }

    public DatoInvalidos(Integer idDatoInvalido) {
        this.idDatoInvalido = idDatoInvalido;
    }

    public DatoInvalidos(Integer idDatoInvalido, int idTipoDatoInvalido, Date fechaHoraRegistro) {
        this.idDatoInvalido = idDatoInvalido;
        this.idTipoDatoInvalido = idTipoDatoInvalido;
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public DatoInvalidos(int idTipoDatoInvalido, Date fechaHoraRegistro, String equipo, String trama, String excepcion) {
        this.idTipoDatoInvalido = idTipoDatoInvalido;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.equipo = equipo;
        this.trama = trama;
        this.excepcion = excepcion;
    }

    public Integer getIdDatoInvalido() {
        return idDatoInvalido;
    }

    public void setIdDatoInvalido(Integer idDatoInvalido) {
        this.idDatoInvalido = idDatoInvalido;
    }

    public int getIdTipoDatoInvalido() {
        return idTipoDatoInvalido;
    }

    public void setIdTipoDatoInvalido(int idTipoDatoInvalido) {
        this.idTipoDatoInvalido = idTipoDatoInvalido;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatoInvalido != null ? idDatoInvalido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoInvalidos)) {
            return false;
        }
        DatoInvalidos other = (DatoInvalidos) object;
        if ((this.idDatoInvalido == null && other.idDatoInvalido != null) || (this.idDatoInvalido != null && !this.idDatoInvalido.equals(other.idDatoInvalido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.DatoInvalidos[ idDatoInvalido=" + idDatoInvalido + " ]";
    }

}
