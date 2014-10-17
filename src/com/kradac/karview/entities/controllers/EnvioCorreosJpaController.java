/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.logic.EnvioCorreos;
import com.kradac.karview.entities.logic.EnvioCorreosPK;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
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
public class EnvioCorreosJpaController implements Serializable {

    public EnvioCorreosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnvioCorreos envioCorreos) throws PreexistingEntityException, Exception {
        if (envioCorreos.getEnvioCorreosPK() == null) {
            envioCorreos.setEnvioCorreosPK(new EnvioCorreosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(envioCorreos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnvioCorreos(envioCorreos.getEnvioCorreosPK()) != null) {
                throw new PreexistingEntityException("EnvioCorreos " + envioCorreos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnvioCorreos envioCorreos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            envioCorreos = em.merge(envioCorreos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EnvioCorreosPK id = envioCorreos.getEnvioCorreosPK();
                if (findEnvioCorreos(id) == null) {
                    throw new NonexistentEntityException("The envioCorreos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EnvioCorreosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnvioCorreos envioCorreos;
            try {
                envioCorreos = em.getReference(EnvioCorreos.class, id);
                envioCorreos.getEnvioCorreosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envioCorreos with id " + id + " no longer exists.", enfe);
            }
            em.remove(envioCorreos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnvioCorreos> findEnvioCorreosEntities() {
        return findEnvioCorreosEntities(true, -1, -1);
    }

    public List<EnvioCorreos> findEnvioCorreosEntities(int maxResults, int firstResult) {
        return findEnvioCorreosEntities(false, maxResults, firstResult);
    }

    private List<EnvioCorreos> findEnvioCorreosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnvioCorreos.class));
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

    public EnvioCorreos findEnvioCorreos(EnvioCorreosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnvioCorreos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnvioCorreosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnvioCorreos> rt = cq.from(EnvioCorreos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
