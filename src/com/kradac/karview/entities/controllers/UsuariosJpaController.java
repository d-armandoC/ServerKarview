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
import com.kradac.karview.entities.logic.RolUsuarios;
import com.kradac.karview.entities.logic.Personas;
import com.kradac.karview.entities.logic.Empresas;
import com.kradac.karview.entities.logic.Usuarios;
import com.kradac.karview.entities.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Diego C
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolUsuarios idRolUsuario = usuarios.getIdRolUsuario();
            if (idRolUsuario != null) {
                idRolUsuario = em.getReference(idRolUsuario.getClass(), idRolUsuario.getIdRolUsuario());
                usuarios.setIdRolUsuario(idRolUsuario);
            }
            Personas idPersona = usuarios.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                usuarios.setIdPersona(idPersona);
            }
            Empresas idEmpresa = usuarios.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                usuarios.setIdEmpresa(idEmpresa);
            }
            em.persist(usuarios);
            if (idRolUsuario != null) {
                idRolUsuario.getUsuariosCollection().add(usuarios);
                idRolUsuario = em.merge(idRolUsuario);
            }
            if (idPersona != null) {
                idPersona.getUsuariosCollection().add(usuarios);
                idPersona = em.merge(idPersona);
            }
            if (idEmpresa != null) {
                idEmpresa.getUsuariosCollection().add(usuarios);
                idEmpresa = em.merge(idEmpresa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            RolUsuarios idRolUsuarioOld = persistentUsuarios.getIdRolUsuario();
            RolUsuarios idRolUsuarioNew = usuarios.getIdRolUsuario();
            Personas idPersonaOld = persistentUsuarios.getIdPersona();
            Personas idPersonaNew = usuarios.getIdPersona();
            Empresas idEmpresaOld = persistentUsuarios.getIdEmpresa();
            Empresas idEmpresaNew = usuarios.getIdEmpresa();
            if (idRolUsuarioNew != null) {
                idRolUsuarioNew = em.getReference(idRolUsuarioNew.getClass(), idRolUsuarioNew.getIdRolUsuario());
                usuarios.setIdRolUsuario(idRolUsuarioNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                usuarios.setIdPersona(idPersonaNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                usuarios.setIdEmpresa(idEmpresaNew);
            }
            usuarios = em.merge(usuarios);
            if (idRolUsuarioOld != null && !idRolUsuarioOld.equals(idRolUsuarioNew)) {
                idRolUsuarioOld.getUsuariosCollection().remove(usuarios);
                idRolUsuarioOld = em.merge(idRolUsuarioOld);
            }
            if (idRolUsuarioNew != null && !idRolUsuarioNew.equals(idRolUsuarioOld)) {
                idRolUsuarioNew.getUsuariosCollection().add(usuarios);
                idRolUsuarioNew = em.merge(idRolUsuarioNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getUsuariosCollection().remove(usuarios);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getUsuariosCollection().add(usuarios);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getUsuariosCollection().remove(usuarios);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getUsuariosCollection().add(usuarios);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            RolUsuarios idRolUsuario = usuarios.getIdRolUsuario();
            if (idRolUsuario != null) {
                idRolUsuario.getUsuariosCollection().remove(usuarios);
                idRolUsuario = em.merge(idRolUsuario);
            }
            Personas idPersona = usuarios.getIdPersona();
            if (idPersona != null) {
                idPersona.getUsuariosCollection().remove(usuarios);
                idPersona = em.merge(idPersona);
            }
            Empresas idEmpresa = usuarios.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getUsuariosCollection().remove(usuarios);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
