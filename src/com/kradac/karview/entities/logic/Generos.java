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
@Table(name = "generos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Generos.findAll", query = "SELECT g FROM Generos g"),
    @NamedQuery(name = "Generos.findByIdGenero", query = "SELECT g FROM Generos g WHERE g.idGenero = :idGenero"),
    @NamedQuery(name = "Generos.findByGenero", query = "SELECT g FROM Generos g WHERE g.genero = :genero")})
public class Generos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero")
    private Integer idGenero;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenero")
    private Collection<Personas> personasCollection;

    public Generos() {
    }

    public Generos(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Generos(Integer idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @XmlTransient
    public Collection<Personas> getPersonasCollection() {
        return personasCollection;
    }

    public void setPersonasCollection(Collection<Personas> personasCollection) {
        this.personasCollection = personasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generos)) {
            return false;
        }
        Generos other = (Generos) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Generos[ idGenero=" + idGenero + " ]";
    }
    
}
