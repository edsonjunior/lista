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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Arcantus
 */
@Stateless
public class ListaDeComprasRepositorio {

    @PersistenceContext
    protected EntityManager entityManager;

    public void addLista(ListaDeCompras lista){
        this.entityManager.persist(lista);
        this.entityManager.flush();
    }
    
     public void updateLista(long editId, String nome, String descricao) {
        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, editId);
        lista.setDescricao(descricao);
        lista.setNome(nome);
        this.entityManager.merge(lista);
        this.entityManager.flush();
    }

    public List<ListaDeCompras> getListaByUser(Usuario user) {
        List<ListaDeCompras> li = null;

        try {
            TypedQuery<ListaDeCompras> query = this.entityManager.createNamedQuery("findListByUser", ListaDeCompras.class);
            query.setParameter("id", user.getId());
            li = query.getResultList();
        } catch (Exception e) {
        }
        return li;
    }

    public void deleteLista(ListaDeCompras lista) {
        ListaDeCompras listaToRemove = this.entityManager.find(ListaDeCompras.class, lista.getId());
        this.entityManager.remove(listaToRemove);
        this.entityManager.flush();
        this.entityManager.clear();
    }
}
