/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.logic;

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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m"),
    @NamedQuery(name = "Mantenimiento.findByIdVehiculo", query = "SELECT m FROM Mantenimiento m WHERE m.mantenimientoPK.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Mantenimiento.findByIdEstandarVehiculo", query = "SELECT m FROM Mantenimiento m WHERE m.mantenimientoPK.idEstandarVehiculo = :idEstandarVehiculo"),
    @NamedQuery(name = "Mantenimiento.findByValorTipoServicio", query = "SELECT m FROM Mantenimiento m WHERE m.valorTipoServicio = :valorTipoServicio"),
    @NamedQuery(name = "Mantenimiento.findByValorTipoMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.valorTipoMantenimiento = :valorTipoMantenimiento"),
    @NamedQuery(name = "Mantenimiento.findByMkilometraje", query = "SELECT m FROM Mantenimiento m WHERE m.mkilometraje = :mkilometraje"),
    @NamedQuery(name = "Mantenimiento.findByMdias", query = "SELECT m FROM Mantenimiento m WHERE m.mdias = :mdias"),
    @NamedQuery(name = "Mantenimiento.findByMfecha", query = "SELECT m FROM Mantenimiento m WHERE m.mfecha = :mfecha"),
    @NamedQuery(name = "Mantenimiento.findByMobservacion", query = "SELECT m FROM Mantenimiento m WHERE m.mobservacion = :mobservacion"),
    @NamedQuery(name = "Mantenimiento.findByRepaFecha", query = "SELECT m FROM Mantenimiento m WHERE m.repaFecha = :repaFecha"),
    @NamedQuery(name = "Mantenimiento.findByRepaDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.repaDescripcion = :repaDescripcion"),
    @NamedQuery(name = "Mantenimiento.findByRepaObservacion", query = "SELECT m FROM Mantenimiento m WHERE m.repaObservacion = :repaObservacion"),
    @NamedQuery(name = "Mantenimiento.findByRepuMarca", query = "SELECT m FROM Mantenimiento m WHERE m.repuMarca = :repuMarca"),
    @NamedQuery(name = "Mantenimiento.findByRepuModelo", query = "SELECT m FROM Mantenimiento m WHERE m.repuModelo = :repuModelo"),
    @NamedQuery(name = "Mantenimiento.findByRepuCodigo", query = "SELECT m FROM Mantenimiento m WHERE m.repuCodigo = :repuCodigo"),
    @NamedQuery(name = "Mantenimiento.findByRepuSerie", query = "SELECT m FROM Mantenimiento m WHERE m.repuSerie = :repuSerie"),
    @NamedQuery(name = "Mantenimiento.findByRepuEstado", query = "SELECT m FROM Mantenimiento m WHERE m.repuEstado = :repuEstado"),
    @NamedQuery(name = "Mantenimiento.findByKilomConfig", query = "SELECT m FROM Mantenimiento m WHERE m.kilomConfig = :kilomConfig"),
    @NamedQuery(name = "Mantenimiento.findByFechaConfig", query = "SELECT m FROM Mantenimiento m WHERE m.fechaConfig = :fechaConfig"),
    @NamedQuery(name = "Mantenimiento.findByFecha", query = "SELECT m FROM Mantenimiento m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mantenimiento.findByDescripSoat", query = "SELECT m FROM Mantenimiento m WHERE m.descripSoat = :descripSoat"),
    @NamedQuery(name = "Mantenimiento.findByFechaSoatReg", query = "SELECT m FROM Mantenimiento m WHERE m.fechaSoatReg = :fechaSoatReg"),
    @NamedQuery(name = "Mantenimiento.findByFechaSoatVenc", query = "SELECT m FROM Mantenimiento m WHERE m.fechaSoatVenc = :fechaSoatVenc"),
    @NamedQuery(name = "Mantenimiento.findByDescripMatricula", query = "SELECT m FROM Mantenimiento m WHERE m.descripMatricula = :descripMatricula"),
    @NamedQuery(name = "Mantenimiento.findByFechaMatriculaReg", query = "SELECT m FROM Mantenimiento m WHERE m.fechaMatriculaReg = :fechaMatriculaReg"),
    @NamedQuery(name = "Mantenimiento.findByFechaMatriculaVenc", query = "SELECT m FROM Mantenimiento m WHERE m.fechaMatriculaVenc = :fechaMatriculaVenc"),
    @NamedQuery(name = "Mantenimiento.findByDescripSeguro", query = "SELECT m FROM Mantenimiento m WHERE m.descripSeguro = :descripSeguro"),
    @NamedQuery(name = "Mantenimiento.findByFechaSeguroReg", query = "SELECT m FROM Mantenimiento m WHERE m.fechaSeguroReg = :fechaSeguroReg"),
    @NamedQuery(name = "Mantenimiento.findByFechaSeguroVenc", query = "SELECT m FROM Mantenimiento m WHERE m.fechaSeguroVenc = :fechaSeguroVenc"),
    @NamedQuery(name = "Mantenimiento.findByMantenimientocol", query = "SELECT m FROM Mantenimiento m WHERE m.mantenimientocol = :mantenimientocol"),
    @NamedQuery(name = "Mantenimiento.findByResponsable", query = "SELECT m FROM Mantenimiento m WHERE m.responsable = :responsable")})
public class Mantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MantenimientoPK mantenimientoPK;
    @Basic(optional = false)
    @Column(name = "valorTipoServicio")
    private int valorTipoServicio;
    @Basic(optional = false)
    @Column(name = "valorTipoMantenimiento")
    private int valorTipoMantenimiento;
    @Basic(optional = false)
    @Column(name = "mkilometraje")
    private int mkilometraje;
    @Basic(optional = false)
    @Column(name = "mdias")
    private int mdias;
    @Column(name = "mfecha")
    @Temporal(TemporalType.DATE)
    private Date mfecha;
    @Basic(optional = false)
    @Column(name = "mobservacion")
    private String mobservacion;
    @Column(name = "repaFecha")
    @Temporal(TemporalType.DATE)
    private Date repaFecha;
    @Basic(optional = false)
    @Column(name = "repaDescripcion")
    private String repaDescripcion;
    @Basic(optional = false)
    @Column(name = "repaObservacion")
    private String repaObservacion;
    @Basic(optional = false)
    @Column(name = "repuMarca")
    private String repuMarca;
    @Basic(optional = false)
    @Column(name = "repuModelo")
    private String repuModelo;
    @Basic(optional = false)
    @Column(name = "repuCodigo")
    private String repuCodigo;
    @Basic(optional = false)
    @Column(name = "repuSerie")
    private String repuSerie;
    @Basic(optional = false)
    @Column(name = "repuEstado")
    private int repuEstado;
    @Basic(optional = false)
    @Column(name = "kilom_config")
    private int kilomConfig;
    @Basic(optional = false)
    @Column(name = "fecha_config")
    @Temporal(TemporalType.DATE)
    private Date fechaConfig;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
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
    @Column(name = "mantenimientocol")
    private String mantenimientocol;
    @Basic(optional = false)
    @Column(name = "responsable")
    private int responsable;

    public Mantenimiento() {
    }

    public Mantenimiento(MantenimientoPK mantenimientoPK) {
        this.mantenimientoPK = mantenimientoPK;
    }

    public Mantenimiento(MantenimientoPK mantenimientoPK, int valorTipoServicio, int valorTipoMantenimiento, int mkilometraje, int mdias, String mobservacion, String repaDescripcion, String repaObservacion, String repuMarca, String repuModelo, String repuCodigo, String repuSerie, int repuEstado, int kilomConfig, Date fechaConfig, Date fecha, int responsable) {
        this.mantenimientoPK = mantenimientoPK;
        this.valorTipoServicio = valorTipoServicio;
        this.valorTipoMantenimiento = valorTipoMantenimiento;
        this.mkilometraje = mkilometraje;
        this.mdias = mdias;
        this.mobservacion = mobservacion;
        this.repaDescripcion = repaDescripcion;
        this.repaObservacion = repaObservacion;
        this.repuMarca = repuMarca;
        this.repuModelo = repuModelo;
        this.repuCodigo = repuCodigo;
        this.repuSerie = repuSerie;
        this.repuEstado = repuEstado;
        this.kilomConfig = kilomConfig;
        this.fechaConfig = fechaConfig;
        this.fecha = fecha;
        this.responsable = responsable;
    }

    public Mantenimiento(int idVehiculo, int idEstandarVehiculo) {
        this.mantenimientoPK = new MantenimientoPK(idVehiculo, idEstandarVehiculo);
    }

    public MantenimientoPK getMantenimientoPK() {
        return mantenimientoPK;
    }

    public void setMantenimientoPK(MantenimientoPK mantenimientoPK) {
        this.mantenimientoPK = mantenimientoPK;
    }

    public int getValorTipoServicio() {
        return valorTipoServicio;
    }

    public void setValorTipoServicio(int valorTipoServicio) {
        this.valorTipoServicio = valorTipoServicio;
    }

    public int getValorTipoMantenimiento() {
        return valorTipoMantenimiento;
    }

    public void setValorTipoMantenimiento(int valorTipoMantenimiento) {
        this.valorTipoMantenimiento = valorTipoMantenimiento;
    }

    public int getMkilometraje() {
        return mkilometraje;
    }

    public void setMkilometraje(int mkilometraje) {
        this.mkilometraje = mkilometraje;
    }

    public int getMdias() {
        return mdias;
    }

    public void setMdias(int mdias) {
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

    public int getRepuEstado() {
        return repuEstado;
    }

    public void setRepuEstado(int repuEstado) {
        this.repuEstado = repuEstado;
    }

    public int getKilomConfig() {
        return kilomConfig;
    }

    public void setKilomConfig(int kilomConfig) {
        this.kilomConfig = kilomConfig;
    }

    public Date getFechaConfig() {
        return fechaConfig;
    }

    public void setFechaConfig(Date fechaConfig) {
        this.fechaConfig = fechaConfig;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getMantenimientocol() {
        return mantenimientocol;
    }

    public void setMantenimientocol(String mantenimientocol) {
        this.mantenimientocol = mantenimientocol;
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
        hash += (mantenimientoPK != null ? mantenimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.mantenimientoPK == null && other.mantenimientoPK != null) || (this.mantenimientoPK != null && !this.mantenimientoPK.equals(other.mantenimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entitis.Mantenimiento[ mantenimientoPK=" + mantenimientoPK + " ]";
    }
    
}
