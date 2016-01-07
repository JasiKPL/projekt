package connector;

import java.sql.*;
import java.util.ArrayList;

import models.User;

public class Connector {
	
	public static String register(User u){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("insert into users values(?, ?, ?, ?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setInt(4, u.getType().ordinal());
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("Error while registering user: " + e.toString());
			return "Wystąpił błąd";
		}
		
		return "Poprawnie zarejestrowano";
	}
	
	public static User authenticate(String username, String password){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setType(User.Type.values()[rs.getInt("type")]);
				return u;
			}
		}catch(Exception e){
			System.out.println("Error while registering user: " + e.toString());
			return null;
		}
		return null;
	}
	
	public static User getUser(String username){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from users where username=?");
            ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setType(User.Type.values()[rs.getInt("type")]);
				return u;
			}
		}catch(Exception e){
			System.out.println("Error while registering user: " + e.toString());
			return null;
		}
		return null;
	}
	
	public static ArrayList<User> getUsers(){
		ArrayList<User> result = new ArrayList<User>();
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setType(User.Type.values()[rs.getInt("type")]);
				result.add(u);
			}
		}catch(Exception e){
			System.out.println("Error while registering user: " + e.toString());
			return null;
		}
		return result;
	}
	
	public static String togglePremium(String username){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from users where username=?");
            ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			User u = new User();
			while (rs.next()) {
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setType(User.Type.values()[rs.getInt("type")]);
				break;
			}
			if (u.getType() == User.Type.REGULAR) {
				u.setType(User.Type.PREMIUM);
			} else if (u.getType() == User.Type.PREMIUM) {
				u.setType(User.Type.REGULAR);
			}
			
			ps = con.prepareStatement("update users set type = ? where username = ?");
			ps.setInt(1, u.getType().ordinal());
			ps.setString(2, u.getUsername());
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("Error while registering user: " + e.toString());
			return "Wystąpił błąd";
		}
		return "Uprawnienia premium zmienione";
	}
}
