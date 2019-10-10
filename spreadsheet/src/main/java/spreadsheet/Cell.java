package spreadsheet;
import java.util.*;
public class Cell implements Subject,Observer{
    String value="";
    int colIndex;
    Boolean isObserver;
    HashSet<Cell> observerList;

    public Cell(String val){
        value = val;
        observerList = new HashSet<Cell>();
        isObserver = false;
    }    
    public void notifyObserver(){        
        for(Cell observer : observerList){            
            observer.update();
        }
    }
    
    public void register(Cell observer){
        observerList.add(observer);        
    }
    
    public void update(){
        value = Driver.client.con.colIndexToExpressionMap.get(String.valueOf(colIndex));        
        Driver.client.con.dependentCellsSet = new HashSet<String>();
        value = getReferredCell();        
        evaluate();
    }

    public String getCellValue(){
        return value;
    }

    public void setCellValue(String cellVal,int col){
        value = cellVal;
        colIndex = col;                 
        if(Driver.client.isValueView){
            if(!Driver.client.isToggled){
                notifyObserver();
            }            
        }else{            
            Driver.client.con.dependentCellsSet = new HashSet<String>();
            this.observerList = new HashSet<Cell>();
        }        
    }    
    
    public void evaluate(){
        value =  PostfixEvaluator.evaluateExpression(value);        
        Driver.client.dataModel.setValueAt(value, 0, colIndex);
    }
    public String getReferredCell(){         
        List<String> resultList = new ArrayList<String>();                
        if(value != null && !value.isBlank()){                        
            if(value.matches(Expression.NUMBER_REGEX)){                
                Driver.client.con.dependentCellsSet.remove(String.valueOf(this.colIndex));                
                resultList.add(value);                
                return String.join(" ", resultList);
            }            
            if(Driver.client.con.dependentCellsSet.contains(String.valueOf(this.colIndex))){
                return Expression.ERROR_MESSAGE; //Circular Dependency
            }else{
                Driver.client.con.dependentCellsSet.add(String.valueOf(this.colIndex));
            }
            for(String term : value.split(" ")){
                term = term.toLowerCase().trim();
                if(term.matches("[a-z]")){
                    int col = (int) term.toCharArray()[0];                                        
                    col = col - Expression.FIRST_ALPHABET_ASCII;                    
                    Cell referredCell = Driver.client.con.values.get(String.valueOf(col));                    
                    this.isObserver = true;
                    referredCell.register(this);
                    term = referredCell.getReferredCell();
                    if(term == Expression.ERROR_MESSAGE)
                        return Expression.ERROR_MESSAGE;
                    resultList.add(term);
                }else{
                    resultList.add(term);
                }
            }
            return String.join(" ", resultList);            
        }
        return value;
        
    }

}