/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
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