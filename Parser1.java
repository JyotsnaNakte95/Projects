package com.java.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.java.models.Admin_PanelModel;

public class Parser1 {

	public Admin_PanelModel get_SingleNews(String json)
	{
		Admin_PanelModel resut=new Admin_PanelModel();
		  try {
			   Log.e("Data", json);
				JSONArray ja = new JSONArray(json);
				JSONObject jo = null;
				  
				for(int i=0; i<ja.length(); i++) {
					
					jo = ja.getJSONObject(i);
					//Log.e("Data", jo.getString("locationid"));
					
					
					String news_detailid=jo.getString("news_detailid");
       	    	String news_title=jo.getString("news_title");
       	    	String news_details=jo.getString("news_details");
       	    	String news_source=jo.getString("news_source");
       	    	String author=jo.getString("author");
       	    	String news_type=jo.getString("news_type");
       	    	String image_name=jo.getString("image_name");
       	    	 
       	    //	String price=jo.getString("price");
       	    	//String hours=jo.getString("hours");
       	     
       	    	Admin_PanelModel pking=new Admin_PanelModel();
       	    	pking.setNewsID(news_detailid);
       	    	pking.setTitle(news_title);
       	    	pking.setShort_Details(news_details);
       	    	pking.setNew_Source(news_source);
       	    	pking.setImage_Location(image_name);
       	    	pking.setNew_Type(news_type);
       	    	
       	    	//Log.d("status",""+confirm_status);
       	     
				resut=pking;
       	    	 
					 
				}	
				
			}catch (Exception e) {
				Log.e("Webservice 3", e.toString());
			}
		return resut;
	}
	public List<Admin_PanelModel> get_All_News(String json)
	{
		   String[] Location_Array={"","Nerul","Thane","CST","Andheri"};
		   List<Admin_PanelModel> pendingList = new ArrayList<Admin_PanelModel>();
		  
		   try {
			   Log.e("Data", json);
				JSONArray ja = new JSONArray(json);
				JSONObject jo = null;
				  
				for(int i=0; i<ja.length(); i++) {
					
					jo = ja.getJSONObject(i);
					//Log.e("Data", jo.getString("locationid"));
					
					
					String news_detailid=jo.getString("news_detailid");
        	    	String news_title=jo.getString("news_title");
        	    	String news_details=jo.getString("news_details");
        	    	String news_source=jo.getString("news_source");
        	    	String author=jo.getString("author");
        	    	String news_type=jo.getString("news_type");
        	    	String image_name=jo.getString("image_name");
        	    	 
        	    //	String price=jo.getString("price");
        	    	//String hours=jo.getString("hours");
        	     
        	    	Admin_PanelModel pking=new Admin_PanelModel();
        	    	pking.setNewsID(news_detailid);
        	    	pking.setTitle(news_title);
        	    	pking.setShort_Details(news_details);
        	    	pking.setNew_Source(news_source);
        	    	pking.setImage_Location(image_name);
        	    	pking.setNew_Type(news_type);
        	    	
        	    	//Log.d("status",""+confirm_status);
        	     
					
        	    	pendingList.add(pking);
					 
				}	
				
			}catch (Exception e) {
				Log.e("Webservice 3", e.toString());
			}
		   
		   return pendingList;
	}

	
}
