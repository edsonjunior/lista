/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import lista.model.ListaDeCompras;
import lista.model.ListaDeComprasRepositorio;
import lista.model.Usuario;

/**
 *
 * @author Arcantus
 */
@Stateless
public class ListaComprasBean  implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private ListaDeComprasRepositorio listaRepositorio;
    
    public ListaDeCompras addLista(String nome, String descricao, Usuario usuarioLista) {
        ListaDeCompras lista = new ListaDeCompras(nome, descricao);
        lista.getUsuarios().add(usuarioLista);
        
        listaRepositorio.addLista(lista);
        
        return lista;
    }
    
    public void editLista(long editId, String nome, String descricao) {
        listaRepositorio.updateLista(editId, nome, descricao);
        
    }
    
    public List<ListaDeCompras> getListaByUser(Usuario user) {
        return listaRepositorio.getListaByUser(user);
    }
    
    public void deleteLista(ListaDeCompras lista) {
        this.listaRepositorio.deleteLista(lista);
    }
}
