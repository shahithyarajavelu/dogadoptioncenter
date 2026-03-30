package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static String url = "jdbc:mysql://localhost:3306/dog_center";
    private static String user = "root";
    private static String password = "sql123";

    private static List<Connection> pool = new ArrayList<>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            for (int i = 0; i < 5; i++) {
                pool.add(DriverManager.getConnection(url, user, password));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (pool.size() > 0) {
            return pool.remove(0);
        }
        return null;
    }

    public static void returnConnection(Connection con) {
        pool.add(con);
    }
}