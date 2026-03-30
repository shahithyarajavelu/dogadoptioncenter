package dao;

import java.sql.*;
import entiry.User;
import util.ConnectionPool;

public class UserDAO {

    public int register(User user) throws SQLException {

        Connection con = ConnectionPool.getConnection();

        String query = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());

        int result = ps.executeUpdate();

        ConnectionPool.returnConnection(con);
        return result;7
    }

    public int login(String email, String password) throws SQLException {

        Connection con = ConnectionPool.getConnection();

        String query = "SELECT * FROM users WHERE email=? AND password=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("user_id");
        }

        ConnectionPool.returnConnection(con);
        return -1;
    }
}