/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "rol_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUsuarios.findAll", query = "SELECT r FROM RolUsuarios r"),
    @NamedQuery(name = "RolUsuarios.findByIdRolUsuario", query = "SELECT r FROM RolUsuarios r WHERE r.idRolUsuario = :idRolUsuario"),
    @NamedQuery(name = "RolUsuarios.findByNombre", query = "SELECT r FROM RolUsuarios r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RolUsuarios.findByDescripcion", query = "SELECT r FROM RolUsuarios r WHERE r.descripcion = :descripcion")})
public class RolUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol_usuario")
    private Integer idRolUsuario;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolUsuario")
    private Collection<Usuarios> usuariosCollection;

    public RolUsuarios() {
    }

    public RolUsuarios(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public RolUsuarios(Integer idRolUsuario, String nombre, String descripcion) {
        this.idRolUsuario = idRolUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolUsuario != null ? idRolUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuarios)) {
            return false;
        }
        RolUsuarios other = (RolUsuarios) object;
        if ((this.idRolUsuario == null && other.idRolUsuario != null) || (this.idRolUsuario != null && !this.idRolUsuario.equals(other.idRolUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.RolUsuarios[ idRolUsuario=" + idRolUsuario + " ]";
    }
    
}
