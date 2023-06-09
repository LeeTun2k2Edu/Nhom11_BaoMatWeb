package DAO;

import Connection.ConnectJDBC;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class LoginDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Users login(String username, String password) {
        String query = "select * from KhachHang where TenTK='"+username+"'";

        try {
            conn = new ConnectJDBC().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

        
        	
        	while(rs.next())
    		{
        		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,5*1024,2);
        		System.out.println("gia tri cl : "+  rs.getString(7));
    			if(encoder.matches(password, rs.getString(7)))
    				return new Users(rs.getInt(1),
    					rs.getString(2),
    					rs.getString(3),
                    	rs.getString(4),
                    	rs.getString(5),
                    	rs.getString(6),
                    	rs.getString(7),
                    	rs.getString(8),
                    	rs.getInt(9),
                    	rs.getInt(10),
                    	rs.getInt(11),
                    	rs.getInt(12));
    		}
        
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    public Users CheckUsers(String email) {
        String query = "select * from KhachHang\r\n"
                + "where Email = ?";

        try {
            conn = new ConnectJDBC().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    public void ChangePass(int id, String pass) {
        String querry = "update KhachHang set MK=?, NNMK = ? where MaKH=?";
        try {

            conn = new ConnectJDBC().getConnection();

            ps = conn.prepareStatement(querry);
            ps.setString(1, pass);
            ps.setString(2, pass);
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }


}
