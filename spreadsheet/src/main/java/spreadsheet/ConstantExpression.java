/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
public class ConstantExpression implements Expression{
    private double value;

    public ConstantExpression(Double value){
        this.value = value;        
    }

    public double evaluate(Context values){        
        return value;
    }
}