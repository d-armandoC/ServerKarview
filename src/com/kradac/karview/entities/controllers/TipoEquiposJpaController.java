/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.logic.TipoEquipos;
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
public class TipoEquiposJpaController implements Serializable {

    public TipoEquiposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEquipos tipoEquipos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoEquipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEquipos tipoEquipos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoEquipos = em.merge(tipoEquipos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoEquipos.getIdTipoEquipo();
                if (findTipoEquipos(id) == null) {
                    throw new NonexistentEntityException("The tipoEquipos with id " + id + " no longer exists.");
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
            TipoEquipos tipoEquipos;
            try {
                tipoEquipos = em.getReference(TipoEquipos.class, id);
                tipoEquipos.getIdTipoEquipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEquipos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoEquipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEquipos> findTipoEquiposEntities() {
        return findTipoEquiposEntities(true, -1, -1);
    }

    public List<TipoEquipos> findTipoEquiposEntities(int maxResults, int firstResult) {
        return findTipoEquiposEntities(false, maxResults, firstResult);
    }

    private List<TipoEquipos> findTipoEquiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoEquipos.class));
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

    public TipoEquipos findTipoEquipos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEquipos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEquiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoEquipos> rt = cq.from(TipoEquipos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
