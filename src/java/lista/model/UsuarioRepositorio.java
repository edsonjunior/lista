/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.model;

import aula.AuthenticationUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Arcantus
 */
@Stateless
public class UsuarioRepositorio {

    @PersistenceContext
    protected EntityManager entityManager;

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<Usuario>();
    }

    public boolean addUsuario(Usuario usuario) {
        Usuario userSameEmail = getByEmail(usuario.getEmail());

        if (userSameEmail == null) {
            this.entityManager.persist(usuario);
            this.entityManager.flush();
            return true;
        }
        return false;
    }

    public Usuario getByEmailAndPassword(String email, String senha) {
        Usuario usuario = null;

        try {
            TypedQuery<Usuario> query = this.entityManager.createNamedQuery("findUserByEmailAndPassword", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("senha", AuthenticationUtils.encodeSHA256(senha));
            usuario = query.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }

    public Usuario getByEmail(String email) {
        TypedQuery<Usuario> query = this.entityManager.createNamedQuery("findUserByEmail", Usuario.class);
        query.setParameter("email", email);
        Usuario usuario = null;
        try {
            usuario = query.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }
}
