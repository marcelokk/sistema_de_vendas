package prototype;

import decorator.Produto;

public abstract class SugestaoPrototype {

    protected Produto produto;

    public abstract SugestaoPrototype clonar();

    public Produto getProduto() {
        return produto;
    }
}
