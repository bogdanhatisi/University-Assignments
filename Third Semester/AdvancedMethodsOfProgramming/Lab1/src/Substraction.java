import java.util.List;

public class Substraction extends ComplexExpression
{
    public Substraction(ComplexNumber[] args) {
        super(Operation.SUBSTRACTION, args);
    }

    @Override
    ComplexNumber executeOneOperation()
    {
        return args[0].substraction(args[1]);
    }
}