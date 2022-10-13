import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpressionParser {

    String[] args;
    Operation operatie = null;
    List<ComplexNumber> numbers = new ArrayList<>();

    public ExpressionParser(String[] args) {
        this.args = args;

    }


    public void getOperation()
    {
        Operation operatie = null;
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
        List<ComplexNumber> numbers = new ArrayList<>();

        int i = 0;

        while (i <= args.length - 1) {

            String[] firstSplit = args[i].split("[+-]");
            System.out.println(firstSplit[0]);

            String[] secondSplit = firstSplit[1].split("\\*");
            System.out.println(secondSplit[0]);
            int xTemp = Integer.parseInt(firstSplit[0]);
            String signTemp = args[i].substring(1, 2);
            int yTemp = Integer.parseInt(secondSplit[0]);
            if (Objects.equals(signTemp, "-")) {
                yTemp = yTemp * (-1);
            }
            numbers.add(new ComplexNumber(xTemp, yTemp));
            i++;
            i++;

        }

        this.numbers = numbers;

    }

    public void run()
    {
        getOperation();
        parseNumbers();

        ComplexNumber result = numbers.get(0);
        ComplexNumber[] operators = new ComplexNumber[2];
        ExpressionFactory factory = new ExpressionFactory();
        for(int j = 1; j <= numbers.size() - 1; j++)
        {
            operators[0] = result;
            operators[1] = numbers.get(j);
            result = factory.createExpression(operatie,operators).execute();

        }

        System.out.println(result.getReal());
        System.out.println(result.getImaginary());
    }

}
