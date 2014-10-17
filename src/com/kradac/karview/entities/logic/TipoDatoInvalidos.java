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
@Table(name = "tipo_dato_invalidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDatoInvalidos.findAll", query = "SELECT t FROM TipoDatoInvalidos t"),
    @NamedQuery(name = "TipoDatoInvalidos.findByIdTipoDatoInvalido", query = "SELECT t FROM TipoDatoInvalidos t WHERE t.idTipoDatoInvalido = :idTipoDatoInvalido"),
    @NamedQuery(name = "TipoDatoInvalidos.findByTipoDatoInvalido", query = "SELECT t FROM TipoDatoInvalidos t WHERE t.tipoDatoInvalido = :tipoDatoInvalido")})
public class TipoDatoInvalidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_dato_invalido")
    private Integer idTipoDatoInvalido;
    @Basic(optional = false)
    @Column(name = "tipo_dato_invalido")
    private String tipoDatoInvalido;

    public TipoDatoInvalidos() {
    }

    public TipoDatoInvalidos(Integer idTipoDatoInvalido) {
        this.idTipoDatoInvalido = idTipoDatoInvalido;
    }

    public TipoDatoInvalidos(Integer idTipoDatoInvalido, String tipoDatoInvalido) {
        this.idTipoDatoInvalido = idTipoDatoInvalido;
        this.tipoDatoInvalido = tipoDatoInvalido;
    }

    public Integer getIdTipoDatoInvalido() {
        return idTipoDatoInvalido;
    }

    public void setIdTipoDatoInvalido(Integer idTipoDatoInvalido) {
        this.idTipoDatoInvalido = idTipoDatoInvalido;
    }

    public String getTipoDatoInvalido() {
        return tipoDatoInvalido;
    }

    public void setTipoDatoInvalido(String tipoDatoInvalido) {
        this.tipoDatoInvalido = tipoDatoInvalido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDatoInvalido != null ? idTipoDatoInvalido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDatoInvalidos)) {
            return false;
        }
        TipoDatoInvalidos other = (TipoDatoInvalidos) object;
        if ((this.idTipoDatoInvalido == null && other.idTipoDatoInvalido != null) || (this.idTipoDatoInvalido != null && !this.idTipoDatoInvalido.equals(other.idTipoDatoInvalido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.TipoDatoInvalidos[ idTipoDatoInvalido=" + idTipoDatoInvalido + " ]";
    }
    
}
