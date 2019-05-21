package bg.fmi.euler.parser;

import bg.fmi.euler.parser.exceptions.InvalidNumberException;

public class EulerCalculatorArguments {
    private static final int DEFAULT_MEMBERS = 100;
    private static final int DEFAULT_THREADS = 4;
    private static final String DEFAULT_OUTPUT_FILENAME = "result.txt";

    private int members;
    private int threads;
    private String outputFilename;
    private boolean quiet;

    public EulerCalculatorArguments() {
        this.members = DEFAULT_MEMBERS;
        this.threads = DEFAULT_THREADS;
        this.outputFilename = DEFAULT_OUTPUT_FILENAME;
        this.quiet = false;
    }

    public int getMembers() {
        return members;
    }


    public void setMembers(int members) {
        this.members = members;
    }

    public void setMembers(String members) {
        try {
            setMembers(Integer.parseInt(members));
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Could not parse number '" + members +"'. " +
                    "Using default members '" + DEFAULT_MEMBERS + "'");
        }
    }


    public int getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        try {
            setThreads(Integer.parseInt(threads));
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Could not parse number '" + threads +"'. " +
                    "Using default threads '" + DEFAULT_THREADS +"'");
        }
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    public void setQuiet() {
        this.quiet = true;
    }

    public boolean hasQuiet() {
        return quiet;
    }
}
