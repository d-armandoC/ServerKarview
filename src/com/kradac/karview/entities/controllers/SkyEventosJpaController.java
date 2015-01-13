/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.EnvioCorreos;
import com.kradac.karview.entities.logic.SkyEventos;
import java.util.ArrayList;
import java.util.Collection;
import com.kradac.karview.entities.logic.UltimoDatoSkps;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego C
 */
public class SkyEventosJpaController implements Serializable {

    public SkyEventosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SkyEventos skyEventos) {
        if (skyEventos.getEnvioCorreosCollection() == null) {
            skyEventos.setEnvioCorreosCollection(new ArrayList<EnvioCorreos>());
        }
        if (skyEventos.getUltimoDatoSkpsCollection() == null) {
            skyEventos.setUltimoDatoSkpsCollection(new ArrayList<UltimoDatoSkps>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<EnvioCorreos> attachedEnvioCorreosCollection = new ArrayList<EnvioCorreos>();
            for (EnvioCorreos envioCorreosCollectionEnvioCorreosToAttach : skyEventos.getEnvioCorreosCollection()) {
                envioCorreosCollectionEnvioCorreosToAttach = em.getReference(envioCorreosCollectionEnvioCorreosToAttach.getClass(), envioCorreosCollectionEnvioCorreosToAttach.getEnvioCorreosPK());
                attachedEnvioCorreosCollection.add(envioCorreosCollectionEnvioCorreosToAttach);
            }
            skyEventos.setEnvioCorreosCollection(attachedEnvioCorreosCollection);
            Collection<UltimoDatoSkps> attachedUltimoDatoSkpsCollection = new ArrayList<UltimoDatoSkps>();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach : skyEventos.getUltimoDatoSkpsCollection()) {
                ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach = em.getReference(ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach.getClass(), ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach.getIdUltimoDatoSkp());
                attachedUltimoDatoSkpsCollection.add(ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach);
            }
            skyEventos.setUltimoDatoSkpsCollection(attachedUltimoDatoSkpsCollection);
            em.persist(skyEventos);
            for (EnvioCorreos envioCorreosCollectionEnvioCorreos : skyEventos.getEnvioCorreosCollection()) {
                SkyEventos oldSkyEventosOfEnvioCorreosCollectionEnvioCorreos = envioCorreosCollectionEnvioCorreos.getSkyEventos();
                envioCorreosCollectionEnvioCorreos.setSkyEventos(skyEventos);
                envioCorreosCollectionEnvioCorreos = em.merge(envioCorreosCollectionEnvioCorreos);
                if (oldSkyEventosOfEnvioCorreosCollectionEnvioCorreos != null) {
                    oldSkyEventosOfEnvioCorreosCollectionEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionEnvioCorreos);
                    oldSkyEventosOfEnvioCorreosCollectionEnvioCorreos = em.merge(oldSkyEventosOfEnvioCorreosCollectionEnvioCorreos);
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionUltimoDatoSkps : skyEventos.getUltimoDatoSkpsCollection()) {
                SkyEventos oldIdSkyEventoOfUltimoDatoSkpsCollectionUltimoDatoSkps = ultimoDatoSkpsCollectionUltimoDatoSkps.getIdSkyEvento();
                ultimoDatoSkpsCollectionUltimoDatoSkps.setIdSkyEvento(skyEventos);
                ultimoDatoSkpsCollectionUltimoDatoSkps = em.merge(ultimoDatoSkpsCollectionUltimoDatoSkps);
                if (oldIdSkyEventoOfUltimoDatoSkpsCollectionUltimoDatoSkps != null) {
                    oldIdSkyEventoOfUltimoDatoSkpsCollectionUltimoDatoSkps.getUltimoDatoSkpsCollection().remove(ultimoDatoSkpsCollectionUltimoDatoSkps);
                    oldIdSkyEventoOfUltimoDatoSkpsCollectionUltimoDatoSkps = em.merge(oldIdSkyEventoOfUltimoDatoSkpsCollectionUltimoDatoSkps);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SkyEventos skyEventos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SkyEventos persistentSkyEventos = em.find(SkyEventos.class, skyEventos.getIdSkyEvento());
            Collection<EnvioCorreos> envioCorreosCollectionOld = persistentSkyEventos.getEnvioCorreosCollection();
            Collection<EnvioCorreos> envioCorreosCollectionNew = skyEventos.getEnvioCorreosCollection();
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionOld = persistentSkyEventos.getUltimoDatoSkpsCollection();
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionNew = skyEventos.getUltimoDatoSkpsCollection();
            List<String> illegalOrphanMessages = null;
            for (EnvioCorreos envioCorreosCollectionOldEnvioCorreos : envioCorreosCollectionOld) {
                if (!envioCorreosCollectionNew.contains(envioCorreosCollectionOldEnvioCorreos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnvioCorreos " + envioCorreosCollectionOldEnvioCorreos + " since its skyEventos field is not nullable.");
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionOldUltimoDatoSkps : ultimoDatoSkpsCollectionOld) {
                if (!ultimoDatoSkpsCollectionNew.contains(ultimoDatoSkpsCollectionOldUltimoDatoSkps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UltimoDatoSkps " + ultimoDatoSkpsCollectionOldUltimoDatoSkps + " since its idSkyEvento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<EnvioCorreos> attachedEnvioCorreosCollectionNew = new ArrayList<EnvioCorreos>();
            for (EnvioCorreos envioCorreosCollectionNewEnvioCorreosToAttach : envioCorreosCollectionNew) {
                envioCorreosCollectionNewEnvioCorreosToAttach = em.getReference(envioCorreosCollectionNewEnvioCorreosToAttach.getClass(), envioCorreosCollectionNewEnvioCorreosToAttach.getEnvioCorreosPK());
                attachedEnvioCorreosCollectionNew.add(envioCorreosCollectionNewEnvioCorreosToAttach);
            }
            envioCorreosCollectionNew = attachedEnvioCorreosCollectionNew;
            skyEventos.setEnvioCorreosCollection(envioCorreosCollectionNew);
            Collection<UltimoDatoSkps> attachedUltimoDatoSkpsCollectionNew = new ArrayList<UltimoDatoSkps>();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach : ultimoDatoSkpsCollectionNew) {
                ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach = em.getReference(ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach.getClass(), ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach.getIdUltimoDatoSkp());
                attachedUltimoDatoSkpsCollectionNew.add(ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach);
            }
            ultimoDatoSkpsCollectionNew = attachedUltimoDatoSkpsCollectionNew;
            skyEventos.setUltimoDatoSkpsCollection(ultimoDatoSkpsCollectionNew);
            skyEventos = em.merge(skyEventos);
            for (EnvioCorreos envioCorreosCollectionNewEnvioCorreos : envioCorreosCollectionNew) {
                if (!envioCorreosCollectionOld.contains(envioCorreosCollectionNewEnvioCorreos)) {
                    SkyEventos oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos = envioCorreosCollectionNewEnvioCorreos.getSkyEventos();
                    envioCorreosCollectionNewEnvioCorreos.setSkyEventos(skyEventos);
                    envioCorreosCollectionNewEnvioCorreos = em.merge(envioCorreosCollectionNewEnvioCorreos);
                    if (oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos != null && !oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos.equals(skyEventos)) {
                        oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionNewEnvioCorreos);
                        oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos = em.merge(oldSkyEventosOfEnvioCorreosCollectionNewEnvioCorreos);
                    }
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionNewUltimoDatoSkps : ultimoDatoSkpsCollectionNew) {
                if (!ultimoDatoSkpsCollectionOld.contains(ultimoDatoSkpsCollectionNewUltimoDatoSkps)) {
                    SkyEventos oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps = ultimoDatoSkpsCollectionNewUltimoDatoSkps.getIdSkyEvento();
                    ultimoDatoSkpsCollectionNewUltimoDatoSkps.setIdSkyEvento(skyEventos);
                    ultimoDatoSkpsCollectionNewUltimoDatoSkps = em.merge(ultimoDatoSkpsCollectionNewUltimoDatoSkps);
                    if (oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps != null && !oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps.equals(skyEventos)) {
                        oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps.getUltimoDatoSkpsCollection().remove(ultimoDatoSkpsCollectionNewUltimoDatoSkps);
                        oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps = em.merge(oldIdSkyEventoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = skyEventos.getIdSkyEvento();
                if (findSkyEventos(id) == null) {
                    throw new NonexistentEntityException("The skyEventos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SkyEventos skyEventos;
            try {
                skyEventos = em.getReference(SkyEventos.class, id);
                skyEventos.getIdSkyEvento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The skyEventos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EnvioCorreos> envioCorreosCollectionOrphanCheck = skyEventos.getEnvioCorreosCollection();
            for (EnvioCorreos envioCorreosCollectionOrphanCheckEnvioCorreos : envioCorreosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SkyEventos (" + skyEventos + ") cannot be destroyed since the EnvioCorreos " + envioCorreosCollectionOrphanCheckEnvioCorreos + " in its envioCorreosCollection field has a non-nullable skyEventos field.");
            }
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionOrphanCheck = skyEventos.getUltimoDatoSkpsCollection();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionOrphanCheckUltimoDatoSkps : ultimoDatoSkpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SkyEventos (" + skyEventos + ") cannot be destroyed since the UltimoDatoSkps " + ultimoDatoSkpsCollectionOrphanCheckUltimoDatoSkps + " in its ultimoDatoSkpsCollection field has a non-nullable idSkyEvento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(skyEventos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SkyEventos> findSkyEventosEntities() {
        return findSkyEventosEntities(true, -1, -1);
    }

    public List<SkyEventos> findSkyEventosEntities(int maxResults, int firstResult) {
        return findSkyEventosEntities(false, maxResults, firstResult);
    }

    private List<SkyEventos> findSkyEventosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SkyEventos.class));
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

    public SkyEventos findSkyEventos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SkyEventos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSkyEventosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SkyEventos> rt = cq.from(SkyEventos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public SkyEventos findSkyEventosByParametro(short parameter) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<SkyEventos> qry;
            qry = em.createNamedQuery("SkyEventos.findByParametro", SkyEventos.class);
            qry.setParameter("parametro", parameter);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public SkyEventos findSkyEventosByParametroAndEvent(short parameter, short event) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<SkyEventos> qry;
            qry = em.createNamedQuery("SkyEventos.findByParametroEvent", SkyEventos.class);
            qry.setParameter("parametro", parameter);
            qry.setParameter("evento", event);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public SkyEventos findSkyEventosByEvento(short evento) {
        EntityManager em = getEntityManager();
        try {
            List<SkyEventos> lstSkyEventos;
            SkyEventos se = null;

            TypedQuery<SkyEventos> qry;
            qry = em.createNamedQuery("SkyEventos.findByEvento", SkyEventos.class);
            qry.setParameter("evento", evento);
            lstSkyEventos = qry.getResultList();
            for (SkyEventos skyEventos : lstSkyEventos) {
                se = skyEventos;
                break;
            }
            return se;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
