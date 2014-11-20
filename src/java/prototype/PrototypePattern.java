package prototype;

import decorator.Produto;

public abstract class PrototypePattern {

    protected Produto produto;

    public abstract PrototypePattern clonar();
    
    public Produto getProduto() {
        return produto;
    }
}
