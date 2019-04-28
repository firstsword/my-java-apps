package writeToDB.tomysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.Date;
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

        String sql = "INSERT INTO testbinlog (t_id,t_name, t_timestamp) VALUES (?,?,?)";
        //String sql = "INSERT INTO wind.student (sid, sname, sage) VALUES (?,?,?)";

        //String sql = "UPDATE testbinlog SET t_name = 'bbb' WHERE t_id = ?";
        //String sql = "DELETE FROM testbinlog WHERE t_id = ?";


        PreparedStatement stmt = conn.prepareStatement(sql);

        int count = 0;
        long begin = System.currentTimeMillis();

        for (int i = start; i <= end; i++) {
            stmt.setInt(1, i);
            stmt.setString(2, "wind" + i);
            stmt.setLong(3, System.currentTimeMillis());

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
