package decorator;

public class Base extends Produto {
    
    @Override
    public Double custo() {
        return this.custo;
    }
    
    @Override
    public String descricao() {
        return this.descricao;
    }
    
    @Override
    public String getComponentes() {
        return String.valueOf(this.id);
    }

    @Override
    public void setCusto(Double valor) {
        this.custo = valor;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
