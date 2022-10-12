import java.util.List;

public class Multiplication extends ComplexExpression
{
    public Multiplication(ComplexNumber[] args) {
        super(Operation.MULTIPLICATION, args);
    }

    @Override
    ComplexNumber executeOneOperation()
    {
        return args[0].multiplication(args[1]);
    }
}