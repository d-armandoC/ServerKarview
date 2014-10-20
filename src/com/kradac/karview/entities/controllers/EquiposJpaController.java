/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.Equipos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Vehiculos;
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
public class EquiposJpaController implements Serializable {

    public EquiposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipos equipos) {
        if (equipos.getVehiculosCollection() == null) {
            equipos.setVehiculosCollection(new ArrayList<Vehiculos>());
        }
        if (equipos.getUltimoDatoSkpsCollection() == null) {
            equipos.setUltimoDatoSkpsCollection(new ArrayList<UltimoDatoSkps>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Vehiculos> attachedVehiculosCollection = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionVehiculosToAttach : equipos.getVehiculosCollection()) {
                vehiculosCollectionVehiculosToAttach = em.getReference(vehiculosCollectionVehiculosToAttach.getClass(), vehiculosCollectionVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollection.add(vehiculosCollectionVehiculosToAttach);
            }
            equipos.setVehiculosCollection(attachedVehiculosCollection);
            Collection<UltimoDatoSkps> attachedUltimoDatoSkpsCollection = new ArrayList<UltimoDatoSkps>();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach : equipos.getUltimoDatoSkpsCollection()) {
                ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach = em.getReference(ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach.getClass(), ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach.getIdUltimoDatoSkp());
                attachedUltimoDatoSkpsCollection.add(ultimoDatoSkpsCollectionUltimoDatoSkpsToAttach);
            }
            equipos.setUltimoDatoSkpsCollection(attachedUltimoDatoSkpsCollection);
            em.persist(equipos);
            for (Vehiculos vehiculosCollectionVehiculos : equipos.getVehiculosCollection()) {
                Equipos oldIdEquipoOfVehiculosCollectionVehiculos = vehiculosCollectionVehiculos.getIdEquipo();
                vehiculosCollectionVehiculos.setIdEquipo(equipos);
                vehiculosCollectionVehiculos = em.merge(vehiculosCollectionVehiculos);
                if (oldIdEquipoOfVehiculosCollectionVehiculos != null) {
                    oldIdEquipoOfVehiculosCollectionVehiculos.getVehiculosCollection().remove(vehiculosCollectionVehiculos);
                    oldIdEquipoOfVehiculosCollectionVehiculos = em.merge(oldIdEquipoOfVehiculosCollectionVehiculos);
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionUltimoDatoSkps : equipos.getUltimoDatoSkpsCollection()) {
                Equipos oldIdEquipoOfUltimoDatoSkpsCollectionUltimoDatoSkps = ultimoDatoSkpsCollectionUltimoDatoSkps.getIdEquipo();
                ultimoDatoSkpsCollectionUltimoDatoSkps.setIdEquipo(equipos);
                ultimoDatoSkpsCollectionUltimoDatoSkps = em.merge(ultimoDatoSkpsCollectionUltimoDatoSkps);
                if (oldIdEquipoOfUltimoDatoSkpsCollectionUltimoDatoSkps != null) {
                    oldIdEquipoOfUltimoDatoSkpsCollectionUltimoDatoSkps.getUltimoDatoSkpsCollection().remove(ultimoDatoSkpsCollectionUltimoDatoSkps);
                    oldIdEquipoOfUltimoDatoSkpsCollectionUltimoDatoSkps = em.merge(oldIdEquipoOfUltimoDatoSkpsCollectionUltimoDatoSkps);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipos equipos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipos persistentEquipos = em.find(Equipos.class, equipos.getIdEquipo());
            Collection<Vehiculos> vehiculosCollectionOld = persistentEquipos.getVehiculosCollection();
            Collection<Vehiculos> vehiculosCollectionNew = equipos.getVehiculosCollection();
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionOld = persistentEquipos.getUltimoDatoSkpsCollection();
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionNew = equipos.getUltimoDatoSkpsCollection();
            List<String> illegalOrphanMessages = null;
            for (Vehiculos vehiculosCollectionOldVehiculos : vehiculosCollectionOld) {
                if (!vehiculosCollectionNew.contains(vehiculosCollectionOldVehiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehiculos " + vehiculosCollectionOldVehiculos + " since its idEquipo field is not nullable.");
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionOldUltimoDatoSkps : ultimoDatoSkpsCollectionOld) {
                if (!ultimoDatoSkpsCollectionNew.contains(ultimoDatoSkpsCollectionOldUltimoDatoSkps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UltimoDatoSkps " + ultimoDatoSkpsCollectionOldUltimoDatoSkps + " since its idEquipo field is not nullable.");
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
            equipos.setVehiculosCollection(vehiculosCollectionNew);
            Collection<UltimoDatoSkps> attachedUltimoDatoSkpsCollectionNew = new ArrayList<UltimoDatoSkps>();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach : ultimoDatoSkpsCollectionNew) {
                ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach = em.getReference(ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach.getClass(), ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach.getIdUltimoDatoSkp());
                attachedUltimoDatoSkpsCollectionNew.add(ultimoDatoSkpsCollectionNewUltimoDatoSkpsToAttach);
            }
            ultimoDatoSkpsCollectionNew = attachedUltimoDatoSkpsCollectionNew;
            equipos.setUltimoDatoSkpsCollection(ultimoDatoSkpsCollectionNew);
            equipos = em.merge(equipos);
            for (Vehiculos vehiculosCollectionNewVehiculos : vehiculosCollectionNew) {
                if (!vehiculosCollectionOld.contains(vehiculosCollectionNewVehiculos)) {
                    Equipos oldIdEquipoOfVehiculosCollectionNewVehiculos = vehiculosCollectionNewVehiculos.getIdEquipo();
                    vehiculosCollectionNewVehiculos.setIdEquipo(equipos);
                    vehiculosCollectionNewVehiculos = em.merge(vehiculosCollectionNewVehiculos);
                    if (oldIdEquipoOfVehiculosCollectionNewVehiculos != null && !oldIdEquipoOfVehiculosCollectionNewVehiculos.equals(equipos)) {
                        oldIdEquipoOfVehiculosCollectionNewVehiculos.getVehiculosCollection().remove(vehiculosCollectionNewVehiculos);
                        oldIdEquipoOfVehiculosCollectionNewVehiculos = em.merge(oldIdEquipoOfVehiculosCollectionNewVehiculos);
                    }
                }
            }
            for (UltimoDatoSkps ultimoDatoSkpsCollectionNewUltimoDatoSkps : ultimoDatoSkpsCollectionNew) {
                if (!ultimoDatoSkpsCollectionOld.contains(ultimoDatoSkpsCollectionNewUltimoDatoSkps)) {
                    Equipos oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps = ultimoDatoSkpsCollectionNewUltimoDatoSkps.getIdEquipo();
                    ultimoDatoSkpsCollectionNewUltimoDatoSkps.setIdEquipo(equipos);
                    ultimoDatoSkpsCollectionNewUltimoDatoSkps = em.merge(ultimoDatoSkpsCollectionNewUltimoDatoSkps);
                    if (oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps != null && !oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps.equals(equipos)) {
                        oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps.getUltimoDatoSkpsCollection().remove(ultimoDatoSkpsCollectionNewUltimoDatoSkps);
                        oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps = em.merge(oldIdEquipoOfUltimoDatoSkpsCollectionNewUltimoDatoSkps);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipos.getIdEquipo();
                if (findEquipos(id) == null) {
                    throw new NonexistentEntityException("The equipos with id " + id + " no longer exists.");
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
            Equipos equipos;
            try {
                equipos = em.getReference(Equipos.class, id);
                equipos.getIdEquipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Vehiculos> vehiculosCollectionOrphanCheck = equipos.getVehiculosCollection();
            for (Vehiculos vehiculosCollectionOrphanCheckVehiculos : vehiculosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipos (" + equipos + ") cannot be destroyed since the Vehiculos " + vehiculosCollectionOrphanCheckVehiculos + " in its vehiculosCollection field has a non-nullable idEquipo field.");
            }
            Collection<UltimoDatoSkps> ultimoDatoSkpsCollectionOrphanCheck = equipos.getUltimoDatoSkpsCollection();
            for (UltimoDatoSkps ultimoDatoSkpsCollectionOrphanCheckUltimoDatoSkps : ultimoDatoSkpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipos (" + equipos + ") cannot be destroyed since the UltimoDatoSkps " + ultimoDatoSkpsCollectionOrphanCheckUltimoDatoSkps + " in its ultimoDatoSkpsCollection field has a non-nullable idEquipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(equipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipos> findEquiposEntities() {
        return findEquiposEntities(true, -1, -1);
    }

    public List<Equipos> findEquiposEntities(int maxResults, int firstResult) {
        return findEquiposEntities(false, maxResults, firstResult);
    }

    private List<Equipos> findEquiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipos.class));
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

    public Equipos findEquipos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipos> rt = cq.from(Equipos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public Equipos findEquiposByEquipo(String equipo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Equipos> qry;
            qry = em.createNamedQuery("Equipos.findByEquipo", Equipos.class);
            qry.setParameter("equipo", equipo);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
