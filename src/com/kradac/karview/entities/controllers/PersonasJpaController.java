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
import com.kradac.karview.entities.logic.Generos;
import com.kradac.karview.entities.logic.EnvioCorreos;
import com.kradac.karview.entities.logic.Personas;
import java.util.ArrayList;
import java.util.Collection;
import com.kradac.karview.entities.logic.Usuarios;
import com.kradac.karview.entities.logic.Vehiculos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class PersonasJpaController implements Serializable {

    public PersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personas personas) {
        if (personas.getEnvioCorreosCollection() == null) {
            personas.setEnvioCorreosCollection(new ArrayList<EnvioCorreos>());
        }
        if (personas.getUsuariosCollection() == null) {
            personas.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        if (personas.getVehiculosCollection() == null) {
            personas.setVehiculosCollection(new ArrayList<Vehiculos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = personas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                personas.setIdEmpresa(idEmpresa);
            }
            Generos idGenero = personas.getIdGenero();
            if (idGenero != null) {
                idGenero = em.getReference(idGenero.getClass(), idGenero.getIdGenero());
                personas.setIdGenero(idGenero);
            }
            Collection<EnvioCorreos> attachedEnvioCorreosCollection = new ArrayList<EnvioCorreos>();
            for (EnvioCorreos envioCorreosCollectionEnvioCorreosToAttach : personas.getEnvioCorreosCollection()) {
                envioCorreosCollectionEnvioCorreosToAttach = em.getReference(envioCorreosCollectionEnvioCorreosToAttach.getClass(), envioCorreosCollectionEnvioCorreosToAttach.getEnvioCorreosPK());
                attachedEnvioCorreosCollection.add(envioCorreosCollectionEnvioCorreosToAttach);
            }
            personas.setEnvioCorreosCollection(attachedEnvioCorreosCollection);
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : personas.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            personas.setUsuariosCollection(attachedUsuariosCollection);
            Collection<Vehiculos> attachedVehiculosCollection = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionVehiculosToAttach : personas.getVehiculosCollection()) {
                vehiculosCollectionVehiculosToAttach = em.getReference(vehiculosCollectionVehiculosToAttach.getClass(), vehiculosCollectionVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollection.add(vehiculosCollectionVehiculosToAttach);
            }
            personas.setVehiculosCollection(attachedVehiculosCollection);
            em.persist(personas);
            if (idEmpresa != null) {
                idEmpresa.getPersonasCollection().add(personas);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idGenero != null) {
                idGenero.getPersonasCollection().add(personas);
                idGenero = em.merge(idGenero);
            }
            for (EnvioCorreos envioCorreosCollectionEnvioCorreos : personas.getEnvioCorreosCollection()) {
                Personas oldPersonasOfEnvioCorreosCollectionEnvioCorreos = envioCorreosCollectionEnvioCorreos.getPersonas();
                envioCorreosCollectionEnvioCorreos.setPersonas(personas);
                envioCorreosCollectionEnvioCorreos = em.merge(envioCorreosCollectionEnvioCorreos);
                if (oldPersonasOfEnvioCorreosCollectionEnvioCorreos != null) {
                    oldPersonasOfEnvioCorreosCollectionEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionEnvioCorreos);
                    oldPersonasOfEnvioCorreosCollectionEnvioCorreos = em.merge(oldPersonasOfEnvioCorreosCollectionEnvioCorreos);
                }
            }
            for (Usuarios usuariosCollectionUsuarios : personas.getUsuariosCollection()) {
                Personas oldIdPersonaOfUsuariosCollectionUsuarios = usuariosCollectionUsuarios.getIdPersona();
                usuariosCollectionUsuarios.setIdPersona(personas);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
                if (oldIdPersonaOfUsuariosCollectionUsuarios != null) {
                    oldIdPersonaOfUsuariosCollectionUsuarios.getUsuariosCollection().remove(usuariosCollectionUsuarios);
                    oldIdPersonaOfUsuariosCollectionUsuarios = em.merge(oldIdPersonaOfUsuariosCollectionUsuarios);
                }
            }
            for (Vehiculos vehiculosCollectionVehiculos : personas.getVehiculosCollection()) {
                Personas oldIdPersonaOfVehiculosCollectionVehiculos = vehiculosCollectionVehiculos.getIdPersona();
                vehiculosCollectionVehiculos.setIdPersona(personas);
                vehiculosCollectionVehiculos = em.merge(vehiculosCollectionVehiculos);
                if (oldIdPersonaOfVehiculosCollectionVehiculos != null) {
                    oldIdPersonaOfVehiculosCollectionVehiculos.getVehiculosCollection().remove(vehiculosCollectionVehiculos);
                    oldIdPersonaOfVehiculosCollectionVehiculos = em.merge(oldIdPersonaOfVehiculosCollectionVehiculos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personas personas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persistentPersonas = em.find(Personas.class, personas.getIdPersona());
            Empresas idEmpresaOld = persistentPersonas.getIdEmpresa();
            Empresas idEmpresaNew = personas.getIdEmpresa();
            Generos idGeneroOld = persistentPersonas.getIdGenero();
            Generos idGeneroNew = personas.getIdGenero();
            Collection<EnvioCorreos> envioCorreosCollectionOld = persistentPersonas.getEnvioCorreosCollection();
            Collection<EnvioCorreos> envioCorreosCollectionNew = personas.getEnvioCorreosCollection();
            Collection<Usuarios> usuariosCollectionOld = persistentPersonas.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = personas.getUsuariosCollection();
            Collection<Vehiculos> vehiculosCollectionOld = persistentPersonas.getVehiculosCollection();
            Collection<Vehiculos> vehiculosCollectionNew = personas.getVehiculosCollection();
            List<String> illegalOrphanMessages = null;
            for (EnvioCorreos envioCorreosCollectionOldEnvioCorreos : envioCorreosCollectionOld) {
                if (!envioCorreosCollectionNew.contains(envioCorreosCollectionOldEnvioCorreos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnvioCorreos " + envioCorreosCollectionOldEnvioCorreos + " since its personas field is not nullable.");
                }
            }
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosCollectionOldUsuarios + " since its idPersona field is not nullable.");
                }
            }
            for (Vehiculos vehiculosCollectionOldVehiculos : vehiculosCollectionOld) {
                if (!vehiculosCollectionNew.contains(vehiculosCollectionOldVehiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehiculos " + vehiculosCollectionOldVehiculos + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                personas.setIdEmpresa(idEmpresaNew);
            }
            if (idGeneroNew != null) {
                idGeneroNew = em.getReference(idGeneroNew.getClass(), idGeneroNew.getIdGenero());
                personas.setIdGenero(idGeneroNew);
            }
            Collection<EnvioCorreos> attachedEnvioCorreosCollectionNew = new ArrayList<EnvioCorreos>();
            for (EnvioCorreos envioCorreosCollectionNewEnvioCorreosToAttach : envioCorreosCollectionNew) {
                envioCorreosCollectionNewEnvioCorreosToAttach = em.getReference(envioCorreosCollectionNewEnvioCorreosToAttach.getClass(), envioCorreosCollectionNewEnvioCorreosToAttach.getEnvioCorreosPK());
                attachedEnvioCorreosCollectionNew.add(envioCorreosCollectionNewEnvioCorreosToAttach);
            }
            envioCorreosCollectionNew = attachedEnvioCorreosCollectionNew;
            personas.setEnvioCorreosCollection(envioCorreosCollectionNew);
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            personas.setUsuariosCollection(usuariosCollectionNew);
            Collection<Vehiculos> attachedVehiculosCollectionNew = new ArrayList<Vehiculos>();
            for (Vehiculos vehiculosCollectionNewVehiculosToAttach : vehiculosCollectionNew) {
                vehiculosCollectionNewVehiculosToAttach = em.getReference(vehiculosCollectionNewVehiculosToAttach.getClass(), vehiculosCollectionNewVehiculosToAttach.getIdVehiculo());
                attachedVehiculosCollectionNew.add(vehiculosCollectionNewVehiculosToAttach);
            }
            vehiculosCollectionNew = attachedVehiculosCollectionNew;
            personas.setVehiculosCollection(vehiculosCollectionNew);
            personas = em.merge(personas);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPersonasCollection().remove(personas);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPersonasCollection().add(personas);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idGeneroOld != null && !idGeneroOld.equals(idGeneroNew)) {
                idGeneroOld.getPersonasCollection().remove(personas);
                idGeneroOld = em.merge(idGeneroOld);
            }
            if (idGeneroNew != null && !idGeneroNew.equals(idGeneroOld)) {
                idGeneroNew.getPersonasCollection().add(personas);
                idGeneroNew = em.merge(idGeneroNew);
            }
            for (EnvioCorreos envioCorreosCollectionNewEnvioCorreos : envioCorreosCollectionNew) {
                if (!envioCorreosCollectionOld.contains(envioCorreosCollectionNewEnvioCorreos)) {
                    Personas oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos = envioCorreosCollectionNewEnvioCorreos.getPersonas();
                    envioCorreosCollectionNewEnvioCorreos.setPersonas(personas);
                    envioCorreosCollectionNewEnvioCorreos = em.merge(envioCorreosCollectionNewEnvioCorreos);
                    if (oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos != null && !oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos.equals(personas)) {
                        oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos.getEnvioCorreosCollection().remove(envioCorreosCollectionNewEnvioCorreos);
                        oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos = em.merge(oldPersonasOfEnvioCorreosCollectionNewEnvioCorreos);
                    }
                }
            }
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    Personas oldIdPersonaOfUsuariosCollectionNewUsuarios = usuariosCollectionNewUsuarios.getIdPersona();
                    usuariosCollectionNewUsuarios.setIdPersona(personas);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
                    if (oldIdPersonaOfUsuariosCollectionNewUsuarios != null && !oldIdPersonaOfUsuariosCollectionNewUsuarios.equals(personas)) {
                        oldIdPersonaOfUsuariosCollectionNewUsuarios.getUsuariosCollection().remove(usuariosCollectionNewUsuarios);
                        oldIdPersonaOfUsuariosCollectionNewUsuarios = em.merge(oldIdPersonaOfUsuariosCollectionNewUsuarios);
                    }
                }
            }
            for (Vehiculos vehiculosCollectionNewVehiculos : vehiculosCollectionNew) {
                if (!vehiculosCollectionOld.contains(vehiculosCollectionNewVehiculos)) {
                    Personas oldIdPersonaOfVehiculosCollectionNewVehiculos = vehiculosCollectionNewVehiculos.getIdPersona();
                    vehiculosCollectionNewVehiculos.setIdPersona(personas);
                    vehiculosCollectionNewVehiculos = em.merge(vehiculosCollectionNewVehiculos);
                    if (oldIdPersonaOfVehiculosCollectionNewVehiculos != null && !oldIdPersonaOfVehiculosCollectionNewVehiculos.equals(personas)) {
                        oldIdPersonaOfVehiculosCollectionNewVehiculos.getVehiculosCollection().remove(vehiculosCollectionNewVehiculos);
                        oldIdPersonaOfVehiculosCollectionNewVehiculos = em.merge(oldIdPersonaOfVehiculosCollectionNewVehiculos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personas.getIdPersona();
                if (findPersonas(id) == null) {
                    throw new NonexistentEntityException("The personas with id " + id + " no longer exists.");
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
            Personas personas;
            try {
                personas = em.getReference(Personas.class, id);
                personas.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EnvioCorreos> envioCorreosCollectionOrphanCheck = personas.getEnvioCorreosCollection();
            for (EnvioCorreos envioCorreosCollectionOrphanCheckEnvioCorreos : envioCorreosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the EnvioCorreos " + envioCorreosCollectionOrphanCheckEnvioCorreos + " in its envioCorreosCollection field has a non-nullable personas field.");
            }
            Collection<Usuarios> usuariosCollectionOrphanCheck = personas.getUsuariosCollection();
            for (Usuarios usuariosCollectionOrphanCheckUsuarios : usuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Usuarios " + usuariosCollectionOrphanCheckUsuarios + " in its usuariosCollection field has a non-nullable idPersona field.");
            }
            Collection<Vehiculos> vehiculosCollectionOrphanCheck = personas.getVehiculosCollection();
            for (Vehiculos vehiculosCollectionOrphanCheckVehiculos : vehiculosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Vehiculos " + vehiculosCollectionOrphanCheckVehiculos + " in its vehiculosCollection field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresas idEmpresa = personas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPersonasCollection().remove(personas);
                idEmpresa = em.merge(idEmpresa);
            }
            Generos idGenero = personas.getIdGenero();
            if (idGenero != null) {
                idGenero.getPersonasCollection().remove(personas);
                idGenero = em.merge(idGenero);
            }
            em.remove(personas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personas> findPersonasEntities() {
        return findPersonasEntities(true, -1, -1);
    }

    public List<Personas> findPersonasEntities(int maxResults, int firstResult) {
        return findPersonasEntities(false, maxResults, firstResult);
    }

    private List<Personas> findPersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personas.class));
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

    public Personas findPersonas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personas> rt = cq.from(Personas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
