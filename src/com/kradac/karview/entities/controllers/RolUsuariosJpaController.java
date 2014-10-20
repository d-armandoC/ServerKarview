/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kradac.karview.entities.controllers;

import com.kradac.karview.entities.controllers.exceptions.IllegalOrphanException;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import com.kradac.karview.entities.logic.RolUsuarios;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kradac.karview.entities.logic.Usuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class RolUsuariosJpaController implements Serializable {

    public RolUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolUsuarios rolUsuarios) {
        if (rolUsuarios.getUsuariosCollection() == null) {
            rolUsuarios.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : rolUsuarios.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            rolUsuarios.setUsuariosCollection(attachedUsuariosCollection);
            em.persist(rolUsuarios);
            for (Usuarios usuariosCollectionUsuarios : rolUsuarios.getUsuariosCollection()) {
                RolUsuarios oldIdRolUsuarioOfUsuariosCollectionUsuarios = usuariosCollectionUsuarios.getIdRolUsuario();
                usuariosCollectionUsuarios.setIdRolUsuario(rolUsuarios);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
                if (oldIdRolUsuarioOfUsuariosCollectionUsuarios != null) {
                    oldIdRolUsuarioOfUsuariosCollectionUsuarios.getUsuariosCollection().remove(usuariosCollectionUsuarios);
                    oldIdRolUsuarioOfUsuariosCollectionUsuarios = em.merge(oldIdRolUsuarioOfUsuariosCollectionUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolUsuarios rolUsuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolUsuarios persistentRolUsuarios = em.find(RolUsuarios.class, rolUsuarios.getIdRolUsuario());
            Collection<Usuarios> usuariosCollectionOld = persistentRolUsuarios.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = rolUsuarios.getUsuariosCollection();
            List<String> illegalOrphanMessages = null;
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosCollectionOldUsuarios + " since its idRolUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            rolUsuarios.setUsuariosCollection(usuariosCollectionNew);
            rolUsuarios = em.merge(rolUsuarios);
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    RolUsuarios oldIdRolUsuarioOfUsuariosCollectionNewUsuarios = usuariosCollectionNewUsuarios.getIdRolUsuario();
                    usuariosCollectionNewUsuarios.setIdRolUsuario(rolUsuarios);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
                    if (oldIdRolUsuarioOfUsuariosCollectionNewUsuarios != null && !oldIdRolUsuarioOfUsuariosCollectionNewUsuarios.equals(rolUsuarios)) {
                        oldIdRolUsuarioOfUsuariosCollectionNewUsuarios.getUsuariosCollection().remove(usuariosCollectionNewUsuarios);
                        oldIdRolUsuarioOfUsuariosCollectionNewUsuarios = em.merge(oldIdRolUsuarioOfUsuariosCollectionNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolUsuarios.getIdRolUsuario();
                if (findRolUsuarios(id) == null) {
                    throw new NonexistentEntityException("The rolUsuarios with id " + id + " no longer exists.");
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
            RolUsuarios rolUsuarios;
            try {
                rolUsuarios = em.getReference(RolUsuarios.class, id);
                rolUsuarios.getIdRolUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolUsuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usuarios> usuariosCollectionOrphanCheck = rolUsuarios.getUsuariosCollection();
            for (Usuarios usuariosCollectionOrphanCheckUsuarios : usuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RolUsuarios (" + rolUsuarios + ") cannot be destroyed since the Usuarios " + usuariosCollectionOrphanCheckUsuarios + " in its usuariosCollection field has a non-nullable idRolUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rolUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolUsuarios> findRolUsuariosEntities() {
        return findRolUsuariosEntities(true, -1, -1);
    }

    public List<RolUsuarios> findRolUsuariosEntities(int maxResults, int firstResult) {
        return findRolUsuariosEntities(false, maxResults, firstResult);
    }

    private List<RolUsuarios> findRolUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolUsuarios.class));
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

    public RolUsuarios findRolUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolUsuarios> rt = cq.from(RolUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
