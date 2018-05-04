package model;

public class Student {

	private String name;
	private String email;
	private String username;
	private String password;
	private int groupNr;
	private String hobby;

	public Student() {

	}

	public Student(String name, String email, String username, String password, int groupNr, String hobby) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.groupNr = groupNr;
		this.hobby = hobby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getGroup() {
		return groupNr;
	}

	public void setGroup(int group) {
		this.groupNr = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String toString() {
		return "Student" + System.lineSeparator() + 
				"name = " + this.name + System.lineSeparator() + 
				"email = " + this.email + System.lineSeparator() + 
				"group = " + this.groupNr + System.lineSeparator() +
				"hobby = " + this.hobby;
	}
}
