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
@Table(name = "comentario_equipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioEquipos.findAll", query = "SELECT c FROM ComentarioEquipos c"),
    @NamedQuery(name = "ComentarioEquipos.findByIdComentarioEquipo", query = "SELECT c FROM ComentarioEquipos c WHERE c.idComentarioEquipo = :idComentarioEquipo"),
    @NamedQuery(name = "ComentarioEquipos.findByIdUsuario", query = "SELECT c FROM ComentarioEquipos c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ComentarioEquipos.findByIdEquipo", query = "SELECT c FROM ComentarioEquipos c WHERE c.idEquipo = :idEquipo"),
    @NamedQuery(name = "ComentarioEquipos.findByComentario", query = "SELECT c FROM ComentarioEquipos c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "ComentarioEquipos.findByFechaHoraRegistro", query = "SELECT c FROM ComentarioEquipos c WHERE c.fechaHoraRegistro = :fechaHoraRegistro")})
public class ComentarioEquipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentario_equipo")
    private Integer idComentarioEquipo;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private int idEquipo;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "fecha_hora_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistro;

    public ComentarioEquipos() {
    }

    public ComentarioEquipos(Integer idComentarioEquipo) {
        this.idComentarioEquipo = idComentarioEquipo;
    }

    public ComentarioEquipos(Integer idComentarioEquipo, int idUsuario, int idEquipo, String comentario, Date fechaHoraRegistro) {
        this.idComentarioEquipo = idComentarioEquipo;
        this.idUsuario = idUsuario;
        this.idEquipo = idEquipo;
        this.comentario = comentario;
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Integer getIdComentarioEquipo() {
        return idComentarioEquipo;
    }

    public void setIdComentarioEquipo(Integer idComentarioEquipo) {
        this.idComentarioEquipo = idComentarioEquipo;
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
        hash += (idComentarioEquipo != null ? idComentarioEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioEquipos)) {
            return false;
        }
        ComentarioEquipos other = (ComentarioEquipos) object;
        if ((this.idComentarioEquipo == null && other.idComentarioEquipo != null) || (this.idComentarioEquipo != null && !this.idComentarioEquipo.equals(other.idComentarioEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.ComentarioEquipos[ idComentarioEquipo=" + idComentarioEquipo + " ]";
    }
    
}
