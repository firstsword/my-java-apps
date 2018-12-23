package writeToDB.tomysql;

import java.sql.Connection;
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

        String sql = "INSERT INTO student (sid,sname,sage) VALUES (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        int count = 0;
        long begin = System.currentTimeMillis();

        for (int i = start; i <= end; i++) {
            stmt.setInt(1, i);
            stmt.setString(2, "wind" + i);
            stmt.setInt(3, 20);

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
