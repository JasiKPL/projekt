package connector;

import java.sql.*;
import java.util.ArrayList;

import models.Address;
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
			System.out.println("Error while listing users: " + e.toString());
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

	public static String createAddress(Address a){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("insert into addresses values(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, a.getId());
            ps.setString(2, a.getUsername());
            ps.setString(3, a.getProvince());
            ps.setString(4, a.getCity());
            ps.setString(5, a.getZip());
            ps.setString(6, a.getStreet());
            ps.setString(7, a.getApartment());
            ps.setInt(8, a.getType().ordinal());
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("Error while creating address: " + e.toString());
			return "Wystąpił błąd";
		}

		return "Poprawnie dodano";
	}

	public static ArrayList<Address> getAddresses(String username){
		ArrayList<Address> result = new ArrayList<Address>();
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from addresses where username=?");
            ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Address a = new Address();
				a.setId(rs.getString("id"));
				a.setUsername(rs.getString("username"));
				a.setProvince(rs.getString("province"));
				a.setCity(rs.getString("city"));
				a.setZip(rs.getString("zip"));
				a.setStreet(rs.getString("street"));
				a.setApartment(rs.getString("apartment"));
				a.setType(Address.Type.values()[rs.getInt("type")]);
				result.add(a);
			}
		}catch(Exception e){
			System.out.println("Error while listing addresses: " + e.toString());
			return null;
		}
		return result;
	}

	public static Address getAddress(String id){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from addresses where id=?");
            ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Address a = new Address();
				a.setId(rs.getString("id"));
				a.setUsername(rs.getString("username"));
				a.setProvince(rs.getString("province"));
				a.setCity(rs.getString("city"));
				a.setZip(rs.getString("zip"));
				a.setStreet(rs.getString("street"));
				a.setApartment(rs.getString("apartment"));
				a.setType(Address.Type.values()[rs.getInt("type")]);
				return a;
			}
		}catch(Exception e){
			System.out.println("Error while listing addresses: " + e.toString());
			return null;
		}
		return null;
	}

	public static String updateAddress(Address a){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("update addresses set `username`=?, `province`=?, `city`=?, `zip`=?, `street`=?, `apartment`=?, `type`=? where `id`=?");
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getProvince());
            ps.setString(3, a.getCity());
            ps.setString(4, a.getZip());
            ps.setString(5, a.getStreet());
            ps.setString(6, a.getApartment());
            ps.setInt(7, a.getType().ordinal());
            ps.setString(8, a.getId());
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("Error while updating address: " + e.toString());
			return "Wystąpił błąd";
		}
		return "Poprawnie zaktualizowano";
	}

	public static String deleteAddress(String id){
		try{
			Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("delete from addresses where id=?");
            ps.setString(1, id);
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("Error while deleting address: " + e.toString());
			return "Wystąpił błąd";
		}

		return "Poprawnie usunięto";
	}
}
