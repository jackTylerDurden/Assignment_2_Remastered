/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
public class SineExpression implements Expression{

    public Expression operand;    

    public SineExpression(Expression op1) {
        operand = op1;
	}

	public double evaluate(Context values){        
        return Math.sin(operand.evaluate(values));
    }

}