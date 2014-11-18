package decorator;

public class Componente1 extends Componentes {
    
    private Produto produto;
    
    public Componente1(Produto produto) {
        this.produto = produto;
    }
    
    @Override
    public Double custo() {
        return this.custo + produto.custo();
    }
    
    @Override
    public String descricao() {
        return produto.descricao() + " + " + this.descricao;
    }
    
    @Override
    public String getComponentes() {
        return produto.getComponentes() + this.id;
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
