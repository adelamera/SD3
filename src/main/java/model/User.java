package model;

public class User {

	private String username;
	private String password;
	private String type;

	public User() {

	}

	public User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		if ((type.equals("student")) || (type.equals("teacher"))) {
			this.type = type;
		}
	}

	public String toString() {
		return "User: " + this.getUsername();
	}

}
