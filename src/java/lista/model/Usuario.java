/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.model;

import aula.AuthenticationUtils;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Arcantus
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findUserByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "findUserByEmailAndPassword", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
})
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -5892169641074303723L;

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public Usuario() {
    }

    public Usuario(String nome, String senha, String email) {
        try {
            this.nome = nome;
            this.senha = AuthenticationUtils.encodeSHA256(senha);
            this.email = email;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            this.senha = AuthenticationUtils.encodeSHA256(senha);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

}
