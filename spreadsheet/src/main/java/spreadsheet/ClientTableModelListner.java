/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ClientTableModelListner implements TableModelListener{

    public ClientTableModelListner(){
        Driver.client.clientTable.getModel().addTableModelListener(this);
    }
    public void tableChanged(TableModelEvent e){
        System.out.println("from event Lisnter----------->>>");
        int row = e.getFirstRow();
        int column = e.getColumn();
        ClientTableModel model = (ClientTableModel)e.getSource();        
        Object data = model.getValueAt(row, column);
        System.out.println("data--------->>>"+data);
    }
}