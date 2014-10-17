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
@Table(name = "tipo_estado_cmds")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstadoCmds.findAll", query = "SELECT t FROM TipoEstadoCmds t"),
    @NamedQuery(name = "TipoEstadoCmds.findByIdTipoEstadoCmd", query = "SELECT t FROM TipoEstadoCmds t WHERE t.idTipoEstadoCmd = :idTipoEstadoCmd"),
    @NamedQuery(name = "TipoEstadoCmds.findByTipoEstadoCmd", query = "SELECT t FROM TipoEstadoCmds t WHERE t.tipoEstadoCmd = :tipoEstadoCmd")})
public class TipoEstadoCmds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_estado_cmd")
    private Integer idTipoEstadoCmd;
    @Column(name = "tipo_estado_cmd")
    private String tipoEstadoCmd;

    public TipoEstadoCmds() {
    }

    public TipoEstadoCmds(Integer idTipoEstadoCmd) {
        this.idTipoEstadoCmd = idTipoEstadoCmd;
    }

    public Integer getIdTipoEstadoCmd() {
        return idTipoEstadoCmd;
    }

    public void setIdTipoEstadoCmd(Integer idTipoEstadoCmd) {
        this.idTipoEstadoCmd = idTipoEstadoCmd;
    }

    public String getTipoEstadoCmd() {
        return tipoEstadoCmd;
    }

    public void setTipoEstadoCmd(String tipoEstadoCmd) {
        this.tipoEstadoCmd = tipoEstadoCmd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEstadoCmd != null ? idTipoEstadoCmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstadoCmds)) {
            return false;
        }
        TipoEstadoCmds other = (TipoEstadoCmds) object;
        if ((this.idTipoEstadoCmd == null && other.idTipoEstadoCmd != null) || (this.idTipoEstadoCmd != null && !this.idTipoEstadoCmd.equals(other.idTipoEstadoCmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.TipoEstadoCmds[ idTipoEstadoCmd=" + idTipoEstadoCmd + " ]";
    }
    
}
