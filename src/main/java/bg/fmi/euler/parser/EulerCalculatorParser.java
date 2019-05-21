package bg.fmi.euler.parser;

import bg.fmi.euler.parser.exceptions.InvalidNumberException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static bg.fmi.euler.parser.ProgramOption.*;

public class EulerCalculatorParser {
    private Options options;

    public EulerCalculatorParser() {
        options = new Options();
        for (ProgramOption option : ProgramOption.values()) {
            options.addOption(option.getOption(), option.hasArg(), option.getDescription());
        }
    }

    public EulerCalculatorArguments parseArgs(String[] args) {
        EulerCalculatorArguments parsedArguments = new EulerCalculatorArguments();
        DefaultParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(MEMBERS.getOption())) {
                parsedArguments.setMembers(cmd.getOptionValue(MEMBERS.getOption()));
            }
            if (cmd.hasOption(THREADS.getOption())) {
                parsedArguments.setThreads(cmd.getOptionValue(THREADS.getOption()));
            }
            if (cmd.hasOption(QUIET.getOption())) {
                parsedArguments.setQuiet();
            }
            if (cmd.hasOption(OUTPUT.getOption())) {
                parsedArguments.setOutputFilename(cmd.getOptionValue(OUTPUT.getOption()));
            }
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }
        catch (ParseException e) {
            System.out.println("Could not parse options. Using default settings");
        }

        return parsedArguments;
    }
}
