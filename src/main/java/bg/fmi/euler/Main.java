package bg.fmi.euler;


import bg.fmi.euler.parser.EulerCalculatorArguments;
import bg.fmi.euler.parser.EulerCalculatorParser;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EulerCalculatorParser parser = new EulerCalculatorParser();
        EulerCalculatorArguments arguments = parser.parseArgs(args);
        EulerCalculator eulerCalculator = new EulerCalculator(arguments);
        eulerCalculator.calculate();
    }

    //1 - 1116
    //2 - 700
    //4 - 518
    //8 - 354
}
