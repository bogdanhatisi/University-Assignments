import java.util.List;

public class Division extends ComplexExpression
{
    public Division(ComplexNumber[]args) {
        super(Operation.DIVISION, args);
    }

    @Override
    ComplexNumber executeOneOperation()
    {
        return args[0].division(args[1]);
    }
}