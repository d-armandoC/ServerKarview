/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.logic.Mantenimientovehiculo;
import com.kradac.karview.entities.logic.RegistrosMantenimiento;
import com.kradac.karview.entities.logic.RegistrosMantenimientoPK;
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
public class RegistrosMantenimientoJpaController implements Serializable {

    public RegistrosMantenimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistrosMantenimiento registrosMantenimiento) throws PreexistingEntityException, Exception {
        if (registrosMantenimiento.getRegistrosMantenimientoPK() == null) {
            registrosMantenimiento.setRegistrosMantenimientoPK(new RegistrosMantenimientoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registrosMantenimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrosMantenimiento(registrosMantenimiento.getRegistrosMantenimientoPK()) != null) {
                throw new PreexistingEntityException("RegistrosMantenimiento " + registrosMantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistrosMantenimiento registrosMantenimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registrosMantenimiento = em.merge(registrosMantenimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistrosMantenimientoPK id = registrosMantenimiento.getRegistrosMantenimientoPK();
                if (findRegistrosMantenimiento(id) == null) {
                    throw new NonexistentEntityException("The registrosMantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistrosMantenimientoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistrosMantenimiento registrosMantenimiento;
            try {
                registrosMantenimiento = em.getReference(RegistrosMantenimiento.class, id);
                registrosMantenimiento.getRegistrosMantenimientoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrosMantenimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(registrosMantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistrosMantenimiento> findRegistrosMantenimientoEntities() {
        return findRegistrosMantenimientoEntities(true, -1, -1);
    }

    public List<RegistrosMantenimiento> findRegistrosMantenimientoEntities(int maxResults, int firstResult) {
        return findRegistrosMantenimientoEntities(false, maxResults, firstResult);
    }

    private List<RegistrosMantenimiento> findRegistrosMantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistrosMantenimiento.class));
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

    public RegistrosMantenimiento findRegistrosMantenimiento(RegistrosMantenimientoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistrosMantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrosMantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistrosMantenimiento> rt = cq.from(RegistrosMantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public List<RegistrosMantenimiento> obtenerRegistroMantenimiento(Date fechaVencimiento) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RegistrosMantenimiento> qry;
            qry = em.createNamedQuery("RegistrosMantenimiento.findByFechaVencimiento", RegistrosMantenimiento.class);
            qry.setParameter("fechaVencimiento", fechaVencimiento);
            return qry.getResultList();
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "Problemas de la fecha [" + fechaVencimiento + "] de Datos del Mnatenimiento Buscado.");
            System.exit(0);
            return null;
        } finally {
            em.close();
        }
    }
    
}
