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

    abstract ComplexNumber executeOneOperation();

    public final ComplexNumber execute()
    {
        return executeOneOperation();
    }

}
