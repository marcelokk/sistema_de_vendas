package model;

import java.util.HashSet;
import java.util.Set;

public class Sugestao {
    private int id;
    private String nome;
    private String descricao;
    private Double valor;
    
    //private Set itens = new HashSet();
    //private static int nextId = 0;
/*
    public Set getItens() {
        return itens;
    }

    public void setItens(Set itens) {
        this.itens = itens;
    }
    
    public Sugestao() {
        id = nextId;
        nextId++;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  */  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
