package argsStudy;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Created by firstsword on 2019/2/13.
 */
public class Args {

    @Option(name = "-o", usage="Option")
    public String option;

    @Option(name = "-r", required = true, usage="Required")
    public String required;

    public void parseArgs(String[] args) throws Exception{
        CmdLineParser p = new CmdLineParser(this);
        p.parseArgument(args);
    }
}
