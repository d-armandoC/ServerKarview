/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.EstandarVehiculos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego C
 */
public class EstandarVehiculosJpaController implements Serializable {

    public EstandarVehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstandarVehiculos estandarVehiculos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estandarVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstandarVehiculos estandarVehiculos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estandarVehiculos = em.merge(estandarVehiculos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estandarVehiculos.getIdEstandarVehiculo();
                if (findEstandarVehiculos(id) == null) {
                    throw new NonexistentEntityException("The estandarVehiculos with id " + id + " no longer exists.");
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
            EstandarVehiculos estandarVehiculos;
            try {
                estandarVehiculos = em.getReference(EstandarVehiculos.class, id);
                estandarVehiculos.getIdEstandarVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estandarVehiculos with id " + id + " no longer exists.", enfe);
            }
            em.remove(estandarVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstandarVehiculos> findEstandarVehiculosEntities() {
        return findEstandarVehiculosEntities(true, -1, -1);
    }

    public List<EstandarVehiculos> findEstandarVehiculosEntities(int maxResults, int firstResult) {
        return findEstandarVehiculosEntities(false, maxResults, firstResult);
    }

    private List<EstandarVehiculos> findEstandarVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstandarVehiculos.class));
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

    public EstandarVehiculos findEstandarVehiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstandarVehiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstandarVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstandarVehiculos> rt = cq.from(EstandarVehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
