package dao;

import java.sql.*;
import util.ConnectionPool;

public class AdoptionDAO {

    public void adoptDog(int userId, int dogId) throws SQLException {

        Connection con = ConnectionPool.getConnection();

        // check availability
        String check = "SELECT status FROM dogs WHERE dog_id=?";
        PreparedStatement ps1 = con.prepareStatement(check);
        ps1.setInt(1, dogId);
        ResultSet rs = ps1.executeQuery();

        if (rs.next() && rs.getString("status").equals("Adopted")) {
            System.out.println("Dog already adopted!");
            return;
        }

        // insert adoption
        String insert = "INSERT INTO adoptions(user_id,dog_id) VALUES(?,?)";
        PreparedStatement ps2 = con.prepareStatement(insert);

        ps2.setInt(1, userId);
        ps2.setInt(2, dogId);
        ps2.executeUpdate();

        // update status
        String update = "UPDATE dogs SET status='Adopted' WHERE dog_id=?";
        PreparedStatement ps3 = con.prepareStatement(update);
        ps3.setInt(1, dogId);
        ps3.executeUpdate();

        System.out.println("Dog Adopted Successfully!");

        ConnectionPool.returnConnection(con);
    }

	
}