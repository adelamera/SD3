package model;

public class Attendence {
	
	private Long studentId;
	private Long laboratoryId;

	public Attendence() {

	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
	
	public String toString() {
		return "Attendence" + System.lineSeparator() + 
				"student id = " + this.studentId + System.lineSeparator() +
				"laboratory id = " + this.laboratoryId;
	}

}
