import java.util.List;

public class Addition extends ComplexExpression
{
    public Addition(ComplexNumber[] args) {
        super(Operation.ADDITION,args);
    }

    @Override
    ComplexNumber executeOneOperation()
    {
        return args[0].addition(args[1]);
    }
}
