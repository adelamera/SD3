package model;

import java.sql.Date;

public class Assignment {
	
	private String name;
	private Date deadline;
	private String description;

	public Assignment() {

	}

	public Assignment(String name, Date deadline, String description) {
		this.name = name;
		this.deadline = deadline;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "Assignment" + System.lineSeparator() + 
				"name = " + this.name + System.lineSeparator() + 
				"deadline = " + this.deadline + System.lineSeparator() +
				"description = " + this.description;
	}


}
