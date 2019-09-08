package com.example.newsapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.widget.ListView;
 
import com.java.adapters.Admin_PanelAdapter;
import com.java.models.Admin_PanelModel;

public class News_Feed extends ActionBarActivity{

	private WebView webView;
	public String decision;
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.news_feed);
	        setTitle("News Feed");
	        
	       
	        webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl("http://www.espncricinfo.com");

	}
	
}
