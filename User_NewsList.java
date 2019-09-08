package com.example.newsapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView; 
import android.widget.Toast;

import com.java.adapters.Admin_PanelAdapter;
import com.java.config.Config;
import com.java.models.Admin_PanelModel;
import com.java.services.WebService1;

public class User_NewsList extends ActionBarActivity{

	String NEWS_TYPE;
	
	  private List<Admin_PanelModel> pendingList = new ArrayList<Admin_PanelModel>();
	  private Admin_PanelAdapter list_adapter;

	  private ListView listView;
	  public String decision;
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.user_newslist);
	        setTitle("Admin Panel");
	        
	        
	        NEWS_TYPE="";
			Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	        	NEWS_TYPE= extras.getString("NEWS_TYPE");
	        }
	        
	        Tooat(NEWS_TYPE);
	        listView = (ListView) findViewById(R.id.list);
	        
	      //List View Event
			ListView_Event();
	        
	        AsyncTaskRunner runner = new AsyncTaskRunner();
	  		runner.execute("Ok");
	      
	}
	

    
    public void Tooat(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();	
    }
	
	private void ListView_Event() {
		
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 			@Override
					public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Admin_PanelModel selected_Values=pendingList.get(position);
			 				 Intent intent = new Intent(getApplicationContext(), User_NewsDetail.class);
			 				 intent.putExtra("NEWS_ID", selected_Values.getNewsID());
			 				 startActivity(intent);
						}
			        });
			
			
		}
	
	
	
	 private class AsyncTaskRunner extends AsyncTask<String, String, String> {

			
	        private String resp;
	        ProgressDialog progressDialog;

	        @Override
	        protected String doInBackground(String... params) {
	            publishProgress("Process..."); // Calls onProgressUpdate()
	            try {
	            	Log.d("User", NEWS_TYPE);
	            	WebService1 web=new WebService1();
	            
	          	//	String result=web.onLogin(username, password);
	            	if(NEWS_TYPE.equalsIgnoreCase("Recommended"))
	            	{
	            		pendingList=web.getPendings(Config.UserID,Config.UserID);
	            	}
	             	if(NEWS_TYPE.equalsIgnoreCase("Entertainment"))
	            	{
	            		pendingList=web.getPendings("Entertainment","");
	            	}
	            	if(NEWS_TYPE.equalsIgnoreCase("Technology"))
	            	{
	            		pendingList=web.getPendings("Technology","");
	            	}
	            	if(NEWS_TYPE.equalsIgnoreCase("Business"))
	            	{
	            		pendingList=web.getPendings("Business","");
	            	}
	            	if(NEWS_TYPE.equalsIgnoreCase("Sport"))
	            	{
	            		pendingList=web.getPendings("Sports","");
	            	}
	            	if(NEWS_TYPE.equalsIgnoreCase("Science"))
	            	{
	            		pendingList=web.getPendings("Science","");
	            	}	            	
	            	if(NEWS_TYPE.equalsIgnoreCase("Health"))
	            	{
	            		pendingList=web.getPendings("Health","");
	            	}
	            	
	            	
	          	 
	          	 	 	
	            } catch (Exception e) {
	                e.printStackTrace();
	                resp = e.getMessage();
	            }
	            return resp;
	        }


	        @Override
	        protected void onPostExecute(String result) {
	            // execution of result of Long time consuming operation
	        	
		 	    list_adapter= new Admin_PanelAdapter(User_NewsList.this, pendingList);
	 	        listView.setAdapter(list_adapter);
	            progressDialog.dismiss();
	            
	            //if(decision.equalsIgnoreCase("Approve"))
	            //{
	            	Tooat(resp);
	           // }
	            //finalResult.setText(result);
	        }


	        @Override
	        protected void onPreExecute() {
	            progressDialog = ProgressDialog.show(User_NewsList.this,"Process","Please wait...");
	        }


	        @Override
	        protected void onProgressUpdate(String... text) {
	            //finalResult.setText(text[0]);
	        
	        }
	    }
	
}
