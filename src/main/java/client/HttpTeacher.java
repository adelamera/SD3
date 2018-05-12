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

import model.Teacher;

public class HttpTeacher {

	// get by id
	public static Teacher getTeacher(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/teachers/id?id=" + String.valueOf(id));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper teacher = new ObjectMapper();
			Teacher t = new Teacher();
			t = teacher.readValue(output, Teacher.class);
			httpClient.getConnectionManager().shutdown();
			return t;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get all
	public static List<Teacher> getAllTeachers() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/teachers");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper teacher = new ObjectMapper();
			Teacher[] teachers = teacher.readValue(output, Teacher[].class);
			List<Teacher> allTeachers = new ArrayList<Teacher>(Arrays.asList(teachers));
			httpClient.getConnectionManager().shutdown();
			return allTeachers;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// save
	public static void saveTeacher(Teacher teacher) {
		try {
			Gson gson = new Gson();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/teachers");
			StringEntity t = new StringEntity(gson.toJson(teacher));
			t.setContentType("application/json");
			postRequest.setEntity(t);
			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// update
	public static void updateTeacher(Long id, Teacher teacher) {
		try {
			Gson gson = new Gson();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/teachers/id?id=" + String.valueOf(id));
			StringEntity t = new StringEntity(gson.toJson(teacher));
			t.setContentType("application/json");
			putRequest.setEntity(t);
			HttpResponse response = httpClient.execute(putRequest);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// delete
	public static void deleteTeacher(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/teachers/id?id=" + String.valueOf(id));
			deleteRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(deleteRequest);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
