package kafkaMirror;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by firstsword on 2019/1/14.
 */
public class Config {

    public String srcTopic;
    public String targetTopic;

    public String srcServers;
    public String targetServers;

    public int timeout;


    public static Config getConfig(String path) {
        //InputStream is = Config.class.getClassLoader().getResourceAsStream(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            Properties props = new Properties();

            Config conf = new Config();

            props.load(br);

            conf.srcTopic = props.getProperty("srcTopic");
            conf.targetTopic = props.getProperty("targetTopic");

            conf.srcServers = props.getProperty("srcServers");
            conf.targetServers = props.getProperty("targetServers");

            conf.timeout = Integer.valueOf(props.getProperty("timeout"));

            return conf;
        } catch (IOException e) {
            System.out.println("read conf fail...");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return srcTopic + "," + targetTopic;
    }
}
