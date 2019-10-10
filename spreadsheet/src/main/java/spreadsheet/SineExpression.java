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