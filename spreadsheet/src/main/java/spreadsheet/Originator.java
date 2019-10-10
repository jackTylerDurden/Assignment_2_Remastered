package spreadsheet;
import java.util.*;
public class Originator{
    private LinkedHashMap<String,String> context = new LinkedHashMap<>();

    public void set(LinkedHashMap<String,String> newContext){        
        context = newContext;    
    }

    public Memento storeInMemento(){
        return new Memento(context);
    }

    public LinkedHashMap<String,String> restoreFromMemento(Memento memento){
        LinkedHashMap<String,String> savedContext = memento.getSavedContext();
        return savedContext;
    }
}