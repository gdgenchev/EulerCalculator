package bg.fmi.euler.parser;

public enum ProgramOption {
    MEMBERS("p", true,"number of members"),
    THREADS("t", true, "number of threads"),
    QUIET  ("q", false, "quiet mode"),
    OUTPUT ("o", true, "output file");

    private String opt;
    private boolean hasArg;
    private String desc;

    ProgramOption(String opt, boolean hasArg, String desc) {
        this.opt = opt;
        this.hasArg = hasArg;
        this.desc = desc;
    }

    public String getOption() {
        return opt;
    }

    public boolean hasArg() {
        return hasArg;
    }

    public String getDescription() {
        return desc;
    }
}
