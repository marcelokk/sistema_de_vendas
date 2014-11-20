package model;

public class Item {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int sugestao_id;

    public int getSugestao_id() {
        return sugestao_id;
    }

    public void setSugestao_id(int sugestao_id) {
        this.sugestao_id = sugestao_id;
    }
    
    public Item() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
