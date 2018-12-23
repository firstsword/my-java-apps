package writeToDB.tomysql;


import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;

public class ConnUtils {

    private static DruidDataSource dataSource = new DruidDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Password");
        dataSource.setUrl("jdbc:mysql://192.168.31.136:3306/wind?useServerPrepStmts=false&rewriteBatchedStatements=true");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        // 启用监控统计功能 dataSource.setFilters("stat");
        // for mysql  dataSource.setPoolPreparedStatements(false);
        dataSource.setPoolPreparedStatements(false);
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static void close() throws Exception {
        dataSource.close();
    }
}
