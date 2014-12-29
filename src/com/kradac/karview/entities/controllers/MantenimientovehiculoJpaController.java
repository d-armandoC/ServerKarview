/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.logic.Configuraciones;
import com.kradac.karview.entities.logic.Mantenimientovehiculo;
import com.kradac.karview.entities.logic.MantenimientovehiculoPK;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego C
 */
public class MantenimientovehiculoJpaController implements Serializable {

    public MantenimientovehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientovehiculo mantenimientovehiculo) throws PreexistingEntityException, Exception {
        if (mantenimientovehiculo.getMantenimientovehiculoPK() == null) {
            mantenimientovehiculo.setMantenimientovehiculoPK(new MantenimientovehiculoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mantenimientovehiculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimientovehiculo(mantenimientovehiculo.getMantenimientovehiculoPK()) != null) {
                throw new PreexistingEntityException("Mantenimientovehiculo " + mantenimientovehiculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimientovehiculo mantenimientovehiculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mantenimientovehiculo = em.merge(mantenimientovehiculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MantenimientovehiculoPK id = mantenimientovehiculo.getMantenimientovehiculoPK();
                if (findMantenimientovehiculo(id) == null) {
                    throw new NonexistentEntityException("The mantenimientovehiculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MantenimientovehiculoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientovehiculo mantenimientovehiculo;
            try {
                mantenimientovehiculo = em.getReference(Mantenimientovehiculo.class, id);
                mantenimientovehiculo.getMantenimientovehiculoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimientovehiculo with id " + id + " no longer exists.", enfe);
            }
            em.remove(mantenimientovehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimientovehiculo> findMantenimientovehiculoEntities() {
        return findMantenimientovehiculoEntities(true, -1, -1);
    }

    public List<Mantenimientovehiculo> findMantenimientovehiculoEntities(int maxResults, int firstResult) {
        return findMantenimientovehiculoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimientovehiculo> findMantenimientovehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimientovehiculo.class));
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

    public Mantenimientovehiculo findMantenimientovehiculo(MantenimientovehiculoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimientovehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public List<Mantenimientovehiculo> obtenerRegistrosHoy(Date fechaConfig) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Mantenimientovehiculo> qry;
            qry = em.createNamedQuery("Mantenimientovehiculo.findByFechaConfig", Mantenimientovehiculo.class);
            qry.setParameter("fechaConfig", fechaConfig);
            return qry.getResultList();
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "Problemas de la fecha [" + fechaConfig + "] de Datos del Mnatenimiento Buscado.");
            System.exit(0);
            return null;
        } finally {
            em.close();
        }
    }

    public int getMantenimientovehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimientovehiculo> rt = cq.from(Mantenimientovehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
