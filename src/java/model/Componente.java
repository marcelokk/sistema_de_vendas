package model;

public class Componente {
    private String nome;
    private int id;
    private int quantidade;
    private double valor;
    private int status;
    
    public Componente() {   
    }
    
    public Componente(int id, String nome, int quantidade, double valor, int status) {
        this.nome = nome;
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.status = status;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
