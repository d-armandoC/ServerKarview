/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.historic.DatoInvalidos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego C
 */
public class DatoInvalidosJpaController implements Serializable {

    public DatoInvalidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatoInvalidos datoInvalidos) {
       
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //em.persist(new DatoInvalidos(3, new Date(),"PT45","asa","as"));
            em.persist(datoInvalidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatoInvalidos datoInvalidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datoInvalidos = em.merge(datoInvalidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datoInvalidos.getIdDatoInvalido();
                if (findDatoInvalidos(id) == null) {
                    throw new NonexistentEntityException("The datoInvalidos with id " + id + " no longer exists.");
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
            DatoInvalidos datoInvalidos;
            try {
                datoInvalidos = em.getReference(DatoInvalidos.class, id);
                datoInvalidos.getIdDatoInvalido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datoInvalidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(datoInvalidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatoInvalidos> findDatoInvalidosEntities() {
        return findDatoInvalidosEntities(true, -1, -1);
    }

    public List<DatoInvalidos> findDatoInvalidosEntities(int maxResults, int firstResult) {
        return findDatoInvalidosEntities(false, maxResults, firstResult);
    }

    private List<DatoInvalidos> findDatoInvalidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatoInvalidos.class));
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

    public DatoInvalidos findDatoInvalidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatoInvalidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatoInvalidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatoInvalidos> rt = cq.from(DatoInvalidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
