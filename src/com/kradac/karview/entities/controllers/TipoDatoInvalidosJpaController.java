/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.logic.TipoDatoInvalidos;
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
public class TipoDatoInvalidosJpaController implements Serializable {

    public TipoDatoInvalidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDatoInvalidos tipoDatoInvalidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoDatoInvalidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDatoInvalidos tipoDatoInvalidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoDatoInvalidos = em.merge(tipoDatoInvalidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDatoInvalidos.getIdTipoDatoInvalido();
                if (findTipoDatoInvalidos(id) == null) {
                    throw new NonexistentEntityException("The tipoDatoInvalidos with id " + id + " no longer exists.");
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
            TipoDatoInvalidos tipoDatoInvalidos;
            try {
                tipoDatoInvalidos = em.getReference(TipoDatoInvalidos.class, id);
                tipoDatoInvalidos.getIdTipoDatoInvalido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDatoInvalidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoDatoInvalidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDatoInvalidos> findTipoDatoInvalidosEntities() {
        return findTipoDatoInvalidosEntities(true, -1, -1);
    }

    public List<TipoDatoInvalidos> findTipoDatoInvalidosEntities(int maxResults, int firstResult) {
        return findTipoDatoInvalidosEntities(false, maxResults, firstResult);
    }

    private List<TipoDatoInvalidos> findTipoDatoInvalidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDatoInvalidos.class));
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

    public TipoDatoInvalidos findTipoDatoInvalidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDatoInvalidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDatoInvalidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDatoInvalidos> rt = cq.from(TipoDatoInvalidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
