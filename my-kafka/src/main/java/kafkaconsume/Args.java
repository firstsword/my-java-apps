package kafkaconsume;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Created by firstsword on 2019/2/13.
 */
public class Args {

    @Option(name = "-servers", usage="kafka servers")
    public String servers;

    @Option(name = "-group", usage="kafka consumer group")
    public String group;

    @Option(name = "-topic", usage="kafka topic")
    public String topic;

    public void parseArgs(String[] args) throws Exception{
        CmdLineParser p = new CmdLineParser(this);
        p.parseArgument(args);
    }
}
