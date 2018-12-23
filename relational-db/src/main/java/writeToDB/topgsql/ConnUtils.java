package writeToDB.topgsql;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class ConnUtils {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            dataSource.setDriverClass("org.postgresql.Driver"); //loads the jdbc driver
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUser("postgres");
            dataSource.setPassword("password");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static void close() throws Exception {
        dataSource.close();
    }
}
