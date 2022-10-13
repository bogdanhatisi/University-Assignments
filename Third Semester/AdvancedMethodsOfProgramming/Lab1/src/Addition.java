import java.util.List;

public class Addition extends ComplexExpression
{
    public Addition(ComplexNumber[] args) {
        super(Operation.ADDITION,args);
    }

    @Override
    ComplexNumber executeOneOperation(ComplexNumber c1,ComplexNumber c2)
    {
        return c1.addition(c2);
    }
}
