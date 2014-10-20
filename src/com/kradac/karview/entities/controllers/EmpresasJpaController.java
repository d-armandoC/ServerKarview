/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.Empresas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.EnvioCorreos;
import java.util.ArrayList;
import java.util.Collection;
import com.kradac.karview.entities.logic.Usuarios;
import com.kradac.karview.entities.logic.Personas;
import com.kradac.karview.entities.logic.Vehiculos;
import com.kradac.karview.entities.logic.Geocercas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class EmpresasJpaController implements Serializable {

    public EmpresasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresas empresas) {
        if (empresas.getEnvioCorreosCollection() == null) {
            empresas.setEnvioCorreosCollection(new ArrayList<EnvioCorreos>());
        }
        if (empresas.getUsuariosCollection() == null) {
            empresas.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        if (empresas.getPersonasCollection() == null) {
            empresas.setPersonasCollection(new ArrayList<Personas>());
        }
        if (empresas.getVehiculosCollection() == null) {
            empresas.setVehiculosCollection(new ArrayList<Vehiculos>());
        }
        if (empresas.getGeocercasCollection() == null) {
            empresas.setGeocercasCollection(new ArrayList<Geocercas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<EnvioCorreos> attachedEnvioCorreosCollection = new ArrayList<EnvioCorreos>();
            for (EnvioCorreos envioCorreosCollectionEnvioCorreosToAttach : empresas.getEnvioCorreosCollection()) {
                envioCorreosCollectionEnvioCorreosToAttach = em.getReference(envioCorreosCollectionEnvioCorreosToAttach.getClass(), envioCorreosCollectionEnvioCorreosToAttach.getEnvioCorreosPK());
                attachedEnvioCorreosCollection.add(envioCorreosCollectionEnvioCorreosToAttach);
            }
            empresas.setEnvioCorreosCollection(attachedEnvioCorreosCollection);
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : empresas.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            empresas.setUsuariosCollection(attachedUsuariosCollection);
            Collection<Personas> attachedPersonasCollection = new ArrayList<Personas>();
            for (Personas personasCollectionPersonasToAttach : empresas.getPersonasCollection()) {
                personasCollectionPersonasToAttach = em.getReference(personasCollectionPersonasToAttach.getClass(), personasCollectionPersonasToAttach.getIdPersona());
                attachedPersonasCollection.add(personasCollectionPersonasToAttach);
            }
            empresas.setPersonasCollection(attachedPersonasCollection);
            Collection<Vehiculos> attachedVehiculosCollection = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionVehiculosToAttach : empresas.getVehiculosCollection()) {
                vehiculosCollectionVehiculosToAttach = em.getReference(vehiculosCollectionVehiculosToAttach.getClass(), vehiculosCollectionVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollection.add(vehiculosCollectionVehiculosToAttach);
            }
            empresas.setVehiculosCollection(attachedVehiculosCollection);
            Collection<Geocercas> attachedGeocercasCollection = new ArrayList<Geocercas>();
            for (Geocercas geocercasCollectionGeocercasToAttach : empresas.getGeocercasCollection()) {
                geocercasCollectionGeocercasToAttach = em.getReference(geocercasCollectionGeocercasToAttach.getClass(), geocercasCollectionGeocercasToAttach.getIdGeocerca());
                attachedGeocercasCollection.add(geocercasCollectionGeocercasToAttach);
            }
            empresas.setGeocercasCollection(attachedGeocercasCollection);
            em.persist(empresas);
            for (EnvioCorreos envioCorreosCollectionEnvioCorreos : empresas.getEnvioCorreosCollection()) {
                Empresas oldEmpresasOfEnvioCorreosCollectionEnvioCorreos = envioCorreosCollectionEnvioCorreos.getEmpresas();
                envioCorreosCollectionEnvioCorreos.setEmpresas(empresas);
                envioCorreosCollectionEnvioCorreos = em.merge(envioCorreosCollectionEnvioCorreos);
                if (oldEmpresasOfEnvioCorreosCollectionEnvioCorreos != null) {
                    oldEmpresasOfEnvioCorreosCollectionEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionEnvioCorreos);
                    oldEmpresasOfEnvioCorreosCollectionEnvioCorreos = em.merge(oldEmpresasOfEnvioCorreosCollectionEnvioCorreos);
                }
            }
            for (Usuarios usuariosCollectionUsuarios : empresas.getUsuariosCollection()) {
                Empresas oldIdEmpresaOfUsuariosCollectionUsuarios = usuariosCollectionUsuarios.getIdEmpresa();
                usuariosCollectionUsuarios.setIdEmpresa(empresas);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
                if (oldIdEmpresaOfUsuariosCollectionUsuarios != null) {
                    oldIdEmpresaOfUsuariosCollectionUsuarios.getUsuariosCollection().remove(usuariosCollectionUsuarios);
                    oldIdEmpresaOfUsuariosCollectionUsuarios = em.merge(oldIdEmpresaOfUsuariosCollectionUsuarios);
                }
            }
            for (Personas personasCollectionPersonas : empresas.getPersonasCollection()) {
                Empresas oldIdEmpresaOfPersonasCollectionPersonas = personasCollectionPersonas.getIdEmpresa();
                personasCollectionPersonas.setIdEmpresa(empresas);
                personasCollectionPersonas = em.merge(personasCollectionPersonas);
                if (oldIdEmpresaOfPersonasCollectionPersonas != null) {
                    oldIdEmpresaOfPersonasCollectionPersonas.getPersonasCollection().remove(personasCollectionPersonas);
                    oldIdEmpresaOfPersonasCollectionPersonas = em.merge(oldIdEmpresaOfPersonasCollectionPersonas);
                }
            }
            for (Vehiculos vehiculosCollectionVehiculos : empresas.getVehiculosCollection()) {
                Empresas oldIdEmpresaOfVehiculosCollectionVehiculos = vehiculosCollectionVehiculos.getIdEmpresa();
                vehiculosCollectionVehiculos.setIdEmpresa(empresas);
                vehiculosCollectionVehiculos = em.merge(vehiculosCollectionVehiculos);
                if (oldIdEmpresaOfVehiculosCollectionVehiculos != null) {
                    oldIdEmpresaOfVehiculosCollectionVehiculos.getVehiculosCollection().remove(vehiculosCollectionVehiculos);
                    oldIdEmpresaOfVehiculosCollectionVehiculos = em.merge(oldIdEmpresaOfVehiculosCollectionVehiculos);
                }
            }
            for (Geocercas geocercasCollectionGeocercas : empresas.getGeocercasCollection()) {
                Empresas oldIdEmpresaOfGeocercasCollectionGeocercas = geocercasCollectionGeocercas.getIdEmpresa();
                geocercasCollectionGeocercas.setIdEmpresa(empresas);
                geocercasCollectionGeocercas = em.merge(geocercasCollectionGeocercas);
                if (oldIdEmpresaOfGeocercasCollectionGeocercas != null) {
                    oldIdEmpresaOfGeocercasCollectionGeocercas.getGeocercasCollection().remove(geocercasCollectionGeocercas);
                    oldIdEmpresaOfGeocercasCollectionGeocercas = em.merge(oldIdEmpresaOfGeocercasCollectionGeocercas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresas empresas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas persistentEmpresas = em.find(Empresas.class, empresas.getIdEmpresa());
            Collection<EnvioCorreos> envioCorreosCollectionOld = persistentEmpresas.getEnvioCorreosCollection();
            Collection<EnvioCorreos> envioCorreosCollectionNew = empresas.getEnvioCorreosCollection();
            Collection<Usuarios> usuariosCollectionOld = persistentEmpresas.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = empresas.getUsuariosCollection();
            Collection<Personas> personasCollectionOld = persistentEmpresas.getPersonasCollection();
            Collection<Personas> personasCollectionNew = empresas.getPersonasCollection();
            Collection<Vehiculos> vehiculosCollectionOld = persistentEmpresas.getVehiculosCollection();
            Collection<Vehiculos> vehiculosCollectionNew = empresas.getVehiculosCollection();
            Collection<Geocercas> geocercasCollectionOld = persistentEmpresas.getGeocercasCollection();
            Collection<Geocercas> geocercasCollectionNew = empresas.getGeocercasCollection();
            List<String> illegalOrphanMessages = null;
            for (EnvioCorreos envioCorreosCollectionOldEnvioCorreos : envioCorreosCollectionOld) {
                if (!envioCorreosCollectionNew.contains(envioCorreosCollectionOldEnvioCorreos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnvioCorreos " + envioCorreosCollectionOldEnvioCorreos + " since its empresas field is not nullable.");
                }
            }
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosCollectionOldUsuarios + " since its idEmpresa field is not nullable.");
                }
            }
            for (Personas personasCollectionOldPersonas : personasCollectionOld) {
                if (!personasCollectionNew.contains(personasCollectionOldPersonas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personas " + personasCollectionOldPersonas + " since its idEmpresa field is not nullable.");
                }
            }
            for (Vehiculos vehiculosCollectionOldVehiculos : vehiculosCollectionOld) {
                if (!vehiculosCollectionNew.contains(vehiculosCollectionOldVehiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehiculos " + vehiculosCollectionOldVehiculos + " since its idEmpresa field is not nullable.");
                }
            }
            for (Geocercas geocercasCollectionOldGeocercas : geocercasCollectionOld) {
                if (!geocercasCollectionNew.contains(geocercasCollectionOldGeocercas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Geocercas " + geocercasCollectionOldGeocercas + " since its idEmpresa field is not nullable.");
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
            empresas.setEnvioCorreosCollection(envioCorreosCollectionNew);
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            empresas.setUsuariosCollection(usuariosCollectionNew);
            Collection<Personas> attachedPersonasCollectionNew = new ArrayList<Personas>();
            for (Personas personasCollectionNewPersonasToAttach : personasCollectionNew) {
                personasCollectionNewPersonasToAttach = em.getReference(personasCollectionNewPersonasToAttach.getClass(), personasCollectionNewPersonasToAttach.getIdPersona());
                attachedPersonasCollectionNew.add(personasCollectionNewPersonasToAttach);
            }
            personasCollectionNew = attachedPersonasCollectionNew;
            empresas.setPersonasCollection(personasCollectionNew);
            Collection<Vehiculos> attachedVehiculosCollectionNew = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionNewVehiculosToAttach : vehiculosCollectionNew) {
                vehiculosCollectionNewVehiculosToAttach = em.getReference(vehiculosCollectionNewVehiculosToAttach.getClass(), vehiculosCollectionNewVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollectionNew.add(vehiculosCollectionNewVehiculosToAttach);
            }
            vehiculosCollectionNew = attachedVehiculosCollectionNew;
            empresas.setVehiculosCollection(vehiculosCollectionNew);
            Collection<Geocercas> attachedGeocercasCollectionNew = new ArrayList<Geocercas>();
            for (Geocercas geocercasCollectionNewGeocercasToAttach : geocercasCollectionNew) {
                geocercasCollectionNewGeocercasToAttach = em.getReference(geocercasCollectionNewGeocercasToAttach.getClass(), geocercasCollectionNewGeocercasToAttach.getIdGeocerca());
                attachedGeocercasCollectionNew.add(geocercasCollectionNewGeocercasToAttach);
            }
            geocercasCollectionNew = attachedGeocercasCollectionNew;
            empresas.setGeocercasCollection(geocercasCollectionNew);
            empresas = em.merge(empresas);
            for (EnvioCorreos envioCorreosCollectionNewEnvioCorreos : envioCorreosCollectionNew) {
                if (!envioCorreosCollectionOld.contains(envioCorreosCollectionNewEnvioCorreos)) {
                    Empresas oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos = envioCorreosCollectionNewEnvioCorreos.getEmpresas();
                    envioCorreosCollectionNewEnvioCorreos.setEmpresas(empresas);
                    envioCorreosCollectionNewEnvioCorreos = em.merge(envioCorreosCollectionNewEnvioCorreos);
                    if (oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos != null && !oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos.equals(empresas)) {
                        oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionNewEnvioCorreos);
                        oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos = em.merge(oldEmpresasOfEnvioCorreosCollectionNewEnvioCorreos);
                    }
                }
            }
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    Empresas oldIdEmpresaOfUsuariosCollectionNewUsuarios = usuariosCollectionNewUsuarios.getIdEmpresa();
                    usuariosCollectionNewUsuarios.setIdEmpresa(empresas);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
                    if (oldIdEmpresaOfUsuariosCollectionNewUsuarios != null && !oldIdEmpresaOfUsuariosCollectionNewUsuarios.equals(empresas)) {
                        oldIdEmpresaOfUsuariosCollectionNewUsuarios.getUsuariosCollection().remove(usuariosCollectionNewUsuarios);
                        oldIdEmpresaOfUsuariosCollectionNewUsuarios = em.merge(oldIdEmpresaOfUsuariosCollectionNewUsuarios);
                    }
                }
            }
            for (Personas personasCollectionNewPersonas : personasCollectionNew) {
                if (!personasCollectionOld.contains(personasCollectionNewPersonas)) {
                    Empresas oldIdEmpresaOfPersonasCollectionNewPersonas = personasCollectionNewPersonas.getIdEmpresa();
                    personasCollectionNewPersonas.setIdEmpresa(empresas);
                    personasCollectionNewPersonas = em.merge(personasCollectionNewPersonas);
                    if (oldIdEmpresaOfPersonasCollectionNewPersonas != null && !oldIdEmpresaOfPersonasCollectionNewPersonas.equals(empresas)) {
                        oldIdEmpresaOfPersonasCollectionNewPersonas.getPersonasCollection().remove(personasCollectionNewPersonas);
                        oldIdEmpresaOfPersonasCollectionNewPersonas = em.merge(oldIdEmpresaOfPersonasCollectionNewPersonas);
                    }
                }
            }
            for (Vehiculos vehiculosCollectionNewVehiculos : vehiculosCollectionNew) {
                if (!vehiculosCollectionOld.contains(vehiculosCollectionNewVehiculos)) {
                    Empresas oldIdEmpresaOfVehiculosCollectionNewVehiculos = vehiculosCollectionNewVehiculos.getIdEmpresa();
                    vehiculosCollectionNewVehiculos.setIdEmpresa(empresas);
                    vehiculosCollectionNewVehiculos = em.merge(vehiculosCollectionNewVehiculos);
                    if (oldIdEmpresaOfVehiculosCollectionNewVehiculos != null && !oldIdEmpresaOfVehiculosCollectionNewVehiculos.equals(empresas)) {
                        oldIdEmpresaOfVehiculosCollectionNewVehiculos.getVehiculosCollection().remove(vehiculosCollectionNewVehiculos);
                        oldIdEmpresaOfVehiculosCollectionNewVehiculos = em.merge(oldIdEmpresaOfVehiculosCollectionNewVehiculos);
                    }
                }
            }
            for (Geocercas geocercasCollectionNewGeocercas : geocercasCollectionNew) {
                if (!geocercasCollectionOld.contains(geocercasCollectionNewGeocercas)) {
                    Empresas oldIdEmpresaOfGeocercasCollectionNewGeocercas = geocercasCollectionNewGeocercas.getIdEmpresa();
                    geocercasCollectionNewGeocercas.setIdEmpresa(empresas);
                    geocercasCollectionNewGeocercas = em.merge(geocercasCollectionNewGeocercas);
                    if (oldIdEmpresaOfGeocercasCollectionNewGeocercas != null && !oldIdEmpresaOfGeocercasCollectionNewGeocercas.equals(empresas)) {
                        oldIdEmpresaOfGeocercasCollectionNewGeocercas.getGeocercasCollection().remove(geocercasCollectionNewGeocercas);
                        oldIdEmpresaOfGeocercasCollectionNewGeocercas = em.merge(oldIdEmpresaOfGeocercasCollectionNewGeocercas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresas.getIdEmpresa();
                if (findEmpresas(id) == null) {
                    throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.");
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
            Empresas empresas;
            try {
                empresas = em.getReference(Empresas.class, id);
                empresas.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EnvioCorreos> envioCorreosCollectionOrphanCheck = empresas.getEnvioCorreosCollection();
            for (EnvioCorreos envioCorreosCollectionOrphanCheckEnvioCorreos : envioCorreosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the EnvioCorreos " + envioCorreosCollectionOrphanCheckEnvioCorreos + " in its envioCorreosCollection field has a non-nullable empresas field.");
            }
            Collection<Usuarios> usuariosCollectionOrphanCheck = empresas.getUsuariosCollection();
            for (Usuarios usuariosCollectionOrphanCheckUsuarios : usuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Usuarios " + usuariosCollectionOrphanCheckUsuarios + " in its usuariosCollection field has a non-nullable idEmpresa field.");
            }
            Collection<Personas> personasCollectionOrphanCheck = empresas.getPersonasCollection();
            for (Personas personasCollectionOrphanCheckPersonas : personasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Personas " + personasCollectionOrphanCheckPersonas + " in its personasCollection field has a non-nullable idEmpresa field.");
            }
            Collection<Vehiculos> vehiculosCollectionOrphanCheck = empresas.getVehiculosCollection();
            for (Vehiculos vehiculosCollectionOrphanCheckVehiculos : vehiculosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Vehiculos " + vehiculosCollectionOrphanCheckVehiculos + " in its vehiculosCollection field has a non-nullable idEmpresa field.");
            }
            Collection<Geocercas> geocercasCollectionOrphanCheck = empresas.getGeocercasCollection();
            for (Geocercas geocercasCollectionOrphanCheckGeocercas : geocercasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Geocercas " + geocercasCollectionOrphanCheckGeocercas + " in its geocercasCollection field has a non-nullable idEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresas> findEmpresasEntities() {
        return findEmpresasEntities(true, -1, -1);
    }

    public List<Empresas> findEmpresasEntities(int maxResults, int firstResult) {
        return findEmpresasEntities(false, maxResults, firstResult);
    }

    private List<Empresas> findEmpresasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresas.class));
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

    public Empresas findEmpresas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresas> rt = cq.from(Empresas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
