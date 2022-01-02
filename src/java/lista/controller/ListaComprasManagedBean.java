package lista.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lista.controller.SessionSessionBean;
import lista.model.ListaDeCompras;
import lista.model.Usuario;

@ManagedBean(name = "listaComprasManagedBean")
@ViewScoped
public class ListaComprasManagedBean implements Serializable {

    private static final long serialVersionUID = -2688161306000143630L;
    @EJB
    private ListaComprasBean cListaBean;

    long idEdit = 0;
    String nome;
    String descricao;
   

    public void criarLista(Usuario currentUser) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            cListaBean.addLista(nome, descricao, currentUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/lista/dashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editLista() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            
            cListaBean.editLista(idEdit, nome, descricao);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/lista/dashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleSubmit(Usuario currentUser) {
        if(idEdit != 0) {
            editLista();
        } else {
            criarLista(currentUser);
        }
    }
    
    public List<ListaDeCompras> getListasDeCompras(Usuario user) {

        return cListaBean.getListaByUser(user);
    }
    
    public void deleteLista(ListaDeCompras lista) {
        cListaBean.deleteLista(lista);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(long idEdit) {
        this.idEdit = idEdit;
    }

    public void setEditConfig(long idEdit, String nome, String descricao) {
        setIdEdit(idEdit);
        setNome(nome);
        setDescricao(descricao);
        
    }

    
}