package com.example.newsapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.java.models.*;
import com.java.services.WebService1;
import com.java.adapters.*;
public class Admin_Panel extends ActionBarActivity{

	  private List<Admin_PanelModel> pendingList = new ArrayList<Admin_PanelModel>();
	  private Admin_PanelAdapter list_adapter;

	  private ListView listView;
	  public String decision;
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.admin_panel);
	        setTitle("Admin Panel");
	        
	        listView = (ListView) findViewById(R.id.list);
	        
	      //List View Event
 			ListView_Event();
	        
	        AsyncTaskRunner runner = new AsyncTaskRunner();
	  		runner.execute("Ok");
	      
 	}
	
	public void Add_Click(View v)
	{
		 Intent intent = new Intent(getApplicationContext(), Admin_AddNews.class);
			// intent.putExtra("NEWS_ID", selected_Values.getNewsID());
		 startActivity(intent);
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
	                
	            	WebService1 web=new WebService1();
	          	//	String result=web.onLogin(username, password);
	          	 	pendingList=web.getPendings("abc","");
	          	 	 	
	            } catch (Exception e) {
	                e.printStackTrace();
	                resp = e.getMessage();
	            }
	            return resp;
	        }


	        @Override
	        protected void onPostExecute(String result) {
	            // execution of result of Long time consuming operation
	        	
		 	    list_adapter= new Admin_PanelAdapter(Admin_Panel.this, pendingList);
	 	        listView.setAdapter(list_adapter);
	            progressDialog.dismiss();
	            
	            //if(decision.equalsIgnoreCase("Approve"))
	            //{
	            //	Tooat(resp);
	           // }
	            //finalResult.setText(result);
	        }


	        @Override
	        protected void onPreExecute() {
	            progressDialog = ProgressDialog.show(Admin_Panel.this,
	                    "Process",
	                    "Please wait...");
	        }


	        @Override
	        protected void onProgressUpdate(String... text) {
	            //finalResult.setText(text[0]);
	        
	        }
	    }
}
