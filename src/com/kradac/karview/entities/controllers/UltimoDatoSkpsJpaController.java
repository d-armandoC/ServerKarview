/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Equipos;
import com.kradac.karview.entities.logic.SkyEventos;
import com.kradac.karview.entities.logic.UltimoDatoSkps;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego C
 */
public class UltimoDatoSkpsJpaController implements Serializable {

    public UltimoDatoSkpsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UltimoDatoSkps ultimoDatoSkps) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipos idEquipo = ultimoDatoSkps.getIdEquipo();
            if (idEquipo != null) {
                idEquipo = em.getReference(idEquipo.getClass(), idEquipo.getIdEquipo());
                ultimoDatoSkps.setIdEquipo(idEquipo);
            }
            SkyEventos idSkyEvento = ultimoDatoSkps.getIdSkyEvento();
            if (idSkyEvento != null) {
                idSkyEvento = em.getReference(idSkyEvento.getClass(), idSkyEvento.getIdSkyEvento());
                ultimoDatoSkps.setIdSkyEvento(idSkyEvento);
            }
            em.persist(ultimoDatoSkps);
            if (idEquipo != null) {
                idEquipo.getUltimoDatoSkpsCollection().add(ultimoDatoSkps);
                idEquipo = em.merge(idEquipo);
            }
            if (idSkyEvento != null) {
                idSkyEvento.getUltimoDatoSkpsCollection().add(ultimoDatoSkps);
                idSkyEvento = em.merge(idSkyEvento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UltimoDatoSkps ultimoDatoSkps) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UltimoDatoSkps persistentUltimoDatoSkps = em.find(UltimoDatoSkps.class, ultimoDatoSkps.getIdUltimoDatoSkp());
            Equipos idEquipoOld = persistentUltimoDatoSkps.getIdEquipo();
            Equipos idEquipoNew = ultimoDatoSkps.getIdEquipo();
            SkyEventos idSkyEventoOld = persistentUltimoDatoSkps.getIdSkyEvento();
            SkyEventos idSkyEventoNew = ultimoDatoSkps.getIdSkyEvento();
            if (idEquipoNew != null) {
                idEquipoNew = em.getReference(idEquipoNew.getClass(), idEquipoNew.getIdEquipo());
                ultimoDatoSkps.setIdEquipo(idEquipoNew);
            }
            if (idSkyEventoNew != null) {
                idSkyEventoNew = em.getReference(idSkyEventoNew.getClass(), idSkyEventoNew.getIdSkyEvento());
                ultimoDatoSkps.setIdSkyEvento(idSkyEventoNew);
            }
            ultimoDatoSkps = em.merge(ultimoDatoSkps);
            if (idEquipoOld != null && !idEquipoOld.equals(idEquipoNew)) {
                idEquipoOld.getUltimoDatoSkpsCollection().remove(ultimoDatoSkps);
                idEquipoOld = em.merge(idEquipoOld);
            }
            if (idEquipoNew != null && !idEquipoNew.equals(idEquipoOld)) {
                idEquipoNew.getUltimoDatoSkpsCollection().add(ultimoDatoSkps);
                idEquipoNew = em.merge(idEquipoNew);
            }
            if (idSkyEventoOld != null && !idSkyEventoOld.equals(idSkyEventoNew)) {
                idSkyEventoOld.getUltimoDatoSkpsCollection().remove(ultimoDatoSkps);
                idSkyEventoOld = em.merge(idSkyEventoOld);
            }
            if (idSkyEventoNew != null && !idSkyEventoNew.equals(idSkyEventoOld)) {
                idSkyEventoNew.getUltimoDatoSkpsCollection().add(ultimoDatoSkps);
                idSkyEventoNew = em.merge(idSkyEventoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ultimoDatoSkps.getIdUltimoDatoSkp();
                if (findUltimoDatoSkps(id) == null) {
                    throw new NonexistentEntityException("The ultimoDatoSkps with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UltimoDatoSkps ultimoDatoSkps;
            try {
                ultimoDatoSkps = em.getReference(UltimoDatoSkps.class, id);
                ultimoDatoSkps.getIdUltimoDatoSkp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ultimoDatoSkps with id " + id + " no longer exists.", enfe);
            }
            Equipos idEquipo = ultimoDatoSkps.getIdEquipo();
            if (idEquipo != null) {
                idEquipo.getUltimoDatoSkpsCollection().remove(ultimoDatoSkps);
                idEquipo = em.merge(idEquipo);
            }
            SkyEventos idSkyEvento = ultimoDatoSkps.getIdSkyEvento();
            if (idSkyEvento != null) {
                idSkyEvento.getUltimoDatoSkpsCollection().remove(ultimoDatoSkps);
                idSkyEvento = em.merge(idSkyEvento);
            }
            em.remove(ultimoDatoSkps);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UltimoDatoSkps> findUltimoDatoSkpsEntities() {
        return findUltimoDatoSkpsEntities(true, -1, -1);
    }

    public List<UltimoDatoSkps> findUltimoDatoSkpsEntities(int maxResults, int firstResult) {
        return findUltimoDatoSkpsEntities(false, maxResults, firstResult);
    }

    private List<UltimoDatoSkps> findUltimoDatoSkpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UltimoDatoSkps.class));
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

    public UltimoDatoSkps findUltimoDatoSkps(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UltimoDatoSkps.class, id);
        } finally {
            em.close();
        }
    }

    public int getUltimoDatoSkpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UltimoDatoSkps> rt = cq.from(UltimoDatoSkps.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
      public UltimoDatoSkps findUltimoDatoSkpsByIdEquipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<UltimoDatoSkps> qry;
            qry = em.createQuery("SELECT u FROM UltimoDatoSkps u WHERE u.idEquipo.idEquipo = :idEquipo", UltimoDatoSkps.class);
            qry.setParameter("idEquipo", id);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
