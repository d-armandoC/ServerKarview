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
import com.kradac.karview.entities.logic.Empresas;
import com.kradac.karview.entities.logic.GeocercaPuntos;
import java.util.ArrayList;
import java.util.Collection;
import com.kradac.karview.entities.logic.GeocercaVehiculos;
import com.kradac.karview.entities.logic.Geocercas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class GeocercasJpaController implements Serializable {

    public GeocercasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Geocercas geocercas) {
        if (geocercas.getGeocercaPuntosCollection() == null) {
            geocercas.setGeocercaPuntosCollection(new ArrayList<GeocercaPuntos>());
        }
        if (geocercas.getGeocercaVehiculosCollection() == null) {
            geocercas.setGeocercaVehiculosCollection(new ArrayList<GeocercaVehiculos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = geocercas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                geocercas.setIdEmpresa(idEmpresa);
            }
            Collection<GeocercaPuntos> attachedGeocercaPuntosCollection = new ArrayList<GeocercaPuntos>();
            for (GeocercaPuntos geocercaPuntosCollectionGeocercaPuntosToAttach : geocercas.getGeocercaPuntosCollection()) {
                geocercaPuntosCollectionGeocercaPuntosToAttach = em.getReference(geocercaPuntosCollectionGeocercaPuntosToAttach.getClass(), geocercaPuntosCollectionGeocercaPuntosToAttach.getGeocercaPuntosPK());
                attachedGeocercaPuntosCollection.add(geocercaPuntosCollectionGeocercaPuntosToAttach);
            }
            geocercas.setGeocercaPuntosCollection(attachedGeocercaPuntosCollection);
            Collection<GeocercaVehiculos> attachedGeocercaVehiculosCollection = new ArrayList<GeocercaVehiculos>();
            for (GeocercaVehiculos geocercaVehiculosCollectionGeocercaVehiculosToAttach : geocercas.getGeocercaVehiculosCollection()) {
                geocercaVehiculosCollectionGeocercaVehiculosToAttach = em.getReference(geocercaVehiculosCollectionGeocercaVehiculosToAttach.getClass(), geocercaVehiculosCollectionGeocercaVehiculosToAttach.getGeocercaVehiculosPK());
                attachedGeocercaVehiculosCollection.add(geocercaVehiculosCollectionGeocercaVehiculosToAttach);
            }
            geocercas.setGeocercaVehiculosCollection(attachedGeocercaVehiculosCollection);
            em.persist(geocercas);
            if (idEmpresa != null) {
                idEmpresa.getGeocercasCollection().add(geocercas);
                idEmpresa = em.merge(idEmpresa);
            }
            for (GeocercaPuntos geocercaPuntosCollectionGeocercaPuntos : geocercas.getGeocercaPuntosCollection()) {
                Geocercas oldGeocercasOfGeocercaPuntosCollectionGeocercaPuntos = geocercaPuntosCollectionGeocercaPuntos.getGeocercas();
                geocercaPuntosCollectionGeocercaPuntos.setGeocercas(geocercas);
                geocercaPuntosCollectionGeocercaPuntos = em.merge(geocercaPuntosCollectionGeocercaPuntos);
                if (oldGeocercasOfGeocercaPuntosCollectionGeocercaPuntos != null) {
                    oldGeocercasOfGeocercaPuntosCollectionGeocercaPuntos.getGeocercaPuntosCollection().remove(geocercaPuntosCollectionGeocercaPuntos);
                    oldGeocercasOfGeocercaPuntosCollectionGeocercaPuntos = em.merge(oldGeocercasOfGeocercaPuntosCollectionGeocercaPuntos);
                }
            }
            for (GeocercaVehiculos geocercaVehiculosCollectionGeocercaVehiculos : geocercas.getGeocercaVehiculosCollection()) {
                Geocercas oldGeocercasOfGeocercaVehiculosCollectionGeocercaVehiculos = geocercaVehiculosCollectionGeocercaVehiculos.getGeocercas();
                geocercaVehiculosCollectionGeocercaVehiculos.setGeocercas(geocercas);
                geocercaVehiculosCollectionGeocercaVehiculos = em.merge(geocercaVehiculosCollectionGeocercaVehiculos);
                if (oldGeocercasOfGeocercaVehiculosCollectionGeocercaVehiculos != null) {
                    oldGeocercasOfGeocercaVehiculosCollectionGeocercaVehiculos.getGeocercaVehiculosCollection().remove(geocercaVehiculosCollectionGeocercaVehiculos);
                    oldGeocercasOfGeocercaVehiculosCollectionGeocercaVehiculos = em.merge(oldGeocercasOfGeocercaVehiculosCollectionGeocercaVehiculos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Geocercas geocercas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Geocercas persistentGeocercas = em.find(Geocercas.class, geocercas.getIdGeocerca());
            Empresas idEmpresaOld = persistentGeocercas.getIdEmpresa();
            Empresas idEmpresaNew = geocercas.getIdEmpresa();
            Collection<GeocercaPuntos> geocercaPuntosCollectionOld = persistentGeocercas.getGeocercaPuntosCollection();
            Collection<GeocercaPuntos> geocercaPuntosCollectionNew = geocercas.getGeocercaPuntosCollection();
            Collection<GeocercaVehiculos> geocercaVehiculosCollectionOld = persistentGeocercas.getGeocercaVehiculosCollection();
            Collection<GeocercaVehiculos> geocercaVehiculosCollectionNew = geocercas.getGeocercaVehiculosCollection();
            List<String> illegalOrphanMessages = null;
            for (GeocercaPuntos geocercaPuntosCollectionOldGeocercaPuntos : geocercaPuntosCollectionOld) {
                if (!geocercaPuntosCollectionNew.contains(geocercaPuntosCollectionOldGeocercaPuntos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GeocercaPuntos " + geocercaPuntosCollectionOldGeocercaPuntos + " since its geocercas field is not nullable.");
                }
            }
            for (GeocercaVehiculos geocercaVehiculosCollectionOldGeocercaVehiculos : geocercaVehiculosCollectionOld) {
                if (!geocercaVehiculosCollectionNew.contains(geocercaVehiculosCollectionOldGeocercaVehiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GeocercaVehiculos " + geocercaVehiculosCollectionOldGeocercaVehiculos + " since its geocercas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                geocercas.setIdEmpresa(idEmpresaNew);
            }
            Collection<GeocercaPuntos> attachedGeocercaPuntosCollectionNew = new ArrayList<GeocercaPuntos>();
            for (GeocercaPuntos geocercaPuntosCollectionNewGeocercaPuntosToAttach : geocercaPuntosCollectionNew) {
                geocercaPuntosCollectionNewGeocercaPuntosToAttach = em.getReference(geocercaPuntosCollectionNewGeocercaPuntosToAttach.getClass(), geocercaPuntosCollectionNewGeocercaPuntosToAttach.getGeocercaPuntosPK());
                attachedGeocercaPuntosCollectionNew.add(geocercaPuntosCollectionNewGeocercaPuntosToAttach);
            }
            geocercaPuntosCollectionNew = attachedGeocercaPuntosCollectionNew;
            geocercas.setGeocercaPuntosCollection(geocercaPuntosCollectionNew);
            Collection<GeocercaVehiculos> attachedGeocercaVehiculosCollectionNew = new ArrayList<GeocercaVehiculos>();
            for (GeocercaVehiculos geocercaVehiculosCollectionNewGeocercaVehiculosToAttach : geocercaVehiculosCollectionNew) {
                geocercaVehiculosCollectionNewGeocercaVehiculosToAttach = em.getReference(geocercaVehiculosCollectionNewGeocercaVehiculosToAttach.getClass(), geocercaVehiculosCollectionNewGeocercaVehiculosToAttach.getGeocercaVehiculosPK());
                attachedGeocercaVehiculosCollectionNew.add(geocercaVehiculosCollectionNewGeocercaVehiculosToAttach);
            }
            geocercaVehiculosCollectionNew = attachedGeocercaVehiculosCollectionNew;
            geocercas.setGeocercaVehiculosCollection(geocercaVehiculosCollectionNew);
            geocercas = em.merge(geocercas);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getGeocercasCollection().remove(geocercas);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getGeocercasCollection().add(geocercas);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (GeocercaPuntos geocercaPuntosCollectionNewGeocercaPuntos : geocercaPuntosCollectionNew) {
                if (!geocercaPuntosCollectionOld.contains(geocercaPuntosCollectionNewGeocercaPuntos)) {
                    Geocercas oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos = geocercaPuntosCollectionNewGeocercaPuntos.getGeocercas();
                    geocercaPuntosCollectionNewGeocercaPuntos.setGeocercas(geocercas);
                    geocercaPuntosCollectionNewGeocercaPuntos = em.merge(geocercaPuntosCollectionNewGeocercaPuntos);
                    if (oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos != null && !oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos.equals(geocercas)) {
                        oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos.getGeocercaPuntosCollection().remove(geocercaPuntosCollectionNewGeocercaPuntos);
                        oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos = em.merge(oldGeocercasOfGeocercaPuntosCollectionNewGeocercaPuntos);
                    }
                }
            }
            for (GeocercaVehiculos geocercaVehiculosCollectionNewGeocercaVehiculos : geocercaVehiculosCollectionNew) {
                if (!geocercaVehiculosCollectionOld.contains(geocercaVehiculosCollectionNewGeocercaVehiculos)) {
                    Geocercas oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos = geocercaVehiculosCollectionNewGeocercaVehiculos.getGeocercas();
                    geocercaVehiculosCollectionNewGeocercaVehiculos.setGeocercas(geocercas);
                    geocercaVehiculosCollectionNewGeocercaVehiculos = em.merge(geocercaVehiculosCollectionNewGeocercaVehiculos);
                    if (oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos != null && !oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos.equals(geocercas)) {
                        oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos.getGeocercaVehiculosCollection().remove(geocercaVehiculosCollectionNewGeocercaVehiculos);
                        oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos = em.merge(oldGeocercasOfGeocercaVehiculosCollectionNewGeocercaVehiculos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = geocercas.getIdGeocerca();
                if (findGeocercas(id) == null) {
                    throw new NonexistentEntityException("The geocercas with id " + id + " no longer exists.");
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
            Geocercas geocercas;
            try {
                geocercas = em.getReference(Geocercas.class, id);
                geocercas.getIdGeocerca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The geocercas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<GeocercaPuntos> geocercaPuntosCollectionOrphanCheck = geocercas.getGeocercaPuntosCollection();
            for (GeocercaPuntos geocercaPuntosCollectionOrphanCheckGeocercaPuntos : geocercaPuntosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Geocercas (" + geocercas + ") cannot be destroyed since the GeocercaPuntos " + geocercaPuntosCollectionOrphanCheckGeocercaPuntos + " in its geocercaPuntosCollection field has a non-nullable geocercas field.");
            }
            Collection<GeocercaVehiculos> geocercaVehiculosCollectionOrphanCheck = geocercas.getGeocercaVehiculosCollection();
            for (GeocercaVehiculos geocercaVehiculosCollectionOrphanCheckGeocercaVehiculos : geocercaVehiculosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Geocercas (" + geocercas + ") cannot be destroyed since the GeocercaVehiculos " + geocercaVehiculosCollectionOrphanCheckGeocercaVehiculos + " in its geocercaVehiculosCollection field has a non-nullable geocercas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresas idEmpresa = geocercas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getGeocercasCollection().remove(geocercas);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(geocercas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Geocercas> findGeocercasEntities() {
        return findGeocercasEntities(true, -1, -1);
    }

    public List<Geocercas> findGeocercasEntities(int maxResults, int firstResult) {
        return findGeocercasEntities(false, maxResults, firstResult);
    }

    private List<Geocercas> findGeocercasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Geocercas.class));
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

    public Geocercas findGeocercas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Geocercas.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeocercasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Geocercas> rt = cq.from(Geocercas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
