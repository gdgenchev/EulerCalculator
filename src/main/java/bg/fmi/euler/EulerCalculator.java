package bg.fmi.euler;

import bg.fmi.euler.parser.EulerCalculatorArguments;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EulerCalculator {
    private boolean quiet;
    private int threads;
    private int lastMember;
    private File outputFile;
    private List<List<Integer>> workForEachThread;
    private int maxWorkSize;
    private BigDecimal eulerNumber;
    private ExecutorService executor;

    public EulerCalculator(EulerCalculatorArguments arguments) {
        this.quiet = arguments.hasQuiet();
        this.threads = arguments.getThreads();
        this.lastMember = arguments.getMembers();
        this.outputFile = new File(arguments.getOutputFilename());
        this.initWorkForEachThread();
        this.maxWorkSize = lastMember / threads + (lastMember % threads == 0 ? 0 : 1);
        this.eulerNumber = BigDecimal.ZERO;
        executor = Executors.newFixedThreadPool(threads);

    }

    private void initWorkForEachThread() {
        this.workForEachThread = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            workForEachThread.add(new ArrayList<>(maxWorkSize));
        }
    }

    public BigDecimal calculate() {
        distributeWorkForEachThread();
        long minElapsed = Long.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            eulerNumber = BigDecimal.ZERO;
            long startTime = System.currentTimeMillis();
            try {
                runWorkers();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis() - startTime;
            if (endTime < minElapsed) {
                minElapsed = endTime;
            }
            if (!quiet) {
                System.out.println("Threads used in current run: " + threads + "\n"
                        + "Total execution time for current run (millis): " + endTime);
            }
            System.out.println(eulerNumber);
        }
        System.out.println(minElapsed);
        return null;
    }

    // 1 - 125
    // 2 - 142

    private void runWorkers() throws InterruptedException, ExecutionException {
        List<Future<BigDecimal>> partialSums = new ArrayList<>(threads);

        for (int i = 0; i < threads; i++) {
            partialSums.add(executor.submit(new PartialSumCalculator(workForEachThread.get(i))));
        }
        for (Future<BigDecimal> partialSum : partialSums) {
            eulerNumber = eulerNumber.add(partialSum.get());
        }
    }

    private void distributeWorkForEachThread() {
        for (int currMember = 0; currMember < lastMember; currMember++) {
            workForEachThread.get(currMember % threads).add(currMember);
        }
    }

}
