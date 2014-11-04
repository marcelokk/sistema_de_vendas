package model;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private int id;
    private String login;
    private String password;
    private int administrador;
    private String nome;
    private String cidade;
    private String estado;
    private String telefone;
    private Set compras = new HashSet();
    private String nascimento;

    public Set getCompras() {
        return compras;
    }

    public void setCompras(Set compras) {
        this.compras = compras;
    }

    public Usuario(String login, String password, int administrador, String nome, String cidade, String estado, String telefone) {
        this.login = login;
        this.password = password;
        this.administrador = administrador;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Usuario() {
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    
}
