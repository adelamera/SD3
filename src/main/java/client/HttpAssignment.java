package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.Date;
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

import model.Assignment;

public class HttpAssignment {

	// get by id
	public static Assignment getAssignment(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/assignments/id?id=" + String.valueOf(id));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper assignment = new ObjectMapper();
			Assignment a = new Assignment();
			a = assignment.readValue(output, Assignment.class);
			httpClient.getConnectionManager().shutdown();
			return a;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get by name
	public static Long getAssignmentByName(String name) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/assignments/name?name=" + name);
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
	public static List<Assignment> getAllAssignments() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/assignments");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper assignment = new ObjectMapper();
			Assignment[] assignments = assignment.readValue(output, Assignment[].class);
			List<Assignment> allAssignments = new ArrayList<Assignment>(Arrays.asList(assignments));
			httpClient.getConnectionManager().shutdown();
			return allAssignments;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get all assignments for lab
	public static List<Assignment> getAllAssignmentsForLab(Long labId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/assignments/laboratory/id?laboratoryId=" + String.valueOf(labId));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper assignment = new ObjectMapper();
			Assignment[] assignments = assignment.readValue(output, Assignment[].class);
			List<Assignment> allAssignments = new ArrayList<Assignment>(Arrays.asList(assignments));
			httpClient.getConnectionManager().shutdown();
			return allAssignments;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// save
	public static String saveAssignment(Long laboratoryId, String name, Date date, String description) {
		try {
			Assignment assignment = new Assignment(name, date, description);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/assignments?laboratoryId=" + String.valueOf(laboratoryId));
			StringEntity a = new StringEntity(gson.toJson(assignment));
			a.setContentType("application/json");
			postRequest.setEntity(a);
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
	public static String updateAssignment(Long id, String name, Date date, String description) {
		try {
			Assignment assignment = new Assignment(name, date, description);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/assignments/id?id=" + String.valueOf(id));
			StringEntity a = new StringEntity(gson.toJson(assignment));
			a.setContentType("application/json");
			putRequest.setEntity(a);
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
	public static String deleteAssignment(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/assignments/id?id=" + String.valueOf(id));
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
