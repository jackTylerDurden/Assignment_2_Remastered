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