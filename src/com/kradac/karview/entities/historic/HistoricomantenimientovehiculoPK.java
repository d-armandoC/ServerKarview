/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.historic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego C
 */
@Embeddable
public class HistoricomantenimientovehiculoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    @Basic(optional = false)
    @Column(name = "id_estandar_vehiculo")
    private int idEstandarVehiculo;

    public HistoricomantenimientovehiculoPK() {
    }

    public HistoricomantenimientovehiculoPK(int idVehiculo, int idEstandarVehiculo) {
        this.idVehiculo = idVehiculo;
        this.idEstandarVehiculo = idEstandarVehiculo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdEstandarVehiculo() {
        return idEstandarVehiculo;
    }

    public void setIdEstandarVehiculo(int idEstandarVehiculo) {
        this.idEstandarVehiculo = idEstandarVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVehiculo;
        hash += (int) idEstandarVehiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricomantenimientovehiculoPK)) {
            return false;
        }
        HistoricomantenimientovehiculoPK other = (HistoricomantenimientovehiculoPK) object;
        if (this.idVehiculo != other.idVehiculo) {
            return false;
        }
        if (this.idEstandarVehiculo != other.idEstandarVehiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.historic.HistoricomantenimientovehiculoPK[ idVehiculo=" + idVehiculo + ", idEstandarVehiculo=" + idEstandarVehiculo + " ]";
    }
    
}
