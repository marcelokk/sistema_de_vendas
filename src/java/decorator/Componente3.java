package decorator;

public class Componente3 extends Componentes {

    private Produto produto;
    
    public Componente3(Produto produto) {
        this.produto = produto;
    }
    
    @Override
    public Double custo() {
        return 1.5;
    }
    
    @Override
    public String descricao() {
        return produto.descricao() + " + Componente3";
    }
    
    @Override
    public String getComponentes() {
        return produto.getComponentes() + "3";
    }    
}
