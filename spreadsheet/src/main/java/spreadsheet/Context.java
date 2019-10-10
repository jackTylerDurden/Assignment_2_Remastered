package spreadsheet;
import java.util.*;
public class Context {
    LinkedHashMap<String,Cell> values = new LinkedHashMap<String,Cell>();    
    LinkedHashMap<String,String> colIndexToExpressionMap = new LinkedHashMap<String,String>();
    Set<String> dependentCellsSet = new HashSet<String>();

    // Equation View undo functionality
    Caretaker caretakerForEquationView = new Caretaker();
    Originator originatorForEquationView = new Originator();
    int currentCellStateForEquationView = -1;

    // Value View undo functionality
    Caretaker caretakerForValueView;    
    Originator originatorForValueView;
    int currentCellStateForValueView = 0;

    public String getValue(String key){        
        if(values.get(key) == null){
            Cell cell = new Cell("");                        
            values.put(key,cell);
        }            
        return values.get(key).getCellValue();
    }

    public void setValue(String key,String value){       
        Cell cellToAssign = values.get(key); 
        if(cellToAssign == null){
            cellToAssign = new Cell("");            
        }                  
        values.put(key,cellToAssign);
        cellToAssign.setCellValue(value,Integer.valueOf(key));       
        if(!Driver.client.undoStarted && !cellToAssign.isObserver){
            saveState();
        }        
    }

    public void saveState(){
        LinkedHashMap<String,String> contextStateMap = new LinkedHashMap<>();        
        if(Driver.client.con.values.keySet().size() > 0){
            for(String key : Driver.client.con.values.keySet()){
                Cell presentCell = Driver.client.con.values.get(key);
                if(presentCell == null)
                    presentCell = new Cell("");
                if(Driver.client.isValueView && !presentCell.isObserver){                
                    contextStateMap.put(key,presentCell.value);                
                }else if(!Driver.client.isValueView){                
                    contextStateMap.put(key,presentCell.value);                
                }            
            }
        }else{
            for(int i=0; i<Driver.client.dataModel.getColumnCount();i++ ){
                contextStateMap.put(String.valueOf(i),"");
            }
        }       
        if(Driver.client.isValueView ){
            Driver.client.con.originatorForValueView.set(contextStateMap);
            Driver.client.con.caretakerForValueView.addMemento(Driver.client.con.originatorForValueView.storeInMemento());            
            Driver.client.con.currentCellStateForValueView++;
        }else if(!Driver.client.isValueView && !Driver.client.isToggled ){
            Driver.client.con.originatorForEquationView.set(contextStateMap);
            Driver.client.con.caretakerForEquationView.addMemento(Driver.client.con.originatorForEquationView.storeInMemento());            
            Driver.client.con.currentCellStateForEquationView++;
        }        

    }

    public void undo(){
            LinkedHashMap<String,String> contextAfterUndo = new LinkedHashMap<>();
            Boolean performUndo = false;
            if(Driver.client.isValueView){
                if(Driver.client.con.currentCellStateForValueView >= 1){
                    performUndo = true;
                    Driver.client.con.currentCellStateForValueView--;
                    contextAfterUndo = Driver.client.con.originatorForValueView.restoreFromMemento(Driver.client.con.caretakerForValueView.fetchMemento(Driver.client.con.currentCellStateForValueView));    
                }
            }else{
                if(Driver.client.con.currentCellStateForEquationView >= 1){
                    performUndo = true;
                    Driver.client.con.currentCellStateForEquationView--;
                    contextAfterUndo = Driver.client.con.originatorForEquationView.restoreFromMemento(Driver.client.con.caretakerForEquationView.fetchMemento(Driver.client.con.currentCellStateForEquationView));
                }
            }
            if(performUndo){
                for(String cellKey : Driver.client.con.values.keySet()){                
                    String pastCellVal = contextAfterUndo.get(cellKey);
                    if(pastCellVal == null)
                        pastCellVal = "";
                    Cell presentCell = Driver.client.con.values.get(cellKey);
                    if(!Driver.client.isValueView && !pastCellVal.equals(presentCell.value)){ //differnetial update        
                        Driver.client.dataModel.setValueAt(pastCellVal, 0, Integer.valueOf(cellKey));
                    }else if(Driver.client.isValueView && !presentCell.isObserver && !pastCellVal.equals(presentCell.value)){
                        Driver.client.dataModel.setValueAt(pastCellVal, 0, Integer.valueOf(cellKey));
                    }
                }
            }
            
        
    }
    
    public void switchToValueView(){        
        for(String cell : Driver.client.con.values.keySet()){            
            String val = Driver.client.con.getValue(cell);            
            val = val.trim();                        
            if(!val.isEmpty()){
                Cell cellToAssign = Driver.client.con.values.get(cell);
                if(!val.matches(Expression.NUMBER_REGEX)){
                    Driver.client.con.colIndexToExpressionMap.put(cell,val);
                    Driver.client.con.values.put(cell,cellToAssign);
                    cellToAssign.setCellValue(cellToAssign.getReferredCell(), Integer.valueOf(cell));
                    cellToAssign.evaluate();
                    Driver.client.con.dependentCellsSet = new HashSet<String>();                     
                }                
            }
        }
        Driver.client.con.currentCellStateForValueView = -1;
        Driver.client.con.caretakerForValueView = new Caretaker();
        Driver.client.con.originatorForValueView = new Originator();    
        Driver.client.con.saveState();
    }
    
    public void switchToEquationView(){
        for(String cell : Driver.client.con.values.keySet()){            
            String expression = Driver.client.con.colIndexToExpressionMap.get(cell);            
            Cell cellToAssign = Driver.client.con.values.get(cell);
            if(expression != null){                
                cellToAssign.setCellValue(expression,Integer.valueOf(cell));
                Driver.client.con.values.put(cell,cellToAssign);                                    
                Driver.client.dataModel.setValueAt(expression, 0, Integer.valueOf(cell));
            }else{
                Driver.client.dataModel.setValueAt(cellToAssign.value, 0, Integer.valueOf(cell));
            }            
        }
    }        
}