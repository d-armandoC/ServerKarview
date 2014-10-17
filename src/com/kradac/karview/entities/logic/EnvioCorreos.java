/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego C
 */
@Entity
@Table(name = "envio_correos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnvioCorreos.findAll", query = "SELECT e FROM EnvioCorreos e"),
    @NamedQuery(name = "EnvioCorreos.findByIdPersona", query = "SELECT e FROM EnvioCorreos e WHERE e.envioCorreosPK.idPersona = :idPersona"),
    @NamedQuery(name = "EnvioCorreos.findByIdSkyEvento", query = "SELECT e FROM EnvioCorreos e WHERE e.envioCorreosPK.idSkyEvento = :idSkyEvento"),
    @NamedQuery(name = "EnvioCorreos.findByIdEmpresa", query = "SELECT e FROM EnvioCorreos e WHERE e.envioCorreosPK.idEmpresa = :idEmpresa")})
public class EnvioCorreos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvioCorreosPK envioCorreosPK;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresas empresas;
    @JoinColumn(name = "id_sky_evento", referencedColumnName = "id_sky_evento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SkyEventos skyEventos;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personas personas;

    public EnvioCorreos() {
    }

    public EnvioCorreos(EnvioCorreosPK envioCorreosPK) {
        this.envioCorreosPK = envioCorreosPK;
    }

    public EnvioCorreos(int idPersona, int idSkyEvento, int idEmpresa) {
        this.envioCorreosPK = new EnvioCorreosPK(idPersona, idSkyEvento, idEmpresa);
    }

    public EnvioCorreosPK getEnvioCorreosPK() {
        return envioCorreosPK;
    }

    public void setEnvioCorreosPK(EnvioCorreosPK envioCorreosPK) {
        this.envioCorreosPK = envioCorreosPK;
    }

    public Empresas getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

    public SkyEventos getSkyEventos() {
        return skyEventos;
    }

    public void setSkyEventos(SkyEventos skyEventos) {
        this.skyEventos = skyEventos;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (envioCorreosPK != null ? envioCorreosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioCorreos)) {
            return false;
        }
        EnvioCorreos other = (EnvioCorreos) object;
        if ((this.envioCorreosPK == null && other.envioCorreosPK != null) || (this.envioCorreosPK != null && !this.envioCorreosPK.equals(other.envioCorreosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.EnvioCorreos[ envioCorreosPK=" + envioCorreosPK + " ]";
    }
    
}
