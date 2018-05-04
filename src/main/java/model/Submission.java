package model;

public class Submission {
	
	private int grade;
	private String link;
	private String remark;

	public Submission() {

	}

	public Submission(int grade, String link, String remark) {
		this.grade = grade;
		this.link = link;
		this.remark = remark;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String toString() {
		String g;
		if (this.grade > 0) {
			g = String.valueOf(this.grade);
		} else {
			g = "not graded yet";
		}
		return "Submission" + System.lineSeparator() + 
				"grade = " + g + System.lineSeparator() + 
				"link = " + this.link + System.lineSeparator() + 
				"remark = " + this.remark;
	}


}
