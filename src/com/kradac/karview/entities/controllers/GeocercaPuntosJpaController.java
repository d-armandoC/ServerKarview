/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.logic.GeocercaPuntos;
import com.kradac.karview.entities.logic.GeocercaPuntosPK;
import com.kradac.karview.entities.logic.Geocercas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego C
 */
public class GeocercaPuntosJpaController implements Serializable {

    public GeocercaPuntosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeocercaPuntos geocercaPuntos) throws PreexistingEntityException, Exception {
        if (geocercaPuntos.getGeocercaPuntosPK() == null) {
            geocercaPuntos.setGeocercaPuntosPK(new GeocercaPuntosPK());
        }
        geocercaPuntos.getGeocercaPuntosPK().setIdGeocerca(geocercaPuntos.getGeocercas().getIdGeocerca());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Geocercas geocercas = geocercaPuntos.getGeocercas();
            if (geocercas != null) {
                geocercas = em.getReference(geocercas.getClass(), geocercas.getIdGeocerca());
                geocercaPuntos.setGeocercas(geocercas);
            }
            em.persist(geocercaPuntos);
            if (geocercas != null) {
                geocercas.getGeocercaPuntosCollection().add(geocercaPuntos);
                geocercas = em.merge(geocercas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeocercaPuntos(geocercaPuntos.getGeocercaPuntosPK()) != null) {
                throw new PreexistingEntityException("GeocercaPuntos " + geocercaPuntos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeocercaPuntos geocercaPuntos) throws NonexistentEntityException, Exception {
        geocercaPuntos.getGeocercaPuntosPK().setIdGeocerca(geocercaPuntos.getGeocercas().getIdGeocerca());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeocercaPuntos persistentGeocercaPuntos = em.find(GeocercaPuntos.class, geocercaPuntos.getGeocercaPuntosPK());
            Geocercas geocercasOld = persistentGeocercaPuntos.getGeocercas();
            Geocercas geocercasNew = geocercaPuntos.getGeocercas();
            if (geocercasNew != null) {
                geocercasNew = em.getReference(geocercasNew.getClass(), geocercasNew.getIdGeocerca());
                geocercaPuntos.setGeocercas(geocercasNew);
            }
            geocercaPuntos = em.merge(geocercaPuntos);
            if (geocercasOld != null && !geocercasOld.equals(geocercasNew)) {
                geocercasOld.getGeocercaPuntosCollection().remove(geocercaPuntos);
                geocercasOld = em.merge(geocercasOld);
            }
            if (geocercasNew != null && !geocercasNew.equals(geocercasOld)) {
                geocercasNew.getGeocercaPuntosCollection().add(geocercaPuntos);
                geocercasNew = em.merge(geocercasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                GeocercaPuntosPK id = geocercaPuntos.getGeocercaPuntosPK();
                if (findGeocercaPuntos(id) == null) {
                    throw new NonexistentEntityException("The geocercaPuntos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(GeocercaPuntosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeocercaPuntos geocercaPuntos;
            try {
                geocercaPuntos = em.getReference(GeocercaPuntos.class, id);
                geocercaPuntos.getGeocercaPuntosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geocercaPuntos with id " + id + " no longer exists.", enfe);
            }
            Geocercas geocercas = geocercaPuntos.getGeocercas();
            if (geocercas != null) {
                geocercas.getGeocercaPuntosCollection().remove(geocercaPuntos);
                geocercas = em.merge(geocercas);
            }
            em.remove(geocercaPuntos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeocercaPuntos> findGeocercaPuntosEntities() {
        return findGeocercaPuntosEntities(true, -1, -1);
    }

    public List<GeocercaPuntos> findGeocercaPuntosEntities(int maxResults, int firstResult) {
        return findGeocercaPuntosEntities(false, maxResults, firstResult);
    }

    private List<GeocercaPuntos> findGeocercaPuntosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeocercaPuntos.class));
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

    public GeocercaPuntos findGeocercaPuntos(GeocercaPuntosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeocercaPuntos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeocercaPuntosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeocercaPuntos> rt = cq.from(GeocercaPuntos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<GeocercaPuntos> listaGeocercaPuntos(int idGeocerca){
         EntityManager em = getEntityManager();
         try {
            TypedQuery<GeocercaPuntos> gcp;
            gcp= em.createQuery("SELECT g FROM GeocercaPuntos g WHERE g.geocercaPuntosPK.idGeocerca = :idGeocerca order by orden", GeocercaPuntos.class);
            gcp.setParameter("idGeocerca", idGeocerca);
            return gcp.getResultList();
        } catch (NoResultException e) {
                  return null;
        } finally {
            em.close();
        
        }
    }
}
