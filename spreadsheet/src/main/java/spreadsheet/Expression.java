/*
    Author - Tanmay Deshpande
    Red ID - 824646024
    Subject - CS 635 CS 635 Advanced Object-Oriented Design and Prorgramming 
*/
package spreadsheet;
import java.util.*;

public interface Expression{
    public static final String SINE_OPERATOR = "€";
    public static final String LOG_OPERATOR = "¶";        
    public static final String NUMBER_REGEX = "[+-]?\\d*\\.?\\d*";
    public static final Set<String> OPERATORSET = new HashSet<String>(Arrays.asList("+","-","*","/",SINE_OPERATOR,LOG_OPERATOR));
    public static final int FIRST_ALPHABET_ASCII = (int) ('a');
    public static final String ERROR_MESSAGE = "error";	
    public double evaluate(Context values);
    public String toString();
}