package com.example.newsapp;

import com.java.config.Config;
import com.java.services.WebService1;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private EditText edit_username;
	private EditText edit_password; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);
        
        Initialized_Data();
    }

  private void Initialized_Data() {
		// TODO Auto-generated method stub
	  edit_username=(EditText)findViewById(R.id.edit_username);
      edit_password=(EditText)findViewById(R.id.edit_password);
	}

//***************************
    // All events related to UI controls
    //**************************
    public void onLogin(View v)
    {
    	Log.d("Come","1");
    	String username=edit_username.getText().toString();
  		String password=edit_password.getText().toString(); 
  		Log.d("Come","2");
  		int furthur=0;
  		if(username.trim().equals(""))
  		{
  			Tooat("Please enter username");
  			furthur=1;
  		}
  		if(password.trim().equals("") && furthur==0)
  		{
  			Tooat("Please enter password");
  			furthur=1;
  		}
  		
  		AsyncTaskRunner runner = new AsyncTaskRunner();
  		runner.execute("Ok");
    }
    
    public void onRegister(View v)
    {
    
    	Intent intent=new Intent(getApplicationContext(),Register.class);
    	startActivity(intent);
    }
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent intent = new Intent(getApplicationContext(), Setting.class);
    	    startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    
    
    
    public void Tooat(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();	
    }

     
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Process..."); // Calls onProgressUpdate()
            try {
                
            	String username=edit_username.getText().toString();
          		String password=edit_password.getText().toString(); 
          		
            	//Log.d("Come","1");
          		WebService1 web=new WebService1();
          		 resp=web.onLogin(username, password);
          		//Log.d("Come","2"+result);
          		
            	
            	
            	
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            
        	if(resp.contains("|"))
      		{
        		//Tooat(result);
      			String[] parts=result.split("\\|");
      			String userid=parts[0];
    	    	String type=parts[1];
    	    	Log.d("UserasdfasdfasdfasdfID   ","User ID : "+userid+" Type :"+type);
    	    	if(type.trim().equalsIgnoreCase("Admin"))
    	    	{
    	    		Intent intent = new Intent(getApplicationContext(), Admin_Panel.class);
    	    		//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	    	 	intent.putExtra("SESSION_ID", userid);
        	    	startActivity(intent);
        	    	//finish();
    	    	}
    	    	else
    	    	{
    	    		Intent intent = new Intent(getApplicationContext(), User_Panel.class);
    	    		//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	    	 	intent.putExtra("SESSION_ID", userid);
    	    	 	Config.UserID=userid;
    	    	 	Config.UserType="User";
        	    	startActivity(intent);
        	    	//finish();
    	    	}
      		}
      		else if(result.trim().equals("No"))
      		{
      			Tooat("Provide valid username and password !");
      		}
      		else
      		{
      			Tooat("Please check your connection !");
      		}
        	// execution of result of Long time consuming operation
            progressDialog.dismiss();
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Process",
                    "Please wait...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);
            
        }
    }
    
    
}
