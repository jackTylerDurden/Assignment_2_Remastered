package spreadsheet;

public class AdditionExpression implements Expression {

    public Expression operand1;
    public Expression operand2;

    public AdditionExpression(Expression op1, Expression op2) {
        operand1 = op1;
        operand2 = op2;
	}

	public double evaluate(Context values){        
        return operand1.evaluate(values) + operand2.evaluate(values);
    }

}