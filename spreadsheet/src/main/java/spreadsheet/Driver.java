/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
public class Driver {    
    static Client client;
    public static void main(String[] args){        
        client = new Client();
        client.setupGrid();
    }    
}