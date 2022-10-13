import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser(args);

        System.out.println("Complex number calculator, only with one operation across the expression.");
        System.out.println("The expression is passed in the command line as number1 operand number2...");
        parser.run();


    }
}