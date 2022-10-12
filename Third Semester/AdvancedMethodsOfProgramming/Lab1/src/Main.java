import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        List<ComplexNumber> numbers = new ArrayList<>();
        List<Operation> operations = new ArrayList<>();

        int i = 0;

        while (i <= args.length - 1) {
            int xTemp = Integer.parseInt(args[i].substring(0, 1));
            String signTemp = args[i].substring(1, 2);
            int yTemp = Integer.parseInt(args[i].substring(2, 3));
            if (Objects.equals(signTemp, "-")) {
                yTemp = yTemp * (-1);
            }
            numbers.add(new ComplexNumber(xTemp, yTemp));
            i++;

            if ((i + 1) > args.length - 1)
                break;


            if (Objects.equals(args[i], "+")) {

                operations.add(Operation.ADDITION);

            } else if (Objects.equals(args[i], "-")) {
                operations.add(Operation.SUBSTRACTION);
            } else if (Objects.equals(args[i], "*")) {
                operations.add(Operation.MULTIPLICATION);
            } else if (Objects.equals(args[i], "/")) {
                operations.add(Operation.DIVISION);
            }
            i++;

        }


//        for (int j = 0; j <= numbers.size() - 1; j++) {
//            System.out.println(numbers.get(j).getReal());
//            System.out.println(numbers.get(j).getImaginary());
//        }
//
//
//        for (int j = 0; j <= operations.size() - 1; j++) {
//            System.out.println(operations.get(j));
//        }

        ComplexNumber result = numbers.get(0);
        ComplexNumber[] operators = new ComplexNumber[2];
        ExpressionFactory factory = new ExpressionFactory();
        int operatie = 0;
        for(int j = 1; j <= numbers.size() - 1; j++)
        {
            operators[0] = result;
            operators[1] = numbers.get(j);
            result = factory.createExpression(operations.get(operatie),operators).execute();
            operatie++;

        }

        System.out.println(result.getReal());
        System.out.println(result.getImaginary());


    }
}