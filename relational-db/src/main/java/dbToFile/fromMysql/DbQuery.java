package dbToFile.fromMysql;

import java.sql.*;

/**
 * Created by firstsword on 2019/1/14.
 */
public class DbQuery {
    public static Connection conn;

    public static Connection getConnection(Config dbconf) {

        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Class.forName("org.postgresql.Driver").newInstance();
                conn = DriverManager.getConnection(dbconf.dbUrl, dbconf.dbUser, dbconf.dbPassword);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return conn;
    }

    public static ResultSet execQuery(String sql) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public static long getMaxOrMin(String sql) throws Exception {
        ResultSet rs = execQuery(sql);
        rs.next();
        return rs.getLong(1);
    }

    public static ResultSet execQuery(String sql, long start, long end) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, start);
        pstmt.setLong(2, end);
        return pstmt.executeQuery();
    }
}
