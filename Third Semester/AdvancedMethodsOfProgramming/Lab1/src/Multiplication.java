import java.util.List;

public class Multiplication extends ComplexExpression
{
    public Multiplication(ComplexNumber[] args) {
        super(Operation.MULTIPLICATION, args);
    }

    @Override
    ComplexNumber executeOneOperation(ComplexNumber c1,ComplexNumber c2)
    {
        return c1.multiplication(c2);
    }
}