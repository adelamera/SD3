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

import model.Attendence;

public class HttpAttendence {

	// get by id
	public static Attendence getAttendence(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/attendences/id?id=" + String.valueOf(id));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper attendence = new ObjectMapper();
			Attendence a = new Attendence();
			a = attendence.readValue(output, Attendence.class);
			httpClient.getConnectionManager().shutdown();
			return a;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get all
	public static List<Attendence> getAllAttendences() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/attendences");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper attendence = new ObjectMapper();
			Attendence[] attendences = attendence.readValue(output, Attendence[].class);
			List<Attendence> allAttendences = new ArrayList<Attendence>(Arrays.asList(attendences));
			httpClient.getConnectionManager().shutdown();
			return allAttendences;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get by student id
	public static List<Attendence> getbyStudentId(Long studentId) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/attendences/studentId?studentId=" + String.valueOf(studentId));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper attendence = new ObjectMapper();
			Attendence[] attendences = attendence.readValue(output, Attendence[].class);
			List<Attendence> allAttendences = new ArrayList<Attendence>(Arrays.asList(attendences));
			httpClient.getConnectionManager().shutdown();
			return allAttendences;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// save
	public static String saveAttendence(Long laboratoryId, Long id) {
		try {
			Attendence attendence = new Attendence();
			attendence.setLaboratoryId(laboratoryId);
			attendence.setStudentId(id);
			Gson gson = new Gson();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/attendences");
			StringEntity a = new StringEntity(gson.toJson(attendence));
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
	public static String updateAttendence(Long id, Long laboratoryId, Long studentId) {
		try {
			Attendence attendence = new Attendence();
			attendence.setLaboratoryId(laboratoryId);
			attendence.setStudentId(studentId);
			Gson gson = new Gson();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/attendences/id?id=" + String.valueOf(id));
			StringEntity a = new StringEntity(gson.toJson(attendence));
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
	public static String deleteAttendence(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/attendences/id?id=" + String.valueOf(id));
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
