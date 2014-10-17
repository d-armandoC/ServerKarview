/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.ClaseVehiculos;
import com.kradac.karview.entities.logic.Equipos;
import com.kradac.karview.entities.logic.Personas;
import com.kradac.karview.entities.logic.Empresas;
import com.kradac.karview.entities.logic.Vehiculos;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego C
 */
public class VehiculosJpaController implements Serializable {

    public VehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehiculos vehiculos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClaseVehiculos idClaseVehiculo = vehiculos.getIdClaseVehiculo();
            if (idClaseVehiculo != null) {
                idClaseVehiculo = em.getReference(idClaseVehiculo.getClass(), idClaseVehiculo.getIdClaseVehiculo());
                vehiculos.setIdClaseVehiculo(idClaseVehiculo);
            }
            Equipos idEquipo = vehiculos.getIdEquipo();
            if (idEquipo != null) {
                idEquipo = em.getReference(idEquipo.getClass(), idEquipo.getIdEquipo());
                vehiculos.setIdEquipo(idEquipo);
            }
            Personas idPersona = vehiculos.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                vehiculos.setIdPersona(idPersona);
            }
            Empresas idEmpresa = vehiculos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                vehiculos.setIdEmpresa(idEmpresa);
            }
            em.persist(vehiculos);
            if (idClaseVehiculo != null) {
                idClaseVehiculo.getVehiculosCollection().add(vehiculos);
                idClaseVehiculo = em.merge(idClaseVehiculo);
            }
            if (idEquipo != null) {
                idEquipo.getVehiculosCollection().add(vehiculos);
                idEquipo = em.merge(idEquipo);
            }
            if (idPersona != null) {
                idPersona.getVehiculosCollection().add(vehiculos);
                idPersona = em.merge(idPersona);
            }
            if (idEmpresa != null) {
                idEmpresa.getVehiculosCollection().add(vehiculos);
                idEmpresa = em.merge(idEmpresa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehiculos vehiculos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculos persistentVehiculos = em.find(Vehiculos.class, vehiculos.getIdVehiculo());
            ClaseVehiculos idClaseVehiculoOld = persistentVehiculos.getIdClaseVehiculo();
            ClaseVehiculos idClaseVehiculoNew = vehiculos.getIdClaseVehiculo();
            Equipos idEquipoOld = persistentVehiculos.getIdEquipo();
            Equipos idEquipoNew = vehiculos.getIdEquipo();
            Personas idPersonaOld = persistentVehiculos.getIdPersona();
            Personas idPersonaNew = vehiculos.getIdPersona();
            Empresas idEmpresaOld = persistentVehiculos.getIdEmpresa();
            Empresas idEmpresaNew = vehiculos.getIdEmpresa();
            if (idClaseVehiculoNew != null) {
                idClaseVehiculoNew = em.getReference(idClaseVehiculoNew.getClass(), idClaseVehiculoNew.getIdClaseVehiculo());
                vehiculos.setIdClaseVehiculo(idClaseVehiculoNew);
            }
            if (idEquipoNew != null) {
                idEquipoNew = em.getReference(idEquipoNew.getClass(), idEquipoNew.getIdEquipo());
                vehiculos.setIdEquipo(idEquipoNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                vehiculos.setIdPersona(idPersonaNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                vehiculos.setIdEmpresa(idEmpresaNew);
            }
            vehiculos = em.merge(vehiculos);
            if (idClaseVehiculoOld != null && !idClaseVehiculoOld.equals(idClaseVehiculoNew)) {
                idClaseVehiculoOld.getVehiculosCollection().remove(vehiculos);
                idClaseVehiculoOld = em.merge(idClaseVehiculoOld);
            }
            if (idClaseVehiculoNew != null && !idClaseVehiculoNew.equals(idClaseVehiculoOld)) {
                idClaseVehiculoNew.getVehiculosCollection().add(vehiculos);
                idClaseVehiculoNew = em.merge(idClaseVehiculoNew);
            }
            if (idEquipoOld != null && !idEquipoOld.equals(idEquipoNew)) {
                idEquipoOld.getVehiculosCollection().remove(vehiculos);
                idEquipoOld = em.merge(idEquipoOld);
            }
            if (idEquipoNew != null && !idEquipoNew.equals(idEquipoOld)) {
                idEquipoNew.getVehiculosCollection().add(vehiculos);
                idEquipoNew = em.merge(idEquipoNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getVehiculosCollection().remove(vehiculos);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getVehiculosCollection().add(vehiculos);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getVehiculosCollection().remove(vehiculos);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getVehiculosCollection().add(vehiculos);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehiculos.getIdVehiculo();
                if (findVehiculos(id) == null) {
                    throw new NonexistentEntityException("The vehiculos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculos vehiculos;
            try {
                vehiculos = em.getReference(Vehiculos.class, id);
                vehiculos.getIdVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculos with id " + id + " no longer exists.", enfe);
            }
            ClaseVehiculos idClaseVehiculo = vehiculos.getIdClaseVehiculo();
            if (idClaseVehiculo != null) {
                idClaseVehiculo.getVehiculosCollection().remove(vehiculos);
                idClaseVehiculo = em.merge(idClaseVehiculo);
            }
            Equipos idEquipo = vehiculos.getIdEquipo();
            if (idEquipo != null) {
                idEquipo.getVehiculosCollection().remove(vehiculos);
                idEquipo = em.merge(idEquipo);
            }
            Personas idPersona = vehiculos.getIdPersona();
            if (idPersona != null) {
                idPersona.getVehiculosCollection().remove(vehiculos);
                idPersona = em.merge(idPersona);
            }
            Empresas idEmpresa = vehiculos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getVehiculosCollection().remove(vehiculos);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(vehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehiculos> findVehiculosEntities() {
        return findVehiculosEntities(true, -1, -1);
    }

    public List<Vehiculos> findVehiculosEntities(int maxResults, int firstResult) {
        return findVehiculosEntities(false, maxResults, firstResult);
    }

    private List<Vehiculos> findVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehiculos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vehiculos findVehiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehiculos> rt = cq.from(Vehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public Vehiculos findVehiculosByEquipo(String equipo) {
         System.out.println("consulta"+ equipo);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Vehiculos> qry;
            qry = em.createQuery("SELECT v FROM Vehiculos v WHERE v.idEquipo.equipo = :equipo", Vehiculos.class);
            qry.setParameter("equipo", equipo);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
