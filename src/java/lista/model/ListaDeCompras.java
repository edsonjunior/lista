/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Arcantus
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findListByUser", query = "select l from ListaDeCompras l join l.usuarios u where u.id = :id"),
    @NamedQuery(name = "findListPrivateByUser", query = "select l from ListaDeCompras l join l.usuarios u where u.id = :id and l.ispublic LIKE 0"),
    @NamedQuery(name = "findListSharedByUser", query = "select l from ListaDeCompras l join l.usuarios u where u.id = :id and l.ispublic LIKE 1"),
    @NamedQuery(name = "findListSharedByUserGeneral", query = "select l from ListaDeCompras l where l.ispublic LIKE 1")
})
@Table(name = "listaDeCompras")
public class ListaDeCompras {
 
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "ispublic", nullable = false)
    private Boolean ispublic;
    
    @ElementCollection
    @CollectionTable(name = "itemsLista")
    private List<String> items;
    
    @ManyToMany
    @JoinTable(
            name = "lista_usuario",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> usuarios = new HashSet<Usuario>();
    
    public ListaDeCompras() {
    }
    
    public ListaDeCompras(String nome, String descricao, Boolean ispublic) {
        this.nome = nome;
        this.descricao = descricao;
        this.ispublic = ispublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }
}