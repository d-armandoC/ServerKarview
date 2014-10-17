/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.historic.ComentarioVehiculos;
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
public class ComentarioVehiculosJpaController implements Serializable {

    public ComentarioVehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComentarioVehiculos comentarioVehiculos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comentarioVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComentarioVehiculos comentarioVehiculos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comentarioVehiculos = em.merge(comentarioVehiculos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentarioVehiculos.getIdComentarioVehiculo();
                if (findComentarioVehiculos(id) == null) {
                    throw new NonexistentEntityException("The comentarioVehiculos with id " + id + " no longer exists.");
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
            ComentarioVehiculos comentarioVehiculos;
            try {
                comentarioVehiculos = em.getReference(ComentarioVehiculos.class, id);
                comentarioVehiculos.getIdComentarioVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentarioVehiculos with id " + id + " no longer exists.", enfe);
            }
            em.remove(comentarioVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComentarioVehiculos> findComentarioVehiculosEntities() {
        return findComentarioVehiculosEntities(true, -1, -1);
    }

    public List<ComentarioVehiculos> findComentarioVehiculosEntities(int maxResults, int firstResult) {
        return findComentarioVehiculosEntities(false, maxResults, firstResult);
    }

    private List<ComentarioVehiculos> findComentarioVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComentarioVehiculos.class));
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

    public ComentarioVehiculos findComentarioVehiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComentarioVehiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComentarioVehiculos> rt = cq.from(ComentarioVehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
