/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.CmdPredefinidos;
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
public class CmdPredefinidosJpaController implements Serializable {

    public CmdPredefinidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CmdPredefinidos cmdPredefinidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cmdPredefinidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CmdPredefinidos cmdPredefinidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cmdPredefinidos = em.merge(cmdPredefinidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cmdPredefinidos.getIdCmdPredefinido();
                if (findCmdPredefinidos(id) == null) {
                    throw new NonexistentEntityException("The cmdPredefinidos with id " + id + " no longer exists.");
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
            CmdPredefinidos cmdPredefinidos;
            try {
                cmdPredefinidos = em.getReference(CmdPredefinidos.class, id);
                cmdPredefinidos.getIdCmdPredefinido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cmdPredefinidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(cmdPredefinidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CmdPredefinidos> findCmdPredefinidosEntities() {
        return findCmdPredefinidosEntities(true, -1, -1);
    }

    public List<CmdPredefinidos> findCmdPredefinidosEntities(int maxResults, int firstResult) {
        return findCmdPredefinidosEntities(false, maxResults, firstResult);
    }

    private List<CmdPredefinidos> findCmdPredefinidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CmdPredefinidos.class));
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

    public CmdPredefinidos findCmdPredefinidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CmdPredefinidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCmdPredefinidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CmdPredefinidos> rt = cq.from(CmdPredefinidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
