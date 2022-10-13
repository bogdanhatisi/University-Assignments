import java.util.ArrayList;
import java.util.List;

enum Operation{
    ADDITION,
    SUBSTRACTION,
    MULTIPLICATION,
    DIVISION
}

public abstract class ComplexExpression {
    private Operation operation;
    ComplexNumber[] args;

    public ComplexExpression(Operation operation, ComplexNumber[] args) {
        this.operation = operation;
        this.args = args;
    }

    abstract ComplexNumber executeOneOperation(ComplexNumber c1,ComplexNumber c2);

    public final ComplexNumber execute()
    {   ComplexNumber result = args[0];
        ComplexNumber[] operators = new ComplexNumber[2];
        for(int j = 1; j <= args.length - 1; j++)
        {
            operators[0] = result;
            operators[1] = args[j];
            result = executeOneOperation(operators[0],operators[1]);


        }

        return result;
    }

}
