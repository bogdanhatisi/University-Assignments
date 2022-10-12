import java.util.List;

public class ExpressionFactory {
    public ComplexExpression createExpression(Operation operation, ComplexNumber[]args){
        if(operation == Operation.ADDITION)
        {
            return new Addition(args);
        }
        else if(operation == Operation.SUBSTRACTION)
        {
            return new Substraction(args);
        }
        else if(operation == Operation.DIVISION)
        {
            return new Division(args);
        }
        else if(operation == Operation.MULTIPLICATION)
        {
            return new Multiplication(args);
        }

        return null;
    }
}
