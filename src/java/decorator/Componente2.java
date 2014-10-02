package decorator;

public class Componente2 extends Componentes {
    
    private Produto produto;
    
    public Componente2(Produto produto) {
        this.produto = produto;
    }

    @Override
    public Double custo() {
        return 1.0;
    }
    
    @Override
    public String descricao() {
        return produto.descricao() + " + Componente2";
    }    
    
    @Override
    public String getComponentes() {
        return produto.getComponentes() + "2";
    }    
}
