package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Lab {

	private int laboratoryNr;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	private String title;
	private String curricula;
	private String description;

	public Lab() {

	}

	public Lab(int laboratoryNr, Date date, String title, String curricula, String description) {
		this.laboratoryNr = laboratoryNr;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.description = description;
	}

	public int getLaboratoryNr() {
		return this.laboratoryNr;
	}

	public void setLaboratoryNr(int laboratoryNr) {
		this.laboratoryNr = laboratoryNr;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public String getCurricula() {
		return this.curricula;
	}

	public void setCurricula(String curricula) {
		this.curricula = curricula;

	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public String toString() {
		return "Laboratory" + System.lineSeparator() +
				"number = " + this.laboratoryNr + System.lineSeparator() + 
				"date = " + this.date + System.lineSeparator() + 
				"title = " + this.title + System.lineSeparator() +
				"curricula = " + this.curricula + System.lineSeparator() +
				"description = " + this.description;
	}

}
