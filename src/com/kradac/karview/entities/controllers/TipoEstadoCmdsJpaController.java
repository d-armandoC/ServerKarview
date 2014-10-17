/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.logic.TipoEstadoCmds;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
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
public class TipoEstadoCmdsJpaController implements Serializable {

    public TipoEstadoCmdsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEstadoCmds tipoEstadoCmds) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoEstadoCmds);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEstadoCmds tipoEstadoCmds) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoEstadoCmds = em.merge(tipoEstadoCmds);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoEstadoCmds.getIdTipoEstadoCmd();
                if (findTipoEstadoCmds(id) == null) {
                    throw new NonexistentEntityException("The tipoEstadoCmds with id " + id + " no longer exists.");
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
            TipoEstadoCmds tipoEstadoCmds;
            try {
                tipoEstadoCmds = em.getReference(TipoEstadoCmds.class, id);
                tipoEstadoCmds.getIdTipoEstadoCmd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEstadoCmds with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoEstadoCmds);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEstadoCmds> findTipoEstadoCmdsEntities() {
        return findTipoEstadoCmdsEntities(true, -1, -1);
    }

    public List<TipoEstadoCmds> findTipoEstadoCmdsEntities(int maxResults, int firstResult) {
        return findTipoEstadoCmdsEntities(false, maxResults, firstResult);
    }

    private List<TipoEstadoCmds> findTipoEstadoCmdsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoEstadoCmds.class));
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

    public TipoEstadoCmds findTipoEstadoCmds(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEstadoCmds.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEstadoCmdsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoEstadoCmds> rt = cq.from(TipoEstadoCmds.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
