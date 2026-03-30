package dao;

import java.sql.*;
import util.ConnectionPool;

public class DogDAO {

    public void viewAvailableDogs() throws SQLException {

        Connection con = ConnectionPool.getConnection();

        String query = "SELECT * FROM dogs WHERE status='Available'";
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt("dog_id") + " "
                            + rs.getString("dog_name") + " "
                            + rs.getString("breed") + " "
                            + rs.getInt("age"));
        }

        ConnectionPool.returnConnection(con);
    }

	
		
	}
