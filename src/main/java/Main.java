
import java.util.List;

import client.ApacheHttpSubmission;
import model.Submission;

public class Main {

	public static void main(String[] args) {
		List<Submission> s = ApacheHttpSubmission.getAllSubmissionsOfStudent(Long.valueOf(1));
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i).toString());
		}
		
		

	}

}
