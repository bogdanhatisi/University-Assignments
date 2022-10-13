import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpressionParser {

    String[] args;
    Operation operatie = null;
    ComplexNumber[] numbers;


    public ExpressionParser(String[] args) {
        this.args = args;
        numbers = new ComplexNumber[args.length/2 + 1];

    }


    public void getOperation()
    {
        Operation operatie = null;
        if(args.length <= 1) {
            operatie = null;
            return;
        }
        if (Objects.equals(args[1], "+")) {

            operatie = Operation.ADDITION;

        } else if (Objects.equals(args[1], "-")) {
            operatie = Operation.SUBSTRACTION;
        } else if (Objects.equals(args[1], "*")) {
            operatie = Operation.MULTIPLICATION;
        } else if (Objects.equals(args[1], "/")) {
            operatie = Operation.DIVISION;
        }
        this.operatie = operatie;
    }
    public void parseNumbers() {


        int i = 0;
        int current = 0;

        while (i <= args.length - 1) {

            String[] firstSplit = args[i].split("[+-]");


            String[] secondSplit = firstSplit[1].split("\\*");

            int xTemp = Integer.parseInt(firstSplit[0]);
            String signTemp = args[i].substring(1, 2);
            int yTemp = Integer.parseInt(secondSplit[0]);
            if (Objects.equals(signTemp, "-")) {
                yTemp = yTemp * (-1);
            }
            numbers[current]=new ComplexNumber(xTemp, yTemp);
            i = i + 2;
            current++;

        }


    }

    public String messageFormat(ComplexNumber c1)
    {
        String message = "The final result of the operation is: ";
        message += c1.getReal();
        int imaginary = c1.getImaginary();
        if(c1.getImaginary() > 0)
        {
            message += " + ";


        }
        else {
            message += " - ";
            imaginary = imaginary * (-1);
        }

        message += imaginary;
        message += " * i";

        return message;
    }

    public void run()
    {
        getOperation();
        parseNumbers();

        if(operatie != null) {
            ComplexNumber result = numbers[0];
            ComplexNumber[] operators = new ComplexNumber[2];
            ExpressionFactory factory = new ExpressionFactory();
            ComplexNumber finalResult = factory.createExpression(operatie, numbers).execute();

            String message = messageFormat(finalResult);
            System.out.println(message);
        }
        else{
            String message = messageFormat(numbers[0]);
            System.out.println(message);
        }
    }

}
