package lista.controller;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import lista.model.Usuario;
import lista.model.UsuarioRepositorio;

@Stateless
public class SessionSessionBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    private UsuarioRepositorio usuarioRepositorio;
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarioRepositorio.getUsuarios();
    }
    
    public Usuario addUsuario(String nome, String senha, String email) {
        try {
            Usuario usuario = new Usuario(nome, senha, email);
            
            boolean created = usuarioRepositorio.addUsuario(usuario);;
            if(created) {
                return usuario;   
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Usuario updateUsuario(Usuario usuario) {
        try {
            
            boolean updated = usuarioRepositorio.updateUsuario(usuario);;
            if(updated) {
                return usuario;   
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Usuario loginUsuario(String email, String senha) {
        Usuario usuario = usuarioRepositorio.getByEmailAndPassword(email, senha);
        return usuario;
    }

}