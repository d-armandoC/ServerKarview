/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego C
 */
@Embeddable
public class RegistrosMantenimientoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    @Basic(optional = false)
    @Column(name = "id_registro")
    private int idRegistro;

    public RegistrosMantenimientoPK() {
    }

    public RegistrosMantenimientoPK(int idVehiculo, int idRegistro) {
        this.idVehiculo = idVehiculo;
        this.idRegistro = idRegistro;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVehiculo;
        hash += (int) idRegistro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrosMantenimientoPK)) {
            return false;
        }
        RegistrosMantenimientoPK other = (RegistrosMantenimientoPK) object;
        if (this.idVehiculo != other.idVehiculo) {
            return false;
        }
        if (this.idRegistro != other.idRegistro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kradac.karview.entities.logic.RegistrosMantenimientoPK[ idVehiculo=" + idVehiculo + ", idRegistro=" + idRegistro + " ]";
    }
    
}
