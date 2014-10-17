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
@Table(name = "cmd_predefinidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmdPredefinidos.findAll", query = "SELECT c FROM CmdPredefinidos c"),
    @NamedQuery(name = "CmdPredefinidos.findByIdCmdPredefinido", query = "SELECT c FROM CmdPredefinidos c WHERE c.idCmdPredefinido = :idCmdPredefinido"),
    @NamedQuery(name = "CmdPredefinidos.findByCmdPredefinido", query = "SELECT c FROM CmdPredefinidos c WHERE c.cmdPredefinido = :cmdPredefinido"),
    @NamedQuery(name = "CmdPredefinidos.findByDescripcion", query = "SELECT c FROM CmdPredefinidos c WHERE c.descripcion = :descripcion")})
public class CmdPredefinidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cmd_predefinido")
    private Integer idCmdPredefinido;
    @Basic(optional = false)
    @Column(name = "cmd_predefinido")
    private String cmdPredefinido;
    @Column(name = "descripcion")
    private String descripcion;

    public CmdPredefinidos() {
    }

    public CmdPredefinidos(Integer idCmdPredefinido) {
        this.idCmdPredefinido = idCmdPredefinido;
    }

    public CmdPredefinidos(Integer idCmdPredefinido, String cmdPredefinido) {
        this.idCmdPredefinido = idCmdPredefinido;
        this.cmdPredefinido = cmdPredefinido;
    }

    public Integer getIdCmdPredefinido() {
        return idCmdPredefinido;
    }

    public void setIdCmdPredefinido(Integer idCmdPredefinido) {
        this.idCmdPredefinido = idCmdPredefinido;
    }

    public String getCmdPredefinido() {
        return cmdPredefinido;
    }

    public void setCmdPredefinido(String cmdPredefinido) {
        this.cmdPredefinido = cmdPredefinido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCmdPredefinido != null ? idCmdPredefinido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmdPredefinidos)) {
            return false;
        }
        CmdPredefinidos other = (CmdPredefinidos) object;
        if ((this.idCmdPredefinido == null && other.idCmdPredefinido != null) || (this.idCmdPredefinido != null && !this.idCmdPredefinido.equals(other.idCmdPredefinido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.CmdPredefinidos[ idCmdPredefinido=" + idCmdPredefinido + " ]";
    }
    
}
