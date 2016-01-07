package models;

public class User {
	public enum Type {
		REGULAR,
		PREMIUM,
		ADMIN
	}
	
	private String username, password, password_confirmation, email;
	private Type type;

	public String validate() {
		if (username == "") {
			return "Enter username";
		}
		if (password == "") {
			return "Enter password";
		}
		if (password_confirmation == "") {
			return "Enter confirmation";
		}
		if (email == "") {
			return "Enter email";
		}
		if (!password.equals(password_confirmation)) {
			return "Passwords do not match";
		}
		return "";
	}
	
	public boolean isPremium() {
		return type.ordinal() >= Type.PREMIUM.ordinal();
	}
	
	public boolean isAdmin() {
		return type == Type.ADMIN;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return password_confirmation;
	}

	public void setPasswordConfirmation(String password) {
		this.password_confirmation = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
