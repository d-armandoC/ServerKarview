/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.logic.Generos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Personas;
import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class GenerosJpaController implements Serializable {

    public GenerosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Generos generos) {
        if (generos.getPersonasCollection() == null) {
            generos.setPersonasCollection(new ArrayList<Personas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Personas> attachedPersonasCollection = new ArrayList<Personas>();
            for (Personas personasCollectionPersonasToAttach : generos.getPersonasCollection()) {
                personasCollectionPersonasToAttach = em.getReference(personasCollectionPersonasToAttach.getClass(), personasCollectionPersonasToAttach.getIdPersona());
                attachedPersonasCollection.add(personasCollectionPersonasToAttach);
            }
            generos.setPersonasCollection(attachedPersonasCollection);
            em.persist(generos);
            for (Personas personasCollectionPersonas : generos.getPersonasCollection()) {
                Generos oldIdGeneroOfPersonasCollectionPersonas = personasCollectionPersonas.getIdGenero();
                personasCollectionPersonas.setIdGenero(generos);
                personasCollectionPersonas = em.merge(personasCollectionPersonas);
                if (oldIdGeneroOfPersonasCollectionPersonas != null) {
                    oldIdGeneroOfPersonasCollectionPersonas.getPersonasCollection().remove(personasCollectionPersonas);
                    oldIdGeneroOfPersonasCollectionPersonas = em.merge(oldIdGeneroOfPersonasCollectionPersonas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Generos generos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generos persistentGeneros = em.find(Generos.class, generos.getIdGenero());
            Collection<Personas> personasCollectionOld = persistentGeneros.getPersonasCollection();
            Collection<Personas> personasCollectionNew = generos.getPersonasCollection();
            List<String> illegalOrphanMessages = null;
            for (Personas personasCollectionOldPersonas : personasCollectionOld) {
                if (!personasCollectionNew.contains(personasCollectionOldPersonas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personas " + personasCollectionOldPersonas + " since its idGenero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Personas> attachedPersonasCollectionNew = new ArrayList<Personas>();
            for (Personas personasCollectionNewPersonasToAttach : personasCollectionNew) {
                personasCollectionNewPersonasToAttach = em.getReference(personasCollectionNewPersonasToAttach.getClass(), personasCollectionNewPersonasToAttach.getIdPersona());
                attachedPersonasCollectionNew.add(personasCollectionNewPersonasToAttach);
            }
            personasCollectionNew = attachedPersonasCollectionNew;
            generos.setPersonasCollection(personasCollectionNew);
            generos = em.merge(generos);
            for (Personas personasCollectionNewPersonas : personasCollectionNew) {
                if (!personasCollectionOld.contains(personasCollectionNewPersonas)) {
                    Generos oldIdGeneroOfPersonasCollectionNewPersonas = personasCollectionNewPersonas.getIdGenero();
                    personasCollectionNewPersonas.setIdGenero(generos);
                    personasCollectionNewPersonas = em.merge(personasCollectionNewPersonas);
                    if (oldIdGeneroOfPersonasCollectionNewPersonas != null && !oldIdGeneroOfPersonasCollectionNewPersonas.equals(generos)) {
                        oldIdGeneroOfPersonasCollectionNewPersonas.getPersonasCollection().remove(personasCollectionNewPersonas);
                        oldIdGeneroOfPersonasCollectionNewPersonas = em.merge(oldIdGeneroOfPersonasCollectionNewPersonas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = generos.getIdGenero();
                if (findGeneros(id) == null) {
                    throw new NonexistentEntityException("The generos with id " + id + " no longer exists.");
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
            Generos generos;
            try {
                generos = em.getReference(Generos.class, id);
                generos.getIdGenero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Personas> personasCollectionOrphanCheck = generos.getPersonasCollection();
            for (Personas personasCollectionOrphanCheckPersonas : personasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Generos (" + generos + ") cannot be destroyed since the Personas " + personasCollectionOrphanCheckPersonas + " in its personasCollection field has a non-nullable idGenero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(generos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Generos> findGenerosEntities() {
        return findGenerosEntities(true, -1, -1);
    }

    public List<Generos> findGenerosEntities(int maxResults, int firstResult) {
        return findGenerosEntities(false, maxResults, firstResult);
    }

    private List<Generos> findGenerosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Generos.class));
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

    public Generos findGeneros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Generos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenerosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Generos> rt = cq.from(Generos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
