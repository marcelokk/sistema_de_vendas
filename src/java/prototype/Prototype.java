package prototype;

import decorator.Base;
import decorator.Componente1;
import decorator.Produto;
import model.Acai;
import model.Componente;
import model.Sugestao;
import singleton.Banco;

public class Prototype extends PrototypePattern {

    public Prototype(Produto produto) {
        this.produto = produto;
    }

    public Prototype(Sugestao sugestao) {
        
        String[] retval = sugestao.getDescricao().split("+");
        for(String s : retval) {
            System.out.println("-> " + s);
        }
        
        Produto b = new Base();
        Acai a = Banco.getInstantance().getAcai(retval[0]);
        
        b.setCusto(a.getValor());
        b.setDescricao(a.getDescricao());
        b.setId(a.getId());
        
        for(int i = 1; i < retval.length; i++) {
            Componente c = Banco.getInstantance().getComponente(retval[i]);
            
            b = new Componente1(b);
            b.setCusto(c.getValor());
            b.setDescricao(c.getDescricao());
            b.setId(c.getId());
        }
        
        this.produto = b;
    }
    
    protected Prototype(Prototype prototype) {
        String s = produto.getComponentes();

        Produto p = null;
        
        if (s.charAt(s.length() - 1) == '0') {
            p = new Base();
        }
/*
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                p = new Componente1(p);
            } else if (s.charAt(i) == '2') {
                p = new Componente2(p);
            } else if (s.charAt(i) == '3') {
                p = new Componente3(p);
            }
        }
        */ 
        this.produto = p;
    }

    @Override
    public PrototypePattern clonar() {
        return new Prototype(this);
    }
}
