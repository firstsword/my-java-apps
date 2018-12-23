package writeToDB.topgsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

public class InsertCallable implements Callable<Long> {

    public int start;
    public int end;

    public InsertCallable() {
    }

    public InsertCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private Long insert() throws Exception {
        Connection conn = ConnUtils.getConnection();
        conn.setAutoCommit(false);

        String sql = "INSERT INTO testtable (t_varchar,t_character,t_text,t_boolean,t_float,t_timestamp,t_date,t_integer,t_price) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        int count = 0;
        long begin = System.currentTimeMillis();

        for (int i = start; i <= end; i++) {
            stmt.setString(1, String.valueOf(i));
            stmt.setString(2, "t_character_" + i);
            stmt.setString(3, "t_text_" + i);
            stmt.setBoolean(4, true);
            stmt.setDouble(5, Double.valueOf(i + ".111"));
            stmt.setInt(6, i);
            stmt.setDate(7, new Date(2018, 6, 6));
            stmt.setInt(8, i);
            stmt.setDouble(9, Double.valueOf(i + ".222"));

            stmt.addBatch();
            count++;

            if (count % CommonVars.ROW_BATCH == 0) {
                stmt.executeBatch();
                conn.commit();
            }
        }

        conn.commit();
        stmt.close();
        conn.close();

        return (System.currentTimeMillis() - begin) / 1000;
    }

    public Long call() throws Exception {
        return insert();
    }
}
