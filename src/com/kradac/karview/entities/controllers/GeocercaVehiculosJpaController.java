/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.logic.GeocercaVehiculos;
import com.kradac.karview.entities.logic.GeocercaVehiculosPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Geocercas;
import com.kradac.karview.entities.logic.Vehiculos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego C
 */
public class GeocercaVehiculosJpaController implements Serializable {

    public GeocercaVehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeocercaVehiculos geocercaVehiculos) throws PreexistingEntityException, Exception {
        if (geocercaVehiculos.getGeocercaVehiculosPK() == null) {
            geocercaVehiculos.setGeocercaVehiculosPK(new GeocercaVehiculosPK());
        }
        geocercaVehiculos.getGeocercaVehiculosPK().setIdGeocerca(geocercaVehiculos.getGeocercas().getIdGeocerca());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Geocercas geocercas = geocercaVehiculos.getGeocercas();
            if (geocercas != null) {
                geocercas = em.getReference(geocercas.getClass(), geocercas.getIdGeocerca());
                geocercaVehiculos.setGeocercas(geocercas);
            }
            em.persist(geocercaVehiculos);
            if (geocercas != null) {
                geocercas.getGeocercaVehiculosCollection().add(geocercaVehiculos);
                geocercas = em.merge(geocercas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeocercaVehiculos(geocercaVehiculos.getGeocercaVehiculosPK()) != null) {
                throw new PreexistingEntityException("GeocercaVehiculos " + geocercaVehiculos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeocercaVehiculos geocercaVehiculos) throws NonexistentEntityException, Exception {
        geocercaVehiculos.getGeocercaVehiculosPK().setIdGeocerca(geocercaVehiculos.getGeocercas().getIdGeocerca());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeocercaVehiculos persistentGeocercaVehiculos = em.find(GeocercaVehiculos.class, geocercaVehiculos.getGeocercaVehiculosPK());
            Geocercas geocercasOld = persistentGeocercaVehiculos.getGeocercas();
            Geocercas geocercasNew = geocercaVehiculos.getGeocercas();
            if (geocercasNew != null) {
                geocercasNew = em.getReference(geocercasNew.getClass(), geocercasNew.getIdGeocerca());
                geocercaVehiculos.setGeocercas(geocercasNew);
            }
            geocercaVehiculos = em.merge(geocercaVehiculos);
            if (geocercasOld != null && !geocercasOld.equals(geocercasNew)) {
                geocercasOld.getGeocercaVehiculosCollection().remove(geocercaVehiculos);
                geocercasOld = em.merge(geocercasOld);
            }
            if (geocercasNew != null && !geocercasNew.equals(geocercasOld)) {
                geocercasNew.getGeocercaVehiculosCollection().add(geocercaVehiculos);
                geocercasNew = em.merge(geocercasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                GeocercaVehiculosPK id = geocercaVehiculos.getGeocercaVehiculosPK();
                if (findGeocercaVehiculos(id) == null) {
                    throw new NonexistentEntityException("The geocercaVehiculos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(GeocercaVehiculosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeocercaVehiculos geocercaVehiculos;
            try {
                geocercaVehiculos = em.getReference(GeocercaVehiculos.class, id);
                geocercaVehiculos.getGeocercaVehiculosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geocercaVehiculos with id " + id + " no longer exists.", enfe);
            }
            Geocercas geocercas = geocercaVehiculos.getGeocercas();
            if (geocercas != null) {
                geocercas.getGeocercaVehiculosCollection().remove(geocercaVehiculos);
                geocercas = em.merge(geocercas);
            }
            em.remove(geocercaVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeocercaVehiculos> findGeocercaVehiculosEntities() {
        return findGeocercaVehiculosEntities(true, -1, -1);
    }

    public List<GeocercaVehiculos> findGeocercaVehiculosEntities(int maxResults, int firstResult) {
        return findGeocercaVehiculosEntities(false, maxResults, firstResult);
    }

    private List<GeocercaVehiculos> findGeocercaVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeocercaVehiculos.class));
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

    public GeocercaVehiculos findGeocercaVehiculos(GeocercaVehiculosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeocercaVehiculos.class, id);
        } finally {
            em.close();
        }
    }

     public GeocercaVehiculos findGeocercaVehiculos(int idVehiculo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<GeocercaVehiculos> qry;
            qry = em.createQuery("SELECT gv FROM GeocercaVehiculos gv WHERE gv.geocercaVehiculosPK.idVehiculo = :idVehiculo", GeocercaVehiculos.class);
            qry.setParameter("idVehiculo", idVehiculo);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


    public int getGeocercaVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeocercaVehiculos> rt = cq.from(GeocercaVehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
