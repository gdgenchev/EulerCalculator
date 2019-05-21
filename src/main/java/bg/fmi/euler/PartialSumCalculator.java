package bg.fmi.euler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class PartialSumCalculator implements Callable<BigDecimal> {
    private List<Integer> membersToCalculate;


    public PartialSumCalculator(List<Integer> membersToCalculate) {
        this.membersToCalculate = membersToCalculate;
    }

    @Override
    public BigDecimal call() {
        long start = System.currentTimeMillis();
        BigDecimal partialSum = BigDecimal.ZERO;
        for (Integer member : membersToCalculate) {
            partialSum = partialSum.add(calculateFormula(member));
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished in "
        + (System.currentTimeMillis() - start) + " ms");
        return partialSum;
    }

    private BigDecimal calculateFormula(Integer member) {
        long numerator = 3 - 4 * member * member;
        BigDecimal denominator = factorial(2 * member + 1);
        return BigDecimal.valueOf(numerator).divide(denominator, 1000, RoundingMode.HALF_EVEN);
    }

    // p = 2000
    // 1 = 6715
    // 2 = 3435
    // 4 = 1991

    // p = 10000
    // 1 = 455
    // 2 = 435

    // p = 50000
    // 1 = 3560
    // 2 =


    private BigDecimal factorial(int n) {
        if (n == 0 || n == 1) {
            return BigDecimal.ONE;
        }
        BigDecimal fact = BigDecimal.ONE
                    ;
        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(BigDecimal.valueOf(i));
        }
        return fact;
    }
}
