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

        String[] retval = sugestao.getDescricao().split("\\+");
        for (String s : retval) {
            System.out.println("-> " + s);
        }

        Produto b = new Base();
        Acai a = Banco.getInstantance().getAcai(retval[0]);

        b.setCusto(a.getValor());
        b.setDescricao(a.getDescricao());
        b.setId(a.getId());

        for (int i = 1; i < retval.length; i++) {
            Componente c = Banco.getInstantance().getComponente(retval[i]);

            b = new Componente1(b);
            b.setCusto(c.getValor());
            b.setDescricao(c.getDescricao());
            b.setId(c.getId());
        }

        this.produto = b;
    }

    protected Prototype(Prototype prototype) {
        Produto p = prototype.getProduto();

        String s = p.getComponentes();

        Produto b = new Base();
        Acai a = Banco.getInstantance().getAcai(s.charAt(0) - '0');

        if(a == null) {
            System.out.println("Essa funcao esta NULL");
        }
        System.out.println("valor " + a.getValor());
        System.out.println("descricao " + a.getDescricao());
        b.setCusto(a.getValor());        
        b.setDescricao(a.getDescricao());
        b.setId(a.getId());

        for (int i = 1; i < s.length(); i++) {
            Componente c = Banco.getInstantance().getComponente(s.charAt(i) - '0');

            b = new Componente1(b);
            b.setCusto(c.getValor());
            b.setDescricao(c.getDescricao());
            b.setId(c.getId());
        }
        
        this.produto = b;
    }

    @Override
    public PrototypePattern clonar() {
        return new Prototype(this);
    }
}
