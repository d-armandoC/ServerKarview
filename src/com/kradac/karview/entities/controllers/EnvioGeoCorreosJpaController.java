/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.logic.EnvioGeoCorreos;
import com.kradac.karview.entities.logic.EnvioGeoCorreosPK;
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
public class EnvioGeoCorreosJpaController implements Serializable {

    public EnvioGeoCorreosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnvioGeoCorreos envioGeoCorreos) throws PreexistingEntityException, Exception {
        if (envioGeoCorreos.getEnvioGeoCorreosPK() == null) {
            envioGeoCorreos.setEnvioGeoCorreosPK(new EnvioGeoCorreosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(envioGeoCorreos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnvioGeoCorreos(envioGeoCorreos.getEnvioGeoCorreosPK()) != null) {
                throw new PreexistingEntityException("EnvioGeoCorreos " + envioGeoCorreos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnvioGeoCorreos envioGeoCorreos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            envioGeoCorreos = em.merge(envioGeoCorreos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EnvioGeoCorreosPK id = envioGeoCorreos.getEnvioGeoCorreosPK();
                if (findEnvioGeoCorreos(id) == null) {
                    throw new NonexistentEntityException("The envioGeoCorreos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EnvioGeoCorreosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnvioGeoCorreos envioGeoCorreos;
            try {
                envioGeoCorreos = em.getReference(EnvioGeoCorreos.class, id);
                envioGeoCorreos.getEnvioGeoCorreosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envioGeoCorreos with id " + id + " no longer exists.", enfe);
            }
            em.remove(envioGeoCorreos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnvioGeoCorreos> findEnvioGeoCorreosEntities() {
        return findEnvioGeoCorreosEntities(true, -1, -1);
    }

    public List<EnvioGeoCorreos> findEnvioGeoCorreosEntities(int maxResults, int firstResult) {
        return findEnvioGeoCorreosEntities(false, maxResults, firstResult);
    }

    private List<EnvioGeoCorreos> findEnvioGeoCorreosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnvioGeoCorreos.class));
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

    public EnvioGeoCorreos findEnvioGeoCorreos(EnvioGeoCorreosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnvioGeoCorreos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnvioGeoCorreosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnvioGeoCorreos> rt = cq.from(EnvioGeoCorreos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
