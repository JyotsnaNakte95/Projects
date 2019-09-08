package com.example.newsapp;

import com.java.config.Config;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class User_Panel extends ActionBarActivity {

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.user_panel);
        setTitle("User Panel");
        Initialized_Data();
    }
    
    public void onRecommended(View v)
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Recommended");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    public void onFavourite(View v)
    {
    	Intent intent =new Intent(getApplicationContext(),News_Feed.class);
    	intent.putExtra("NEWS_TYPE", "Favourite");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }

    
    public void onSport(View v)
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Sport");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    public void onTechnology(View v )
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Technology");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    
    public void onBusiness(View v )
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Business");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    public void onEntertainment(View v)
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Entertainment");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    public void onScience(View v )
    {
    	
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Science");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);
    }
    
    public void onHealth(View v)
    {
    	Intent intent =new Intent(getApplicationContext(),User_NewsList.class);
    	intent.putExtra("NEWS_TYPE", "Health");
    	intent.putExtra("SESSION_ID", Config.UserID);
    	startActivity(intent);	
    }
    
	private void Initialized_Data() {
		// TODO Auto-generated method stub
		
	}
	

    
    public void Tooat(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();	
    }
}
