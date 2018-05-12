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

import model.Lab;

public class HttpLab {

	// get by id
	public static Lab getLab(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/labs/id?id=" + String.valueOf(id));
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper lab = new ObjectMapper();
			Lab l = new Lab();
			l = lab.readValue(output, Lab.class);
			httpClient.getConnectionManager().shutdown();
			return l;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get by laboratory number
	public static Long getByLabNr(int laboratoryNr) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/labs/laboratoryNr?laboratoryNr=" + String.valueOf(laboratoryNr));
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

	// get by keyword
	public static List<Lab> getByKeyword(String keyword) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/labs/keyword?keyword=" + keyword);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper lab = new ObjectMapper();
			Lab[] labs = lab.readValue(output, Lab[].class);
			List<Lab> allLabs = new ArrayList<Lab>(Arrays.asList(labs));
			httpClient.getConnectionManager().shutdown();
			return allLabs;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get all
	public static List<Lab> getAllLabs() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/labs");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output = br.readLine();
			ObjectMapper lab = new ObjectMapper();
			Lab[] labs = lab.readValue(output, Lab[].class);
			List<Lab> allLabs = new ArrayList<Lab>(Arrays.asList(labs));
			httpClient.getConnectionManager().shutdown();
			return allLabs;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// save
	public static String saveLab(int labNr, Date date, String title, String curricula, String description) {
		try {
			Lab lab = new Lab(labNr, date, title, curricula, description);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/labs");
			StringEntity l = new StringEntity(gson.toJson(lab));
			l.setContentType("application/json");
			postRequest.setEntity(l);
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
	public static String updateLab(Long id, int labNr, Date date, String title, String curricula, String description) {
		try {
			Lab lab = new Lab(labNr, date, title, curricula, description);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/labs/id?id=" + String.valueOf(id));
			StringEntity l = new StringEntity(gson.toJson(lab));
			l.setContentType("application/json");
			putRequest.setEntity(l);
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
	public static String deleteLab(Long id) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/labs/id?id=" + String.valueOf(id));
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
