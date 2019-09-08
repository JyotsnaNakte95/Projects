package com.example.newsapp;

import java.util.ArrayList;
  
import com.java.config.Config;
import com.java.services.WebService1;

 
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class Register extends Activity{
	
	String FULLNAME,USERNAME,EMAIL,FAVOURITE,MOBILE,PASSWORD,GENDER;
	private EditText edit_fullname;
	private EditText edit_username;
	private EditText edit_email;
	private Spinner  spinn_usertype; 
	private EditText edit_Password; 
	private EditText mobile;
	private RadioButton radio_male;
	private RadioButton radio_female; 
	
	ArrayList<String> spinnerArray = new ArrayList<String>();
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        
	  	 
	        
			//Intiation
	        edit_fullname=(EditText)findViewById(R.id.edit_fullname);

	        edit_username=(EditText)findViewById(R.id.edit_username);

	        edit_Password=(EditText)findViewById(R.id.edit_Password);
	        
	        edit_email=(EditText)findViewById(R.id.edit_email);

	        mobile=(EditText)findViewById(R.id.mobile);
	        
	        radio_male=(RadioButton)findViewById(R.id.male);

	        radio_female=(RadioButton)findViewById(R.id.female);
	        
	        spinnerArray=fill();
	        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
	        spinn_usertype=(Spinner)findViewById(R.id.spinn_usertype);
	        spinn_usertype.setAdapter(spinnerArrayAdapter);   
	        
	    }
	 
	 
	 
	 public void onSubmit(View v)
	 {
	
		 String fullname=edit_fullname.getText().toString();
		 String username=edit_username.getText().toString();
		 String Password=edit_Password.getText().toString();
		 String email=edit_email.getText().toString();
		 String usertype=spinn_usertype.getSelectedItem().toString(); 
		 boolean strfemale=radio_female.isChecked();
	     boolean strmale=radio_male.isChecked();
	     String strmobile=mobile.getText().toString();
	     String gender="";
         if(strfemale==true)
         {
            gender="female";
         }
         if(strmale==true)
         {
            gender="male";
         }
		 if(Validate()==0)
		 {
			 
			 FULLNAME=fullname;
			 USERNAME=username;
			 PASSWORD=Password;
			 EMAIL=email;
			 FAVOURITE=usertype;
			 GENDER=gender;
			 MOBILE=strmobile;
			 
			 AsyncTaskRunner runner = new AsyncTaskRunner();
			 runner.execute("Ok");
		 }
		 
	 }
	
	 public int Validate()
	 {
		 int furthur=0;
		 String fullname=edit_fullname.getText().toString();
		 String username=edit_username.getText().toString();
		 String Password=edit_Password.getText().toString();
		 String email=edit_email.getText().toString();
		 String usertype=spinn_usertype.getSelectedItem().toString(); 
		 
		 if(fullname.trim().equals(""))
		 {
			 Tooat("Full Name please");
			 furthur=1;
		 }
		 if(username.trim().equals("") && furthur==0)
		 {
			 Tooat("Username please");
			 furthur=1;
		 } 
		 if(Password.trim().equals("")&& furthur==0 )
		 {
			 Tooat("Password please");
			 furthur=1;
		 }
		 if(email.trim().equals("")&& furthur==0)
		 {
			 Tooat("Email please");
			 furthur=1;
		 }
		 if(usertype.equals("")&& furthur==0)
		 {
			 Tooat("User Type please");
			 furthur=1;
		 }
		 
		 return furthur;
	 }
	
	 public  ArrayList<String> fill()
    {
    	 spinnerArray.add("Sports");
         spinnerArray.add("Technology");
         spinnerArray.add("Business");   
         spinnerArray.add("Entertainment");   
         spinnerArray.add("Science");   
         spinnerArray.add("Health");
         
         return spinnerArray;
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
	              
	            	//Log.d("Come","1");
	          		WebService1 web=new WebService1();
	          		//news_detail=web.getSingleNews(NEWS_ID);
	          		
	          		 resp=web.onRegister(FULLNAME,USERNAME,PASSWORD,EMAIL,MOBILE,GENDER,FAVOURITE);
	          		//Log.d("Come","2"+result);
	          		
	            	
	            	
	            	
	            } catch (Exception e) {
	                e.printStackTrace();
	                resp = e.getMessage();
	            }
	            return resp;
	        }


	        @Override
	        protected void onPostExecute(String result) {
	            
	        	if(resp.trim().equalsIgnoreCase("Added"))
    	    	{
	        		Tooat("You have successfully registered ");
          			finish();
    	    	}
	        	else
	        	{
	        		Tooat(resp);
	        	}
	        	// execution of result of Long time consuming operation
	            progressDialog.dismiss();
	            //finalResult.setText(result);
	        }


	        @Override
	        protected void onPreExecute() {
	            progressDialog = ProgressDialog.show(Register.this,
	                    "Process",
	                    "Please wait...");
	        }


	        @Override
	        protected void onProgressUpdate(String... text) {
	            //finalResult.setText(text[0]);
	            
	        }
	    }
		
	 
	 
}
