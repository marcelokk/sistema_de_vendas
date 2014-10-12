package iterator;

import java.util.Set;
import model.Compra;
import model.Item;
import model.Usuario;
import singleton.Banco;

public class Transacao {

    private Compra compra;
    private Set items;

    public Transacao(Compra compra, Set items) {
        this.compra = compra;
        this.items = items;
    }

    public Usuario getUsuario() {
        return Banco.getInstantance().getUsuario(compra.getUsuario());
    }

    public String getData() {
        return compra.getData_da_compra();
    }

    public double getValor() {
        double total = 0.0;
        Iterator it = this.createIterator();
        while (it.hasNext()) {
            Item i = it.next();
            total += i.getValor();
        }
        return total;
    }

    public Iterator createIterator() {
        return new TransacaoIterator(items);
    }
}
