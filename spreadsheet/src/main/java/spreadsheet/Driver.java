package spreadsheet;
public class Driver {    
    static Client client;
    public static void main(String[] args){        
        client = new Client();
        client.setupGrid();
    }    
}