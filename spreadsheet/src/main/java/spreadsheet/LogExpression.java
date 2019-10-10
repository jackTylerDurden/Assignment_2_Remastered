package spreadsheet;
public class LogExpression implements Expression{

    public Expression operand;    

    public LogExpression(Expression op) {
        operand = op;        
	}

	public double evaluate(Context values){        
        return Math.log10(operand.evaluate(values)) / Math.log10(2);
    }

}