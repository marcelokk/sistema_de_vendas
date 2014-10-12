package iterator;

import java.util.Set;
import model.Item;

public class TransacaoIterator implements Iterator {
    private Item[] items;
    int position = 0;
    
    public TransacaoIterator(Set items) {
        this.items = (Item[]) items.toArray();
    }
    
    public Item next() {
        Item i = items[position];
        position++;
        return i;
    }
    
    public boolean hasNext() {
        if(position >= items.length || items[position] == null) {
            return false;
        } else {
            return true;
        }
    }
}
