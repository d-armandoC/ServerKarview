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
@Table(name = "historicomantenimientovehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicomantenimientovehiculo.findAll", query = "SELECT h FROM Historicomantenimientovehiculo h"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByIdVehiculo", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.historicomantenimientovehiculoPK.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByIdEstandarVehiculo", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.historicomantenimientovehiculoPK.idEstandarVehiculo = :idEstandarVehiculo"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByHora", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.hora = :hora"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaRegistro", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByValorTipoServicio", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.valorTipoServicio = :valorTipoServicio"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByKilomConfig", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.kilomConfig = :kilomConfig"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaConfig", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaConfig = :fechaConfig"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByValorTipoMantenimiento", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.valorTipoMantenimiento = :valorTipoMantenimiento"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByMkilometraje", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.mkilometraje = :mkilometraje"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByMdias", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.mdias = :mdias"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByMfecha", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.mfecha = :mfecha"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByMobservacion", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.mobservacion = :mobservacion"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepaFecha", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repaFecha = :repaFecha"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepaDescripcion", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repaDescripcion = :repaDescripcion"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepaObservacion", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repaObservacion = :repaObservacion"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepuMarca", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repuMarca = :repuMarca"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepuModelo", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repuModelo = :repuModelo"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepuCodigo", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repuCodigo = :repuCodigo"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepuSerie", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repuSerie = :repuSerie"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByRepuEstado", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.repuEstado = :repuEstado"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByDescripSoat", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.descripSoat = :descripSoat"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaSoatReg", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaSoatReg = :fechaSoatReg"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaSoatVenc", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaSoatVenc = :fechaSoatVenc"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByDescripMatricula", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.descripMatricula = :descripMatricula"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaMatriculaReg", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaMatriculaReg = :fechaMatriculaReg"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaMatriculaVenc", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaMatriculaVenc = :fechaMatriculaVenc"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByDescripSeguro", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.descripSeguro = :descripSeguro"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaSeguroReg", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaSeguroReg = :fechaSeguroReg"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByFechaSeguroVenc", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.fechaSeguroVenc = :fechaSeguroVenc"),
    @NamedQuery(name = "Historicomantenimientovehiculo.findByResponsable", query = "SELECT h FROM Historicomantenimientovehiculo h WHERE h.responsable = :responsable")})
public class Historicomantenimientovehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricomantenimientovehiculoPK historicomantenimientovehiculoPK;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "valorTipoServicio")
    private int valorTipoServicio;
    @Column(name = "kilom_config")
    private Integer kilomConfig;
    @Column(name = "fecha_config")
    @Temporal(TemporalType.DATE)
    private Date fechaConfig;
    @Column(name = "valorTipoMantenimiento")
    private Integer valorTipoMantenimiento;
    @Column(name = "mkilometraje")
    private Integer mkilometraje;
    @Column(name = "mdias")
    private Integer mdias;
    @Column(name = "mfecha")
    @Temporal(TemporalType.DATE)
    private Date mfecha;
    @Column(name = "mobservacion")
    private String mobservacion;
    @Column(name = "repaFecha")
    @Temporal(TemporalType.DATE)
    private Date repaFecha;
    @Column(name = "repaDescripcion")
    private String repaDescripcion;
    @Column(name = "repaObservacion")
    private String repaObservacion;
    @Column(name = "repuMarca")
    private String repuMarca;
    @Column(name = "repuModelo")
    private String repuModelo;
    @Column(name = "repuCodigo")
    private String repuCodigo;
    @Column(name = "repuSerie")
    private String repuSerie;
    @Column(name = "repuEstado")
    private Integer repuEstado;
    @Column(name = "descripSoat")
    private String descripSoat;
    @Column(name = "fechaSoatReg")
    @Temporal(TemporalType.DATE)
    private Date fechaSoatReg;
    @Column(name = "fechaSoatVenc")
    @Temporal(TemporalType.DATE)
    private Date fechaSoatVenc;
    @Column(name = "descripMatricula")
    private String descripMatricula;
    @Column(name = "fechaMatriculaReg")
    @Temporal(TemporalType.DATE)
    private Date fechaMatriculaReg;
    @Column(name = "fechaMatriculaVenc")
    @Temporal(TemporalType.DATE)
    private Date fechaMatriculaVenc;
    @Column(name = "descripSeguro")
    private String descripSeguro;
    @Column(name = "fechaSeguroReg")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguroReg;
    @Column(name = "fechaSeguroVenc")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguroVenc;
    @Basic(optional = false)
    @Column(name = "responsable")
    private int responsable;

    public Historicomantenimientovehiculo() {
    }

    public Historicomantenimientovehiculo(HistoricomantenimientovehiculoPK historicomantenimientovehiculoPK) {
        this.historicomantenimientovehiculoPK = historicomantenimientovehiculoPK;
    }

    public Historicomantenimientovehiculo(HistoricomantenimientovehiculoPK historicomantenimientovehiculoPK, int valorTipoServicio, int responsable) {
        this.historicomantenimientovehiculoPK = historicomantenimientovehiculoPK;
        this.valorTipoServicio = valorTipoServicio;
        this.responsable = responsable;
    }

    public Historicomantenimientovehiculo(int idVehiculo, int idEstandarVehiculo) {
        this.historicomantenimientovehiculoPK = new HistoricomantenimientovehiculoPK(idVehiculo, idEstandarVehiculo);
    }

    public HistoricomantenimientovehiculoPK getHistoricomantenimientovehiculoPK() {
        return historicomantenimientovehiculoPK;
    }

    public void setHistoricomantenimientovehiculoPK(HistoricomantenimientovehiculoPK historicomantenimientovehiculoPK) {
        this.historicomantenimientovehiculoPK = historicomantenimientovehiculoPK;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getValorTipoServicio() {
        return valorTipoServicio;
    }

    public void setValorTipoServicio(int valorTipoServicio) {
        this.valorTipoServicio = valorTipoServicio;
    }

    public Integer getKilomConfig() {
        return kilomConfig;
    }

    public void setKilomConfig(Integer kilomConfig) {
        this.kilomConfig = kilomConfig;
    }

    public Date getFechaConfig() {
        return fechaConfig;
    }

    public void setFechaConfig(Date fechaConfig) {
        this.fechaConfig = fechaConfig;
    }

    public Integer getValorTipoMantenimiento() {
        return valorTipoMantenimiento;
    }

    public void setValorTipoMantenimiento(Integer valorTipoMantenimiento) {
        this.valorTipoMantenimiento = valorTipoMantenimiento;
    }

    public Integer getMkilometraje() {
        return mkilometraje;
    }

    public void setMkilometraje(Integer mkilometraje) {
        this.mkilometraje = mkilometraje;
    }

    public Integer getMdias() {
        return mdias;
    }

    public void setMdias(Integer mdias) {
        this.mdias = mdias;
    }

    public Date getMfecha() {
        return mfecha;
    }

    public void setMfecha(Date mfecha) {
        this.mfecha = mfecha;
    }

    public String getMobservacion() {
        return mobservacion;
    }

    public void setMobservacion(String mobservacion) {
        this.mobservacion = mobservacion;
    }

    public Date getRepaFecha() {
        return repaFecha;
    }

    public void setRepaFecha(Date repaFecha) {
        this.repaFecha = repaFecha;
    }

    public String getRepaDescripcion() {
        return repaDescripcion;
    }

    public void setRepaDescripcion(String repaDescripcion) {
        this.repaDescripcion = repaDescripcion;
    }

    public String getRepaObservacion() {
        return repaObservacion;
    }

    public void setRepaObservacion(String repaObservacion) {
        this.repaObservacion = repaObservacion;
    }

    public String getRepuMarca() {
        return repuMarca;
    }

    public void setRepuMarca(String repuMarca) {
        this.repuMarca = repuMarca;
    }

    public String getRepuModelo() {
        return repuModelo;
    }

    public void setRepuModelo(String repuModelo) {
        this.repuModelo = repuModelo;
    }

    public String getRepuCodigo() {
        return repuCodigo;
    }

    public void setRepuCodigo(String repuCodigo) {
        this.repuCodigo = repuCodigo;
    }

    public String getRepuSerie() {
        return repuSerie;
    }

    public void setRepuSerie(String repuSerie) {
        this.repuSerie = repuSerie;
    }

    public Integer getRepuEstado() {
        return repuEstado;
    }

    public void setRepuEstado(Integer repuEstado) {
        this.repuEstado = repuEstado;
    }

    public String getDescripSoat() {
        return descripSoat;
    }

    public void setDescripSoat(String descripSoat) {
        this.descripSoat = descripSoat;
    }

    public Date getFechaSoatReg() {
        return fechaSoatReg;
    }

    public void setFechaSoatReg(Date fechaSoatReg) {
        this.fechaSoatReg = fechaSoatReg;
    }

    public Date getFechaSoatVenc() {
        return fechaSoatVenc;
    }

    public void setFechaSoatVenc(Date fechaSoatVenc) {
        this.fechaSoatVenc = fechaSoatVenc;
    }

    public String getDescripMatricula() {
        return descripMatricula;
    }

    public void setDescripMatricula(String descripMatricula) {
        this.descripMatricula = descripMatricula;
    }

    public Date getFechaMatriculaReg() {
        return fechaMatriculaReg;
    }

    public void setFechaMatriculaReg(Date fechaMatriculaReg) {
        this.fechaMatriculaReg = fechaMatriculaReg;
    }

    public Date getFechaMatriculaVenc() {
        return fechaMatriculaVenc;
    }

    public void setFechaMatriculaVenc(Date fechaMatriculaVenc) {
        this.fechaMatriculaVenc = fechaMatriculaVenc;
    }

    public String getDescripSeguro() {
        return descripSeguro;
    }

    public void setDescripSeguro(String descripSeguro) {
        this.descripSeguro = descripSeguro;
    }

    public Date getFechaSeguroReg() {
        return fechaSeguroReg;
    }

    public void setFechaSeguroReg(Date fechaSeguroReg) {
        this.fechaSeguroReg = fechaSeguroReg;
    }

    public Date getFechaSeguroVenc() {
        return fechaSeguroVenc;
    }

    public void setFechaSeguroVenc(Date fechaSeguroVenc) {
        this.fechaSeguroVenc = fechaSeguroVenc;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historicomantenimientovehiculoPK != null ? historicomantenimientovehiculoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicomantenimientovehiculo)) {
            return false;
        }
        Historicomantenimientovehiculo other = (Historicomantenimientovehiculo) object;
        if ((this.historicomantenimientovehiculoPK == null && other.historicomantenimientovehiculoPK != null) || (this.historicomantenimientovehiculoPK != null && !this.historicomantenimientovehiculoPK.equals(other.historicomantenimientovehiculoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.Historicomantenimientovehiculo[ historicomantenimientovehiculoPK=" + historicomantenimientovehiculoPK + " ]";
    }
    
}
