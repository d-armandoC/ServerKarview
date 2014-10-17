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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "comandos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comandos.findAll", query = "SELECT c FROM Comandos c"),
    @NamedQuery(name = "Comandos.findByIdUsuario", query = "SELECT c FROM Comandos c WHERE c.comandosPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Comandos.findByIdEquipo", query = "SELECT c FROM Comandos c WHERE c.comandosPK.idEquipo = :idEquipo"),
    @NamedQuery(name = "Comandos.findByIdTipoEstadoCmd", query = "SELECT c FROM Comandos c WHERE c.idTipoEstadoCmd = :idTipoEstadoCmd"),
    @NamedQuery(name = "Comandos.findByComando", query = "SELECT c FROM Comandos c WHERE c.comando = :comando"),
    @NamedQuery(name = "Comandos.findByRespuesta", query = "SELECT c FROM Comandos c WHERE c.respuesta = :respuesta"),
    @NamedQuery(name = "Comandos.findByFechaHoraRegistro", query = "SELECT c FROM Comandos c WHERE c.comandosPK.fechaHoraRegistro = :fechaHoraRegistro"),
    @NamedQuery(name = "Comandos.findByFechaHoraEnvio", query = "SELECT c FROM Comandos c WHERE c.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "Comandos.findByFechaHoraRespuesta", query = "SELECT c FROM Comandos c WHERE c.fechaHoraRespuesta = :fechaHoraRespuesta")})
public class Comandos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComandosPK comandosPK;
    @Basic(optional = false)
    @Column(name = "id_tipo_estado_cmd")
    private int idTipoEstadoCmd;
    @Basic(optional = false)
    @Column(name = "comando")
    private String comando;
    @Basic(optional = false)
    @Column(name = "respuesta")
    private String respuesta;
    @Basic(optional = false)
    @Column(name = "fecha_hora_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;
    @Basic(optional = false)
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;

    public Comandos() {
    }

    public Comandos(ComandosPK comandosPK) {
        this.comandosPK = comandosPK;
    }

    public Comandos(ComandosPK comandosPK, int idTipoEstadoCmd, String comando, String respuesta, Date fechaHoraEnvio, Date fechaHoraRespuesta) {
        this.comandosPK = comandosPK;
        this.idTipoEstadoCmd = idTipoEstadoCmd;
        this.comando = comando;
        this.respuesta = respuesta;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Comandos(int idUsuario, int idEquipo, Date fechaHoraRegistro) {
        this.comandosPK = new ComandosPK(idUsuario, idEquipo, fechaHoraRegistro);
    }

    public ComandosPK getComandosPK() {
        return comandosPK;
    }

    public void setComandosPK(ComandosPK comandosPK) {
        this.comandosPK = comandosPK;
    }

    public int getIdTipoEstadoCmd() {
        return idTipoEstadoCmd;
    }

    public void setIdTipoEstadoCmd(int idTipoEstadoCmd) {
        this.idTipoEstadoCmd = idTipoEstadoCmd;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comandosPK != null ? comandosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comandos)) {
            return false;
        }
        Comandos other = (Comandos) object;
        if ((this.comandosPK == null && other.comandosPK != null) || (this.comandosPK != null && !this.comandosPK.equals(other.comandosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.Comandos[ comandosPK=" + comandosPK + " ]";
    }
    
}
