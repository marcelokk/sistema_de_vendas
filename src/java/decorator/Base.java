package decorator;

public class Base extends Produto {
    
    @Override
    public Double custo() {
        return 0.5;
    }
    
    @Override
    public String descricao() {
        return "base";
    }
    
    @Override
    public String getComponentes() {
        return "0";
    }
}
