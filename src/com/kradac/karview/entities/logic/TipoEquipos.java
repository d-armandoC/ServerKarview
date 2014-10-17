/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "tipo_equipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEquipos.findAll", query = "SELECT t FROM TipoEquipos t"),
    @NamedQuery(name = "TipoEquipos.findByIdTipoEquipo", query = "SELECT t FROM TipoEquipos t WHERE t.idTipoEquipo = :idTipoEquipo"),
    @NamedQuery(name = "TipoEquipos.findByTipoEquipo", query = "SELECT t FROM TipoEquipos t WHERE t.tipoEquipo = :tipoEquipo")})
public class TipoEquipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_equipo")
    private Integer idTipoEquipo;
    @Basic(optional = false)
    @Column(name = "tipo_equipo")
    private String tipoEquipo;

    public TipoEquipos() {
    }

    public TipoEquipos(Integer idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public TipoEquipos(Integer idTipoEquipo, String tipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
        this.tipoEquipo = tipoEquipo;
    }

    public Integer getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(Integer idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEquipo != null ? idTipoEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEquipos)) {
            return false;
        }
        TipoEquipos other = (TipoEquipos) object;
        if ((this.idTipoEquipo == null && other.idTipoEquipo != null) || (this.idTipoEquipo != null && !this.idTipoEquipo.equals(other.idTipoEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.TipoEquipos[ idTipoEquipo=" + idTipoEquipo + " ]";
    }
    
}
