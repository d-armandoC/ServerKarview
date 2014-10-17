/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import com.kradac.karview.entities.historic.Comandos;
import com.kradac.karview.entities.historic.ComandosPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego C
 */
public class ComandosJpaController implements Serializable {

    public ComandosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comandos comandos) throws PreexistingEntityException, Exception {
        if (comandos.getComandosPK() == null) {
            comandos.setComandosPK(new ComandosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comandos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComandos(comandos.getComandosPK()) != null) {
                throw new PreexistingEntityException("Comandos " + comandos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comandos comandos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comandos = em.merge(comandos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComandosPK id = comandos.getComandosPK();
                if (findComandos(id) == null) {
                    throw new NonexistentEntityException("The comandos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComandosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comandos comandos;
            try {
                comandos = em.getReference(Comandos.class, id);
                comandos.getComandosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comandos with id " + id + " no longer exists.", enfe);
            }
            em.remove(comandos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comandos> findComandosEntities() {
        return findComandosEntities(true, -1, -1);
    }

    public List<Comandos> findComandosEntities(int maxResults, int firstResult) {
        return findComandosEntities(false, maxResults, firstResult);
    }

    private List<Comandos> findComandosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comandos.class));
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

    public Comandos findComandos(ComandosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comandos.class, id);
        } finally {
            em.close();
        }
    }

    public int getComandosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comandos> rt = cq.from(Comandos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public Comandos getComandosToSend(int idEquipo) {
        EntityManager em = getEntityManager();
        try {
            List<Comandos> lc;
            Comandos c = null;
            TypedQuery<Comandos> qry;
            qry = em.createQuery("SELECT c FROM Comandos c WHERE c.comandosPK.idEquipo = :idEquipo AND c.idTipoEstadoCmd = :idTipoEstadoCmd AND DATE(c.comandosPK.fechaHoraRegistro) = DATE(NOW())", Comandos.class);
            qry.setParameter("idEquipo", idEquipo);
            qry.setParameter("idTipoEstadoCmd", 1);
            lc = qry.getResultList();
            for (Comandos comandos : lc) {
                c = comandos;
                break;
            }
            
            return c;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
