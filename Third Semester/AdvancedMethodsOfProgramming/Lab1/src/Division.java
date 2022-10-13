import java.util.List;

public class Division extends ComplexExpression
{
    public Division(ComplexNumber[]args) {
        super(Operation.DIVISION, args);
    }

    @Override
    ComplexNumber executeOneOperation(ComplexNumber c1,ComplexNumber c2)
    {
        return c1.division(c2);
    }
}