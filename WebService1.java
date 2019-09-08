package com.java.services;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.java.config.Config;
import com.java.models.Admin_PanelModel;
import com.java.parsers.Parser1;

import android.util.Log;
 
public class WebService1 {

	public String onRegister(String fullname,String username,String password,String email,String mobile,String gender,String favourite)
	{
		String result="";
		String parameter=Config.IP+"/personal_news/services/register.php";
		try
		{
			String line="";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(parameter);
			
			//Parameters
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("fullname", fullname));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("mobile", mobile));
			params.add(new BasicNameValuePair("gender", gender));
			params.add(new BasicNameValuePair("option", favourite));
			
			Log.d("well","1");
			httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			Log.d("well","2");
			HttpResponse response = httpClient.execute(httpPost);
			Log.d("well","2");
			HttpEntity entity = response.getEntity();
			Log.d("well","3");
			InputStream is = entity.getContent();
			Log.d("well","4");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
			StringBuilder sb = new StringBuilder();
			Log.d("well","2");
			while((line = reader.readLine()) != null) {
				
				sb.append(line + "\n");
				Log.e("Login Data : ", line);
			}
		 	is.close();
			result = sb.toString();
		 	 	
		}
		catch(Exception ex)
		{
			String error=ex.getMessage();
			result=error;
			Log.e("Login Error : ", error);
		}
		return result;
	}
	
	public String onLogin(String username,String password)
	{
		String result="";
		String parameter=Config.IP+"/personal_news/services/validate_users.php";
		try
		{
			String line="";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(parameter);
			
			//Parameters
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			
			Log.d("well","1");
			httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			Log.d("well","2");
			HttpResponse response = httpClient.execute(httpPost);
			Log.d("well","2");
			HttpEntity entity = response.getEntity();
			Log.d("well","3");
			InputStream is = entity.getContent();
			Log.d("well","4");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
			StringBuilder sb = new StringBuilder();
			Log.d("well","2");
			while((line = reader.readLine()) != null) {
				
				sb.append(line + "\n");
				Log.e("Login Data : ", line);
			}
		 	is.close();
			result = sb.toString();
		 	 	
		}
		catch(Exception ex)
		{
			String error=ex.getMessage();
			result=error;
			Log.e("Login Error : ", error);
		}
		return result;
	}
	
	public Admin_PanelModel getSingleNews(String news_id,String user_id)
	{
		Admin_PanelModel news=new Admin_PanelModel();
		 String result="";
			String parameter=Config.IP+"/personal_news/services/get_specificnews.php";
			try
			{
				String line="";
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(parameter);
				
				//Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>(1);
				params.add(new BasicNameValuePair("id", news_id));
				params.add(new BasicNameValuePair("userid", user_id));
				
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			 	//Log.d("well","2");
				HttpResponse response = httpClient.execute(httpPost);
				//Log.d("well","2");
				HttpEntity entity = response.getEntity();
				//Log.d("well","3");
				InputStream is = entity.getContent();
				Log.d("well","4");
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
				StringBuilder sb = new StringBuilder();
				Log.d("well","2");
				while((line = reader.readLine()) != null) {
					
					sb.append(line + "\n");
					//Log.e("Login Data : ", line);
				}
			 	is.close();
				result = sb.toString();
				Parser1 parser=new Parser1();
				Log.d("Data",result);
				news=parser.get_SingleNews(result);
			}
			catch(Exception ex)
			{
				String error=ex.getMessage();
				result=error;
				Log.e("Login Error : ", error);
			}
		return news;
	}
	
	public List<Admin_PanelModel> getPendings(String user_id,String some)
	{
		   List<Admin_PanelModel> pendingList = new ArrayList<Admin_PanelModel>();
		 
		   String result="";
		   
			String parameter="";
			if(some.equals(""))
			{
				parameter=Config.IP+"/personal_news/services/get_categorywise_news.php";	
			}
			else
			{
				parameter=Config.IP+"/personal_news/services/recommended_news.php";	
			}
			
			try
			{
				String line="";
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(parameter);
				
				//Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>(1);
				params.add(new BasicNameValuePair("news_tp", user_id));
			  
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			 	//Log.d("well","2");
				HttpResponse response = httpClient.execute(httpPost);
				//Log.d("well","2");
				HttpEntity entity = response.getEntity();
				//Log.d("well","3");
				InputStream is = entity.getContent();
				Log.d("well","4");
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
				StringBuilder sb = new StringBuilder();
				Log.d("well","2");
				while((line = reader.readLine()) != null) {
					
					sb.append(line + "\n");
					//Log.e("Login Data : ", line);
				}
			 	is.close();
				result = sb.toString();
				Parser1 parser=new Parser1();
				Log.d("Data",result);
				pendingList=parser.get_All_News(result);
			}
			catch(Exception ex)
			{
				String error=ex.getMessage();
				result=error;
				Log.e("Login Error : ", error);
			}
		   
			
			
		   return pendingList;
	}
	
 
	
	
	
	
	
	
	
	
}
