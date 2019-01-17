package dbToFile.fromMysql;

import java.sql.ResultSet;

/**
 * Created by firstsword on 2019/1/14.
 */
public class App {

    public static void main(String[] args) throws Exception{
        Config dbConf = Config.getConfig(args[0]);

        System.out.println(dbConf.toString());

        DbQuery.getConnection(dbConf);//初始化
        WriteToFile.getBufferedWriter(dbConf.outpath);

        long min = 0;
        long max = 0;

        if (dbConf.sqlMin == null || dbConf.sqlMin.trim().length() == 0) {
            min = dbConf.minValue - 1;
        } else {
            min = DbQuery.getMaxOrMin(dbConf.sqlMin) - 1;
        }

        if (dbConf.sqlMax == null || dbConf.sqlMax.trim().length() == 0) {
            max = dbConf.maxValue;
        } else {
            max = DbQuery.getMaxOrMin(dbConf.sqlMax);
        }

        long step = dbConf.step;


        for (long i = min; i <= max; i += step) {
            long j = i + step;
            if (j > max) j = max;
            System.out.println(i + ", " + j);

            WriteToFile.writeString(getValue(DbQuery.execQuery(dbConf.query, i, j), dbConf.separator));
        }
    }

    public static String getQuerySql(long begin, long end) {
        /*String sql = "select history.itemid, history.clock, history.`value`, `hosts`.host, items.key_ from history " +
                "inner join items on history.itemid = items.itemid " +
                "inner join `hosts` on `hosts`.hostid = items.hostid" +
                "where history.clock > " + begin + "and" +
                "history.clock <= " + end;
        return sql;*/

        String sql = "select * from student " +
                "where sid > " + begin + " and " +
                "sid <= " + end;
        return sql;
    }

    public static String getValue2(ResultSet rs, String separator) throws Exception {
        StringBuilder sb = new StringBuilder();

        while (rs.next()) {
            sb.append(rs.getInt(1)).append(separator);
            sb.append(rs.getString(2)).append(separator);
            sb.append(rs.getInt(3)).append("\n");
        }

        return sb.toString();
    }

    public static String getValue(ResultSet rs, String separator) throws Exception {
        StringBuilder sb = new StringBuilder();

        while (rs.next()) {
            sb.append(rs.getLong(1)).append(separator);
            sb.append(rs.getLong(2)).append(separator);
            sb.append(rs.getDouble(3)).append(separator);
            sb.append(rs.getString(4)).append(separator);
            sb.append(rs.getString(5)).append("\n");
        }

        return sb.toString();
    }
}
