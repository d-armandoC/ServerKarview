/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Empresas;
import com.kradac.karview.entities.logic.EnvioCorreos;
import com.kradac.karview.entities.logic.EnvioCorreosPK;
import com.kradac.karview.entities.logic.SkyEventos;
import com.kradac.karview.entities.logic.Personas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        envioCorreos.getEnvioCorreosPK().setIdPersona(envioCorreos.getPersonas().getIdPersona());
        envioCorreos.getEnvioCorreosPK().setIdSkyEvento(envioCorreos.getSkyEventos().getIdSkyEvento());
        envioCorreos.getEnvioCorreosPK().setIdEmpresa(envioCorreos.getEmpresas().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas empresas = envioCorreos.getEmpresas();
            if (empresas != null) {
                empresas = em.getReference(empresas.getClass(), empresas.getIdEmpresa());
                envioCorreos.setEmpresas(empresas);
            }
            SkyEventos skyEventos = envioCorreos.getSkyEventos();
            if (skyEventos != null) {
                skyEventos = em.getReference(skyEventos.getClass(), skyEventos.getIdSkyEvento());
                envioCorreos.setSkyEventos(skyEventos);
            }
            Personas personas = envioCorreos.getPersonas();
            if (personas != null) {
                personas = em.getReference(personas.getClass(), personas.getIdPersona());
                envioCorreos.setPersonas(personas);
            }
            em.persist(envioCorreos);
            if (empresas != null) {
                empresas.getEnvioCorreosCollection().add(envioCorreos);
                empresas = em.merge(empresas);
            }
            if (skyEventos != null) {
                skyEventos.getEnvioCorreosCollection().add(envioCorreos);
                skyEventos = em.merge(skyEventos);
            }
            if (personas != null) {
                personas.getEnvioCorreosCollection().add(envioCorreos);
                personas = em.merge(personas);
            }
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
        envioCorreos.getEnvioCorreosPK().setIdPersona(envioCorreos.getPersonas().getIdPersona());
        envioCorreos.getEnvioCorreosPK().setIdSkyEvento(envioCorreos.getSkyEventos().getIdSkyEvento());
        envioCorreos.getEnvioCorreosPK().setIdEmpresa(envioCorreos.getEmpresas().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnvioCorreos persistentEnvioCorreos = em.find(EnvioCorreos.class, envioCorreos.getEnvioCorreosPK());
            Empresas empresasOld = persistentEnvioCorreos.getEmpresas();
            Empresas empresasNew = envioCorreos.getEmpresas();
            SkyEventos skyEventosOld = persistentEnvioCorreos.getSkyEventos();
            SkyEventos skyEventosNew = envioCorreos.getSkyEventos();
            Personas personasOld = persistentEnvioCorreos.getPersonas();
            Personas personasNew = envioCorreos.getPersonas();
            if (empresasNew != null) {
                empresasNew = em.getReference(empresasNew.getClass(), empresasNew.getIdEmpresa());
                envioCorreos.setEmpresas(empresasNew);
            }
            if (skyEventosNew != null) {
                skyEventosNew = em.getReference(skyEventosNew.getClass(), skyEventosNew.getIdSkyEvento());
                envioCorreos.setSkyEventos(skyEventosNew);
            }
            if (personasNew != null) {
                personasNew = em.getReference(personasNew.getClass(), personasNew.getIdPersona());
                envioCorreos.setPersonas(personasNew);
            }
            envioCorreos = em.merge(envioCorreos);
            if (empresasOld != null && !empresasOld.equals(empresasNew)) {
                empresasOld.getEnvioCorreosCollection().remove(envioCorreos);
                empresasOld = em.merge(empresasOld);
            }
            if (empresasNew != null && !empresasNew.equals(empresasOld)) {
                empresasNew.getEnvioCorreosCollection().add(envioCorreos);
                empresasNew = em.merge(empresasNew);
            }
            if (skyEventosOld != null && !skyEventosOld.equals(skyEventosNew)) {
                skyEventosOld.getEnvioCorreosCollection().remove(envioCorreos);
                skyEventosOld = em.merge(skyEventosOld);
            }
            if (skyEventosNew != null && !skyEventosNew.equals(skyEventosOld)) {
                skyEventosNew.getEnvioCorreosCollection().add(envioCorreos);
                skyEventosNew = em.merge(skyEventosNew);
            }
            if (personasOld != null && !personasOld.equals(personasNew)) {
                personasOld.getEnvioCorreosCollection().remove(envioCorreos);
                personasOld = em.merge(personasOld);
            }
            if (personasNew != null && !personasNew.equals(personasOld)) {
                personasNew.getEnvioCorreosCollection().add(envioCorreos);
                personasNew = em.merge(personasNew);
            }
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
            Empresas empresas = envioCorreos.getEmpresas();
            if (empresas != null) {
                empresas.getEnvioCorreosCollection().remove(envioCorreos);
                empresas = em.merge(empresas);
            }
            SkyEventos skyEventos = envioCorreos.getSkyEventos();
            if (skyEventos != null) {
                skyEventos.getEnvioCorreosCollection().remove(envioCorreos);
                skyEventos = em.merge(skyEventos);
            }
            Personas personas = envioCorreos.getPersonas();
            if (personas != null) {
                personas.getEnvioCorreosCollection().remove(envioCorreos);
                personas = em.merge(personas);
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
