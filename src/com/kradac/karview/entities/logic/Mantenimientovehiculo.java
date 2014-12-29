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
@Table(name = "mantenimientovehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimientovehiculo.findAll", query = "SELECT m FROM Mantenimientovehiculo m"),
    @NamedQuery(name = "Mantenimientovehiculo.findByIdVehiculo", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mantenimientovehiculoPK.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Mantenimientovehiculo.findByIdEstandarVehiculo", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mantenimientovehiculoPK.idEstandarVehiculo = :idEstandarVehiculo"),
    @NamedQuery(name = "Mantenimientovehiculo.findByHora", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.hora = :hora"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaRegistro", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Mantenimientovehiculo.findByValorTipoServicio", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.valorTipoServicio = :valorTipoServicio"),
    @NamedQuery(name = "Mantenimientovehiculo.findByKilomConfig", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.kilomConfig = :kilomConfig"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaConfig", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaConfig = :fechaConfig"),
    @NamedQuery(name = "Mantenimientovehiculo.findByValorTipoMantenimiento", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.valorTipoMantenimiento = :valorTipoMantenimiento"),
    @NamedQuery(name = "Mantenimientovehiculo.findByMkilometraje", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mkilometraje = :mkilometraje"),
    @NamedQuery(name = "Mantenimientovehiculo.findByMdias", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mdias = :mdias"),
    @NamedQuery(name = "Mantenimientovehiculo.findByMfecha", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mfecha = :mfecha"),
    @NamedQuery(name = "Mantenimientovehiculo.findByMobservacion", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.mobservacion = :mobservacion"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepaFecha", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repaFecha = :repaFecha"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepaDescripcion", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repaDescripcion = :repaDescripcion"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepaObservacion", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repaObservacion = :repaObservacion"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepuMarca", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repuMarca = :repuMarca"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepuModelo", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repuModelo = :repuModelo"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepuCodigo", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repuCodigo = :repuCodigo"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepuSerie", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repuSerie = :repuSerie"),
    @NamedQuery(name = "Mantenimientovehiculo.findByRepuEstado", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.repuEstado = :repuEstado"),
    @NamedQuery(name = "Mantenimientovehiculo.findByDescripSoat", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.descripSoat = :descripSoat"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaSoatReg", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaSoatReg = :fechaSoatReg"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaSoatVenc", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaSoatVenc = :fechaSoatVenc"),
    @NamedQuery(name = "Mantenimientovehiculo.findByDescripMatricula", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.descripMatricula = :descripMatricula"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaMatriculaReg", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaMatriculaReg = :fechaMatriculaReg"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaMatriculaVenc", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaMatriculaVenc = :fechaMatriculaVenc"),
    @NamedQuery(name = "Mantenimientovehiculo.findByDescripSeguro", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.descripSeguro = :descripSeguro"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaSeguroReg", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaSeguroReg = :fechaSeguroReg"),
    @NamedQuery(name = "Mantenimientovehiculo.findByFechaSeguroVenc", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.fechaSeguroVenc = :fechaSeguroVenc"),
    @NamedQuery(name = "Mantenimientovehiculo.findByResponsable", query = "SELECT m FROM Mantenimientovehiculo m WHERE m.responsable = :responsable")})
public class Mantenimientovehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MantenimientovehiculoPK mantenimientovehiculoPK;
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

    public Mantenimientovehiculo() {
    }

    public Mantenimientovehiculo(MantenimientovehiculoPK mantenimientovehiculoPK) {
        this.mantenimientovehiculoPK = mantenimientovehiculoPK;
    }

    public Mantenimientovehiculo(MantenimientovehiculoPK mantenimientovehiculoPK, int valorTipoServicio, int responsable) {
        this.mantenimientovehiculoPK = mantenimientovehiculoPK;
        this.valorTipoServicio = valorTipoServicio;
        this.responsable = responsable;
    }

    public Mantenimientovehiculo(int idVehiculo, int idEstandarVehiculo) {
        this.mantenimientovehiculoPK = new MantenimientovehiculoPK(idVehiculo, idEstandarVehiculo);
    }

    public MantenimientovehiculoPK getMantenimientovehiculoPK() {
        return mantenimientovehiculoPK;
    }

    public void setMantenimientovehiculoPK(MantenimientovehiculoPK mantenimientovehiculoPK) {
        this.mantenimientovehiculoPK = mantenimientovehiculoPK;
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
        hash += (mantenimientovehiculoPK != null ? mantenimientovehiculoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimientovehiculo)) {
            return false;
        }
        Mantenimientovehiculo other = (Mantenimientovehiculo) object;
        if ((this.mantenimientovehiculoPK == null && other.mantenimientovehiculoPK != null) || (this.mantenimientovehiculoPK != null && !this.mantenimientovehiculoPK.equals(other.mantenimientovehiculoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.logic.Mantenimientovehiculo[ mantenimientovehiculoPK=" + mantenimientovehiculoPK + " ]";
    }
    
}
