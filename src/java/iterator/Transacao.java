package iterator;

import java.util.ArrayList;
import model.Compra;
import model.Item;
import model.Usuario;
import singleton.Banco;

public class Transacao {
    private Compra compra;
    private ArrayList<Item> items;
    
    public Transacao(Compra compra, ArrayList items) {
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
        for(Item item : items) {
            total += item.getValor();
        }
        return total;
    }
    
    public Iterator createIterator() {
        return new TransacaoIterator(items);
    }
}
