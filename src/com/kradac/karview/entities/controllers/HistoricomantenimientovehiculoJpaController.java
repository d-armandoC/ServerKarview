/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.historic.Historicomantenimientovehiculo;
import com.kradac.karview.entities.historic.HistoricomantenimientovehiculoPK;
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
public class HistoricomantenimientovehiculoJpaController implements Serializable {

    public HistoricomantenimientovehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicomantenimientovehiculo historicomantenimientovehiculo) throws PreexistingEntityException, Exception {
        if (historicomantenimientovehiculo.getHistoricomantenimientovehiculoPK() == null) {
            historicomantenimientovehiculo.setHistoricomantenimientovehiculoPK(new HistoricomantenimientovehiculoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historicomantenimientovehiculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoricomantenimientovehiculo(historicomantenimientovehiculo.getHistoricomantenimientovehiculoPK()) != null) {
                throw new PreexistingEntityException("Historicomantenimientovehiculo " + historicomantenimientovehiculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicomantenimientovehiculo historicomantenimientovehiculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historicomantenimientovehiculo = em.merge(historicomantenimientovehiculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistoricomantenimientovehiculoPK id = historicomantenimientovehiculo.getHistoricomantenimientovehiculoPK();
                if (findHistoricomantenimientovehiculo(id) == null) {
                    throw new NonexistentEntityException("The historicomantenimientovehiculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistoricomantenimientovehiculoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicomantenimientovehiculo historicomantenimientovehiculo;
            try {
                historicomantenimientovehiculo = em.getReference(Historicomantenimientovehiculo.class, id);
                historicomantenimientovehiculo.getHistoricomantenimientovehiculoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicomantenimientovehiculo with id " + id + " no longer exists.", enfe);
            }
            em.remove(historicomantenimientovehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicomantenimientovehiculo> findHistoricomantenimientovehiculoEntities() {
        return findHistoricomantenimientovehiculoEntities(true, -1, -1);
    }

    public List<Historicomantenimientovehiculo> findHistoricomantenimientovehiculoEntities(int maxResults, int firstResult) {
        return findHistoricomantenimientovehiculoEntities(false, maxResults, firstResult);
    }

    private List<Historicomantenimientovehiculo> findHistoricomantenimientovehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historicomantenimientovehiculo.class));
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

    public Historicomantenimientovehiculo findHistoricomantenimientovehiculo(HistoricomantenimientovehiculoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicomantenimientovehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricomantenimientovehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historicomantenimientovehiculo> rt = cq.from(Historicomantenimientovehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
