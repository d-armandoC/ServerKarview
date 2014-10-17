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
@Table(name = "comentario_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioVehiculos.findAll", query = "SELECT c FROM ComentarioVehiculos c"),
    @NamedQuery(name = "ComentarioVehiculos.findByIdComentarioVehiculo", query = "SELECT c FROM ComentarioVehiculos c WHERE c.idComentarioVehiculo = :idComentarioVehiculo"),
    @NamedQuery(name = "ComentarioVehiculos.findByIdUsuario", query = "SELECT c FROM ComentarioVehiculos c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ComentarioVehiculos.findByIdVehiculo", query = "SELECT c FROM ComentarioVehiculos c WHERE c.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "ComentarioVehiculos.findByComentario", query = "SELECT c FROM ComentarioVehiculos c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "ComentarioVehiculos.findByFechaHoraRegistro", query = "SELECT c FROM ComentarioVehiculos c WHERE c.fechaHoraRegistro = :fechaHoraRegistro")})
public class ComentarioVehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentario_vehiculo")
    private Integer idComentarioVehiculo;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;

    public ComentarioVehiculos() {
    }

    public ComentarioVehiculos(Integer idComentarioVehiculo) {
        this.idComentarioVehiculo = idComentarioVehiculo;
    }

    public ComentarioVehiculos(Integer idComentarioVehiculo, int idUsuario, int idVehiculo, String comentario, Date fechaHoraRegistro) {
        this.idComentarioVehiculo = idComentarioVehiculo;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.comentario = comentario;
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Integer getIdComentarioVehiculo() {
        return idComentarioVehiculo;
    }

    public void setIdComentarioVehiculo(Integer idComentarioVehiculo) {
        this.idComentarioVehiculo = idComentarioVehiculo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        hash += (idComentarioVehiculo != null ? idComentarioVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioVehiculos)) {
            return false;
        }
        ComentarioVehiculos other = (ComentarioVehiculos) object;
        if ((this.idComentarioVehiculo == null && other.idComentarioVehiculo != null) || (this.idComentarioVehiculo != null && !this.idComentarioVehiculo.equals(other.idComentarioVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.ComentarioVehiculos[ idComentarioVehiculo=" + idComentarioVehiculo + " ]";
    }
    
}
