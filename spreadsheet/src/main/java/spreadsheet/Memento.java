package spreadsheet;
import java.util.*;
public class Memento{
    private LinkedHashMap<String,String> context = new LinkedHashMap<String,String>();

    public Memento(LinkedHashMap<String,String> savedContext){
        context = savedContext;
    }

    public LinkedHashMap<String,String> getSavedContext(){
        return context;
    }
}