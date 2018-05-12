package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Student;

public class HttpStudent {

	// get by id
	public static Student getStudent(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/students/id?id=" + String.valueOf(id));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper student = new ObjectMapper();
			Student s = new Student();
			s = student.readValue(output, Student.class);
			httpClient.getConnectionManager().shutdown();
			return s;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get id
	public static Long getId(String username, String password) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/students/username/password?username=" + username + "&password=" + password);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			httpClient.getConnectionManager().shutdown();
			return Long.valueOf(output);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get all
	public static List<Student> getAllStudents() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/students");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper student = new ObjectMapper();
			Student[] students = student.readValue(output, Student[].class);
			List<Student> allStudents = new ArrayList<Student>(Arrays.asList(students));
			httpClient.getConnectionManager().shutdown();
			return allStudents;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// save
	public static String saveStudent(String email, String name) {
		try {
			Student student = new Student(0, name, email, null, null, null);
			Gson gson = new GsonBuilder().create();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/students");
			StringEntity s = new StringEntity(gson.toJson(student));
			s.setContentType("application/json");
			postRequest.setEntity(s);
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			httpClient.getConnectionManager().shutdown();
			return (result.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// update
	public static String updateStudent(Long id, String name, String email, String username, String password, int group,
			String hobby) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/students/id?id=" + String.valueOf(id));
			StringEntity s = new StringEntity("{" + 
					"   \"email\": \"" + email + "\"," + 
					"   \"group\": \"" + String.valueOf(group) + "\"," + 
					"   \"hobby\": \"" + hobby + "\"," + 
					"   \"name\": \"" + name + "\","  + 
					"   \"password\": \"" + password + "\"," + 
					"   \"username\": \"" + username + "\"" + 
					" }");
			s.setContentType("application/json");
			putRequest.setEntity(s);
			HttpResponse response = httpClient.execute(putRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			httpClient.getConnectionManager().shutdown();
			return (result.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// register
	public static String register(String username, String name, String email, String newUsername, String password, int group,
			String hobby) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/students/username?username=" + username);
			StringEntity s = new StringEntity("{" + 
					"   \"email\": \"" + email + "\"," + 
					"   \"group\": \"" + String.valueOf(group) + "\"," + 
					"   \"hobby\": \"" + hobby + "\"," + 
					"   \"name\": \"" + name + "\","  + 
					"   \"password\": \"" + password + "\"," + 
					"   \"username\": \"" + newUsername + "\"" + 
					" }");
			s.setContentType("application/json");
			putRequest.setEntity(s);
			HttpResponse response = httpClient.execute(putRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			httpClient.getConnectionManager().shutdown();
			return (result.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// delete
	public static String deleteStudent(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/students/id?id=" + String.valueOf(id));
			deleteRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(deleteRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			httpClient.getConnectionManager().shutdown();
			return (result.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
