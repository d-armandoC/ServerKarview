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
@Table(name = "sky_eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SkyEventos.findAll", query = "SELECT s FROM SkyEventos s"),
    @NamedQuery(name = "SkyEventos.findByIdSkyEvento", query = "SELECT s FROM SkyEventos s WHERE s.idSkyEvento = :idSkyEvento"),
    @NamedQuery(name = "SkyEventos.findByEvento", query = "SELECT s FROM SkyEventos s WHERE s.evento = :evento"),
    @NamedQuery(name = "SkyEventos.findByParametro", query = "SELECT s FROM SkyEventos s WHERE s.parametro = :parametro"),
    @NamedQuery(name = "SkyEventos.findBySkyEvento", query = "SELECT s FROM SkyEventos s WHERE s.skyEvento = :skyEvento"),
    @NamedQuery(name = "SkyEventos.findByAcronimo", query = "SELECT s FROM SkyEventos s WHERE s.acronimo = :acronimo"),
    @NamedQuery(name = "SkyEventos.findByColor", query = "SELECT s FROM SkyEventos s WHERE s.color = :color"),
    @NamedQuery(name = "SkyEventos.findByObservacion", query = "SELECT s FROM SkyEventos s WHERE s.observacion = :observacion"),
    @NamedQuery(name = "SkyEventos.findByMensaje", query = "SELECT s FROM SkyEventos s WHERE s.mensaje = :mensaje")})
public class SkyEventos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sky_evento")
    private Integer idSkyEvento;
    @Column(name = "evento")
    private Short evento;
    @Column(name = "parametro")
    private Short parametro;
    @Column(name = "sky_evento")
    private String skyEvento;
    @Column(name = "acronimo")
    private String acronimo;
    @Column(name = "color")
    private String color;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "mensaje")
    private String mensaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skyEventos")
    private Collection<EnvioCorreos> envioCorreosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSkyEvento")
    private Collection<UltimoDatoSkps> ultimoDatoSkpsCollection;

    public SkyEventos() {
    }

    public SkyEventos(Integer idSkyEvento) {
        this.idSkyEvento = idSkyEvento;
    }

    public Integer getIdSkyEvento() {
        return idSkyEvento;
    }

    public void setIdSkyEvento(Integer idSkyEvento) {
        this.idSkyEvento = idSkyEvento;
    }

    public Short getEvento() {
        return evento;
    }

    public void setEvento(Short evento) {
        this.evento = evento;
    }

    public Short getParametro() {
        return parametro;
    }

    public void setParametro(Short parametro) {
        this.parametro = parametro;
    }

    public String getSkyEvento() {
        return skyEvento;
    }

    public void setSkyEvento(String skyEvento) {
        this.skyEvento = skyEvento;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @XmlTransient
    public Collection<EnvioCorreos> getEnvioCorreosCollection() {
        return envioCorreosCollection;
    }

    public void setEnvioCorreosCollection(Collection<EnvioCorreos> envioCorreosCollection) {
        this.envioCorreosCollection = envioCorreosCollection;
    }

    @XmlTransient
    public Collection<UltimoDatoSkps> getUltimoDatoSkpsCollection() {
        return ultimoDatoSkpsCollection;
    }

    public void setUltimoDatoSkpsCollection(Collection<UltimoDatoSkps> ultimoDatoSkpsCollection) {
        this.ultimoDatoSkpsCollection = ultimoDatoSkpsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSkyEvento != null ? idSkyEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkyEventos)) {
            return false;
        }
        SkyEventos other = (SkyEventos) object;
        if ((this.idSkyEvento == null && other.idSkyEvento != null) || (this.idSkyEvento != null && !this.idSkyEvento.equals(other.idSkyEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.SkyEventos[ idSkyEvento=" + idSkyEvento + " ]";
    }
    
}
