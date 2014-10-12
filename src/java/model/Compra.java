package model;

import java.util.HashSet;
import java.util.Set;

public class Compra {
    private int id;
    private int usuario;
    private String data_da_compra;
    private Set itens = new HashSet();

    public Set getItens() {
        return itens;
    }

    public void setItens(Set itens) {
        this.itens = itens;
    }
    
    public Compra() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getData_da_compra() {
        return data_da_compra;
    }

    public void setData_da_compra(String data_de_compra) {
        this.data_da_compra = data_de_compra;
    }
    
    
}
