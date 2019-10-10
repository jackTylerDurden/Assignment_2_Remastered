/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DriverTest{    
    @Test
    public void testAdditionExpression(){        
        Driver.client = new Client();
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("2.0", 0,1);
        Driver.client.dataModel.setValueAt("B A +", 0,2);
        Driver.client.toggleButton.doClick();        
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("3.0",Driver.client.dataModel.getValueAt(0,2));        
    }

    @Test
    public void testSubtractionExpression(){ 
        Driver.client = new Client();      
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("2.0",0,0);
        Driver.client.dataModel.setValueAt("1.0", 0,1);
        Driver.client.dataModel.setValueAt("B A -", 0,2);
        Driver.client.toggleButton.doClick();        
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,2));           
    }

    @Test
    public void testMultiplicationExpression(){ 
        Driver.client = new Client();      
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("2.0",0,0);
        Driver.client.dataModel.setValueAt("5.0", 0,1);
        Driver.client.dataModel.setValueAt("B A *", 0,2);
        Driver.client.toggleButton.doClick();        
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("5.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,2));         
    }

    @Test
    public void testDivisionExpression(){
        Driver.client = new Client();       
        Driver.client.setupGrid();                
        Driver.client.dataModel.setValueAt("4.0",0,0);
        Driver.client.dataModel.setValueAt("2.0",0,1);
        Driver.client.dataModel.setValueAt("B A /",0,2);
        Driver.client.toggleButton.doClick();        
        assertEquals("4.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,2));    
    }

    @Test
    public void testLogExpression(){ 
        Driver.client = new Client();      
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("2.0",0,0);
        Driver.client.dataModel.setValueAt("A log",0,1);
        Driver.client.toggleButton.doClick();        
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,1));        
    }

    @Test
    public void testSineExpression(){
        Driver.client = new Client();       
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("0.0",0,0);
        Driver.client.dataModel.setValueAt("A sin",0,1);
        Driver.client.toggleButton.doClick();        
        assertEquals("0.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("0.0",Driver.client.dataModel.getValueAt(0,1));        
    }

    @Test
    public void testSwitchToValueView(){ 
        Driver.client = new Client();      
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);
        Driver.client.toggleButton.doClick();        
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("6.0",Driver.client.dataModel.getValueAt(0,3));         
    }

    @Test
    public void testSwitchToEquationView(){
        Driver.client = new Client();
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);
        Driver.client.toggleButton.doClick();       
        Driver.client.toggleButton.doClick();       
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("A C +",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("1 D +",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("5 A +",Driver.client.dataModel.getValueAt(0,3));         
    }
    
    @Test
    public void testSwitchToValueViewForCircularDependecy(){ 
        Driver.client = new Client();        
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 B +",0,3);                
        Driver.client.toggleButton.doClick();        
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals(Expression.ERROR_MESSAGE,Driver.client.dataModel.getValueAt(0,1));
        assertEquals(Expression.ERROR_MESSAGE,Driver.client.dataModel.getValueAt(0,2));         
        assertEquals(Expression.ERROR_MESSAGE,Driver.client.dataModel.getValueAt(0,3));      
    }

    @Test
    public void testSwitchToValueViewForUndoEquationView(){
        Driver.client = new Client();
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);                
        Driver.client.dataModel.setValueAt("5 B +",0,3);                
        Driver.client.undoButton.doClick();            
        assertEquals("5 A +",Driver.client.dataModel.getValueAt(0,3));
    }

    @Test
    public void testEquationViewUndoUnlimited(){
        Driver.client = new Client();
        Driver.client.setupGrid();        
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);        
        Driver.client.undoButton.doClick();            
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("A C +",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("1 D +",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("",Driver.client.dataModel.getValueAt(0,3)); 
        Driver.client.undoButton.doClick();    
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("A C +",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("",Driver.client.dataModel.getValueAt(0,3)); 
        Driver.client.undoButton.doClick();    
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("",Driver.client.dataModel.getValueAt(0,3)); 
        Driver.client.undoButton.doClick();    
        assertEquals("",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("",Driver.client.dataModel.getValueAt(0,3)); 
    }

    @Test
    public void testObserverPattern(){
        Driver.client = new Client();
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);
        Driver.client.toggleButton.doClick();
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("6.0",Driver.client.dataModel.getValueAt(0,3));  
        Driver.client.con.setValue("0", "2.0");        
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,3));  
    }


    @Test
    public void testValueViewUndo(){
        Driver.client = new Client();
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);       
        Driver.client.toggleButton.doClick();
        Driver.client.con.setValue("0", "2.0");        
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,3));  
        Driver.client.undoButton.doClick();
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("6.0",Driver.client.dataModel.getValueAt(0,3));
    }

    @Test
    public void testValueViewUndoUnlimited(){
        Driver.client = new Client();
        Driver.client.setupGrid();
        Driver.client.dataModel.setValueAt("1.0",0,0);
        Driver.client.dataModel.setValueAt("A C +",0,1);
        Driver.client.dataModel.setValueAt("1 D +",0,2);
        Driver.client.dataModel.setValueAt("5 A +",0,3);         
        Driver.client.toggleButton.doClick();
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("6.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.con.setValue("0", "2.0");       
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.con.setValue("0", "3.0");       
        assertEquals("3.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("12.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("9.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.con.setValue("0", "4.0");       
        assertEquals("4.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("14.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("9.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.undoButton.doClick();
        assertEquals("3.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("12.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("9.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.undoButton.doClick();
        assertEquals("2.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("10.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,3));
        Driver.client.undoButton.doClick();
        assertEquals("1.0",Driver.client.dataModel.getValueAt(0,0));
        assertEquals("8.0",Driver.client.dataModel.getValueAt(0,1));
        assertEquals("7.0",Driver.client.dataModel.getValueAt(0,2));         
        assertEquals("6.0",Driver.client.dataModel.getValueAt(0,3));
    }
}