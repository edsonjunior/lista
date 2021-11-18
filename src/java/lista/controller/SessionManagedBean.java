package lista.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lista.controller.SessionSessionBean;
import lista.model.Usuario;

@Named("sessionManagedBean")
@SessionScoped
public class SessionManagedBean implements Serializable {

    private static final long serialVersionUID = -2688161306000143630L;
    @EJB
    private SessionSessionBean cSessionBean;

    String nome;
    String email;
    String senha;
    Usuario currentUser;
    boolean isLoggedIn = false;
    boolean wrongLogin = false;
    boolean emailBeingUsed = false;

    public void cadastro() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            currentUser = cSessionBean.addUsuario(nome, senha, email);
            if (currentUser != null) {
                emailBeingUsed = false;
                wrongLogin = false;
                isLoggedIn = true;
                try {
                    request.login(email, senha);
                } catch (ServletException ex) {
                    Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                sessionMap.put("User", currentUser);

                FacesContext.getCurrentInstance().getExternalContext().redirect("/lista/dashboard.xhtml");
            } else {
                emailBeingUsed = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        currentUser = cSessionBean.loginUsuario(email, senha);
        if (currentUser != null) {
            wrongLogin = false;
            isLoggedIn = true;
            try {
                try {
                    request.login(email, senha);
                } catch (ServletException ex) {
                    Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                Principal principal = request.getUserPrincipal();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                sessionMap.put("User", currentUser);

                FacesContext.getCurrentInstance().getExternalContext().redirect("/lista/dashboard.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            wrongLogin = true;
        }
    }
    
    public void alterarCadastro(String email, String nome, String senha, String confirmacaoSenha){
        
        if(!senha.equals(confirmacaoSenha)){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senhas não são iguais!","Senhas não são iguais!"));
        }else{
            //SETAR NOVOS DADOS
        }
        return;
        
        
    }

    public String getEmail() {
        return email;
    }

    public void logout() {
        isLoggedIn = false;
        currentUser = null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isWrongLogin() {
        return wrongLogin;
    }

    public void setWrongLogin(boolean wrongLogin) {
        this.wrongLogin = wrongLogin;
    }

    public boolean isEmailBeingUsed() {
        return emailBeingUsed;
    }

    public void setEmailBeingUsed(boolean emailBeingUsed) {
        this.emailBeingUsed = emailBeingUsed;
    }

    
}
