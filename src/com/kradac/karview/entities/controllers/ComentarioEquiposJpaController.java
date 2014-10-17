/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.historic.ComentarioEquipos;
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
public class ComentarioEquiposJpaController implements Serializable {

    public ComentarioEquiposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComentarioEquipos comentarioEquipos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comentarioEquipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComentarioEquipos comentarioEquipos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comentarioEquipos = em.merge(comentarioEquipos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentarioEquipos.getIdComentarioEquipo();
                if (findComentarioEquipos(id) == null) {
                    throw new NonexistentEntityException("The comentarioEquipos with id " + id + " no longer exists.");
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
            ComentarioEquipos comentarioEquipos;
            try {
                comentarioEquipos = em.getReference(ComentarioEquipos.class, id);
                comentarioEquipos.getIdComentarioEquipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentarioEquipos with id " + id + " no longer exists.", enfe);
            }
            em.remove(comentarioEquipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComentarioEquipos> findComentarioEquiposEntities() {
        return findComentarioEquiposEntities(true, -1, -1);
    }

    public List<ComentarioEquipos> findComentarioEquiposEntities(int maxResults, int firstResult) {
        return findComentarioEquiposEntities(false, maxResults, firstResult);
    }

    private List<ComentarioEquipos> findComentarioEquiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComentarioEquipos.class));
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

    public ComentarioEquipos findComentarioEquipos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComentarioEquipos.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioEquiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComentarioEquipos> rt = cq.from(ComentarioEquipos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
