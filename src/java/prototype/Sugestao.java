package prototype;

import decorator.Base;
import decorator.Componente1;
import decorator.Componente2;
import decorator.Componente3;
import decorator.Produto;

public class Sugestao extends SugestaoPrototype {

    public Sugestao(Produto produto) {
        this.produto = produto;
    }

    protected Sugestao(Sugestao sugestao) {
        String s = produto.getComponentes();

        Produto p = null;

        if (s.charAt(s.length() - 1) == '0') {
            p = new Base();
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                p = new Componente1(p);
            } else if (s.charAt(i) == '2') {
                p = new Componente2(p);
            } else if (s.charAt(i) == '3') {
                p = new Componente3(p);
            }
        }
        this.produto = p;
    }

    @Override
    public SugestaoPrototype clonar() {
        return new Sugestao(this);
    }
}
