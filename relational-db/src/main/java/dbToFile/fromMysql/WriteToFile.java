package dbToFile.fromMysql;

import java.io.*;

/**
 * Created by firstsword on 2019/1/14.
 */
public class WriteToFile {

    public static BufferedWriter bw;


    public static BufferedWriter getBufferedWriter(String path) {
        if (bw == null) {
            try {
                bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(
                                        new File(path)
                                ),
                                "UTF-8")
                );

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return bw;
    }


    public static void writeString(String s) {

        try {
            bw.write(s);
            //bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void close() {
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
