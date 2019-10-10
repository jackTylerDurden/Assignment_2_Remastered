package spreadsheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Client {
    public  JFrame frame;
    public  ClientTableModel dataModel = new ClientTableModel();
    public  JTable clientTable;
    public  Context con = new Context();
    public  Boolean isValueView = false;
    public  Boolean isToggled = false;
    public  Boolean undoStarted = false;
    public JButton toggleButton = new JButton("Toggle");
    public JButton undoButton = new JButton("Undo");
    public  void setupGrid() {        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame Title
        frame.setTitle("Equation View");
        // Initializing the JTable
        clientTable = new JTable(dataModel);        
        con.saveState();
        clientTable.setBounds(30, 40, 200, 300);        
        JScrollPane sp = new JScrollPane(clientTable);
        
        toggleButton.setBounds(40, 95, 90, 50);
        toggleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isToggled = true;
                if(!isValueView){
                    isValueView = true;                    
                    frame.setTitle("Value View");
                    con.switchToValueView();                                      
                }else{
                    isValueView = false;
                    frame.setTitle("Equation View");
                    con.switchToEquationView();                    
                }
                isToggled = false;
            }
            
        });
        frame.add(toggleButton);


        // JButton undoButton = new JButton("Undo");
        undoButton.setBounds(150, 95, 90, 50);
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoStarted = true;
                Driver.client.con.undo();                
                undoStarted = false;
            }            
        });
        frame.add(undoButton);
        frame.add(sp);        
        
                

        // Frame Size 
        frame.setSize(500, 200); 
        // Frame Visible = true 
        frame.setVisible(true); 
    } 
}