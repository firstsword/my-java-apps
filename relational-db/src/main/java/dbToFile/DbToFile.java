package dbToFile;

import dbToFile.fromMysql.Config;
import dbToFile.fromMysql.DbQuery;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Created by firstsword on 2019/1/14.
 */
public class DbToFile {


    @Test
    public void t1() throws Exception{
        Config dbConfig = new Config();
        dbConfig.dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        dbConfig.dbUser = "postgres";
        dbConfig.dbPassword = "123456";

        DbQuery.getConnection(dbConfig);

        ResultSet rs = DbQuery.execQuery("select * from hangxin");

        while (rs.next()) {
            System.out.println(rs);
        }


    }
}
