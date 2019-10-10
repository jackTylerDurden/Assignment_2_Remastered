/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
import java.util.*;
public class PostfixEvaluator{

    static Expression fetchOperatorToInterpret(String operator,Expression op1, Expression op2){
        operator = operator.trim();
        switch(operator){
            case "+" : return new AdditionExpression(op1, op2);

            case "*" : return new MultiplicationExpresion(op1, op2);

            case "/" : return new DivisionExpression(op1, op2);
            
            case "-" : return new SubtractionExpression(op1, op2);            
        }
        return null;
    }

    public static String evaluateExpression(String postfixExpression){            
        String result = "";
        if(postfixExpression.isEmpty()){
            return "";
        }else if(postfixExpression.equals(Expression.ERROR_MESSAGE)){
            return Expression.ERROR_MESSAGE;
        }
        try{
            postfixExpression = postfixExpression.toLowerCase();
            postfixExpression = postfixExpression.replaceAll("sin", String.valueOf(Expression.SINE_OPERATOR));
            postfixExpression = postfixExpression.replaceAll("log", String.valueOf(Expression.LOG_OPERATOR));            
            Stack<String> postfixStack = new Stack<String>();            
            for(String term : postfixExpression.split(" ")) {                
                if(!Expression.OPERATORSET.contains(term)){
                    String operand = "";                                            
                    operand = term;
                    if(operand != null && !operand.isEmpty()){
                        postfixStack.push(operand);                        
                    }
                }else{
                    if(term.equals("+") || term.equals("*") || term.equals("/") || term.equals("-")){                        
                        Expression op1 = new ConstantExpression(Double.valueOf(postfixStack.pop()));
                        Expression op2 = new ConstantExpression(Double.valueOf(postfixStack.pop()));
                        Expression operation = fetchOperatorToInterpret(term,op1,op2);
                        postfixStack.push(String.valueOf(operation.evaluate(Driver.client.con)));
                    }else if(term.equals(Expression.SINE_OPERATOR)){
                        Expression op = new ConstantExpression(Double.valueOf(postfixStack.pop()));
                        Expression sin = new SineExpression(op);
                        postfixStack.push(String.valueOf(sin.evaluate(Driver.client.con)));
                    }else if(term.equals(Expression.LOG_OPERATOR)){
                        Expression op = new ConstantExpression(Double.valueOf(postfixStack.pop()));
                        Expression log = new LogExpression(op);
                        postfixStack.push(String.valueOf(log.evaluate(Driver.client.con)));
                    }
                }                                   
            }
            result = postfixStack.pop();            
        }catch(Exception e){
            e.printStackTrace();    
            return Expression.ERROR_MESSAGE;
        }                
        return result;
    }
}