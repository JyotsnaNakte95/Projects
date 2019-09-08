package com.example.newsapp;

import com.java.config.Config;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class Setting extends ActionBarActivity{

private EditText edit_username;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.setting);
	        setTitle("Admin Panel");
	        
	        edit_username=(EditText)findViewById(R.id.edit_username);
	        edit_username.setText(Config.IP);
 	}
	
	
	public void onLogin(View v)
    {
		String Ip=edit_username.getText().toString();
		Config.IP=Ip;
		finish();
    }
	  

	
	
}
