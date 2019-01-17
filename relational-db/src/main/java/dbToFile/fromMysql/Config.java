package dbToFile.fromMysql;

import java.io.*;
import java.util.Properties;

/**
 * Created by firstsword on 2019/1/14.
 */
public class Config {

    public String dbUrl;
    public String dbUser;
    public String dbPassword;

    public String sqlMax;
    public String sqlMin;
    public String query;

    public long step;

    public String outpath;

    public String separator;

    public long maxValue;
    public long minValue;


    public static Config getConfig(String path) {
        //InputStream is = Config.class.getClassLoader().getResourceAsStream(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            Properties props = new Properties();

            Config db = new Config();

            props.load(br);

            db.dbUrl = props.getProperty("dbUrl");
            db.dbUser = props.getProperty("dbUser");
            db.dbPassword = props.getProperty("dbPassword");

            db.sqlMax = props.getProperty("sql_max");
            db.sqlMin = props.getProperty("sql_min");
            db.query = props.getProperty("query");

            db.step = Long.valueOf(props.getProperty("step"));

            db.outpath = props.getProperty("outpath");
            db.separator = props.getProperty("separator");

            db.maxValue = Long.valueOf(props.getProperty("max_value"));
            db.minValue = Long.valueOf(props.getProperty("min_value"));

            return db;
        } catch (IOException e) {
            System.out.println("read conf fail...");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return dbUrl + "," + dbUser + "," + dbPassword;
    }
}
