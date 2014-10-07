package iterator;

import java.util.ArrayList;
import model.Item;

public class TransacaoIterator implements Iterator {
    private ArrayList<Item> items;
    int position = 0;
    
    public TransacaoIterator(ArrayList items) {
        this.items = items;
    }
    
    public Object next() {
        Item i = items.get(position);
        position++;
        return i;
    }
    
    public boolean hasNext() {
        if(position >= items.size() || items.get(position) == null) {
            return false;
        } else {
            return true;
        }
    }
}
