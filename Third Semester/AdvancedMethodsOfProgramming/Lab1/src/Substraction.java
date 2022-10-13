import java.util.List;

public class Substraction extends ComplexExpression
{
    public Substraction(ComplexNumber[] args) {
        super(Operation.SUBSTRACTION, args);
    }

    @Override
    ComplexNumber executeOneOperation(ComplexNumber c1,ComplexNumber c2)
    {
        return c1.substraction(c2);
    }
}