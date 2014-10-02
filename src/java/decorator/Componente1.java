package decorator;

public class Componente1 extends Componentes {
    
    private Produto produto;
    
    public Componente1(Produto produto) {
        this.produto = produto;
    }
    
    @Override
    public Double custo() {
        return 2.0;
    }
    
    @Override
    public String descricao() {
        return produto.descricao() + " + Componente1";
    }
    
    @Override
    public String getComponentes() {
        return produto.getComponentes() + "1";
    }    
}
