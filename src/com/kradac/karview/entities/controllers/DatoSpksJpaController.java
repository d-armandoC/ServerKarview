/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.historic.DatoSpks;
import com.kradac.karview.entities.historic.DatoSpksPK;
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
public class DatoSpksJpaController implements Serializable {

    public DatoSpksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatoSpks datoSpks) throws PreexistingEntityException, Exception {
        if (datoSpks.getDatoSpksPK() == null) {
            datoSpks.setDatoSpksPK(new DatoSpksPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datoSpks);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatoSpks(datoSpks.getDatoSpksPK()) != null) {
                throw new PreexistingEntityException("DatoSpks " + datoSpks + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatoSpks datoSpks) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datoSpks = em.merge(datoSpks);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatoSpksPK id = datoSpks.getDatoSpksPK();
                if (findDatoSpks(id) == null) {
                    throw new NonexistentEntityException("The datoSpks with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatoSpksPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatoSpks datoSpks;
            try {
                datoSpks = em.getReference(DatoSpks.class, id);
                datoSpks.getDatoSpksPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datoSpks with id " + id + " no longer exists.", enfe);
            }
            em.remove(datoSpks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatoSpks> findDatoSpksEntities() {
        return findDatoSpksEntities(true, -1, -1);
    }

    public List<DatoSpks> findDatoSpksEntities(int maxResults, int firstResult) {
        return findDatoSpksEntities(false, maxResults, firstResult);
    }

    private List<DatoSpks> findDatoSpksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatoSpks.class));
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

    public DatoSpks findDatoSpks(DatoSpksPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatoSpks.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatoSpksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatoSpks> rt = cq.from(DatoSpks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
