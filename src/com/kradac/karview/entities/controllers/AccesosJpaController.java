/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.historic.Accesos;
import com.kradac.karview.entities.historic.AccesosPK;
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
public class AccesosJpaController implements Serializable {

    public AccesosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accesos accesos) throws PreexistingEntityException, Exception {
        if (accesos.getAccesosPK() == null) {
            accesos.setAccesosPK(new AccesosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accesos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccesos(accesos.getAccesosPK()) != null) {
                throw new PreexistingEntityException("Accesos " + accesos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accesos accesos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accesos = em.merge(accesos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AccesosPK id = accesos.getAccesosPK();
                if (findAccesos(id) == null) {
                    throw new NonexistentEntityException("The accesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AccesosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accesos accesos;
            try {
                accesos = em.getReference(Accesos.class, id);
                accesos.getAccesosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accesos with id " + id + " no longer exists.", enfe);
            }
            em.remove(accesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accesos> findAccesosEntities() {
        return findAccesosEntities(true, -1, -1);
    }

    public List<Accesos> findAccesosEntities(int maxResults, int firstResult) {
        return findAccesosEntities(false, maxResults, firstResult);
    }

    private List<Accesos> findAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accesos.class));
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

    public Accesos findAccesos(AccesosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accesos> rt = cq.from(Accesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
