/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;


import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.ClaseVehiculos;
import com.kradac.karview.entities.logic.Vehiculos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego C
 */
public class ClaseVehiculosJpaController implements Serializable {

    public ClaseVehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClaseVehiculos claseVehiculos) {
        if (claseVehiculos.getVehiculosCollection() == null) {
            claseVehiculos.setVehiculosCollection(new ArrayList<Vehiculos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Vehiculos> attachedVehiculosCollection = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionVehiculosToAttach : claseVehiculos.getVehiculosCollection()) {
                vehiculosCollectionVehiculosToAttach = em.getReference(vehiculosCollectionVehiculosToAttach.getClass(), vehiculosCollectionVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollection.add(vehiculosCollectionVehiculosToAttach);
            }
            claseVehiculos.setVehiculosCollection(attachedVehiculosCollection);
            em.persist(claseVehiculos);
            for (Vehiculos vehiculosCollectionVehiculos : claseVehiculos.getVehiculosCollection()) {
                ClaseVehiculos oldIdClaseVehiculoOfVehiculosCollectionVehiculos = vehiculosCollectionVehiculos.getIdClaseVehiculo();
                vehiculosCollectionVehiculos.setIdClaseVehiculo(claseVehiculos);
                vehiculosCollectionVehiculos = em.merge(vehiculosCollectionVehiculos);
                if (oldIdClaseVehiculoOfVehiculosCollectionVehiculos != null) {
                    oldIdClaseVehiculoOfVehiculosCollectionVehiculos.getVehiculosCollection().remove(vehiculosCollectionVehiculos);
                    oldIdClaseVehiculoOfVehiculosCollectionVehiculos = em.merge(oldIdClaseVehiculoOfVehiculosCollectionVehiculos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClaseVehiculos claseVehiculos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClaseVehiculos persistentClaseVehiculos = em.find(ClaseVehiculos.class, claseVehiculos.getIdClaseVehiculo());
            Collection<Vehiculos> vehiculosCollectionOld = persistentClaseVehiculos.getVehiculosCollection();
            Collection<Vehiculos> vehiculosCollectionNew = claseVehiculos.getVehiculosCollection();
            List<String> illegalOrphanMessages = null;
            for (Vehiculos vehiculosCollectionOldVehiculos : vehiculosCollectionOld) {
                if (!vehiculosCollectionNew.contains(vehiculosCollectionOldVehiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehiculos " + vehiculosCollectionOldVehiculos + " since its idClaseVehiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Vehiculos> attachedVehiculosCollectionNew = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionNewVehiculosToAttach : vehiculosCollectionNew) {
                vehiculosCollectionNewVehiculosToAttach = em.getReference(vehiculosCollectionNewVehiculosToAttach.getClass(), vehiculosCollectionNewVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollectionNew.add(vehiculosCollectionNewVehiculosToAttach);
            }
            vehiculosCollectionNew = attachedVehiculosCollectionNew;
            claseVehiculos.setVehiculosCollection(vehiculosCollectionNew);
            claseVehiculos = em.merge(claseVehiculos);
            for (Vehiculos vehiculosCollectionNewVehiculos : vehiculosCollectionNew) {
                if (!vehiculosCollectionOld.contains(vehiculosCollectionNewVehiculos)) {
                    ClaseVehiculos oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos = vehiculosCollectionNewVehiculos.getIdClaseVehiculo();
                    vehiculosCollectionNewVehiculos.setIdClaseVehiculo(claseVehiculos);
                    vehiculosCollectionNewVehiculos = em.merge(vehiculosCollectionNewVehiculos);
                    if (oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos != null && !oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos.equals(claseVehiculos)) {
                        oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos.getVehiculosCollection().remove(vehiculosCollectionNewVehiculos);
                        oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos = em.merge(oldIdClaseVehiculoOfVehiculosCollectionNewVehiculos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = claseVehiculos.getIdClaseVehiculo();
                if (findClaseVehiculos(id) == null) {
                    throw new NonexistentEntityException("The claseVehiculos with id " + id + " no longer exists.");
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
            ClaseVehiculos claseVehiculos;
            try {
                claseVehiculos = em.getReference(ClaseVehiculos.class, id);
                claseVehiculos.getIdClaseVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The claseVehiculos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Vehiculos> vehiculosCollectionOrphanCheck = claseVehiculos.getVehiculosCollection();
            for (Vehiculos vehiculosCollectionOrphanCheckVehiculos : vehiculosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClaseVehiculos (" + claseVehiculos + ") cannot be destroyed since the Vehiculos " + vehiculosCollectionOrphanCheckVehiculos + " in its vehiculosCollection field has a non-nullable idClaseVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(claseVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClaseVehiculos> findClaseVehiculosEntities() {
        return findClaseVehiculosEntities(true, -1, -1);
    }

    public List<ClaseVehiculos> findClaseVehiculosEntities(int maxResults, int firstResult) {
        return findClaseVehiculosEntities(false, maxResults, firstResult);
    }

    private List<ClaseVehiculos> findClaseVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClaseVehiculos.class));
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

    public ClaseVehiculos findClaseVehiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClaseVehiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getClaseVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClaseVehiculos> rt = cq.from(ClaseVehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
