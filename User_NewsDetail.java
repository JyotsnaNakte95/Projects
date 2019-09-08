package com.example.newsapp;

import java.io.InputStream;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
  
import com.java.config.Config;
import com.java.models.Admin_PanelModel;
import com.java.services.WebService1;

public class User_NewsDetail extends ActionBarActivity{

Admin_PanelModel news_detail;
String NEWS_ID;
	
private TextView news_title;
private TextView news_source;
private TextView news_details;
private ImageView image_source;
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.user_detailview);
	        setTitle("Admin Panel");
	        
	        Initialized_Data();
	        AsyncTaskRunner runner = new AsyncTaskRunner();
	  		runner.execute("Ok");
 	}

	private void Initialized_Data() {
		news_title=(TextView)findViewById(R.id.news_title);
		news_source=(TextView)findViewById(R.id.news_source);
		news_details=(TextView)findViewById(R.id.news_details);
		image_source=(ImageView)findViewById(R.id.image_source);
		
		NEWS_ID="";
		Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	NEWS_ID= extras.getString("NEWS_ID");
        }
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
          		news_detail=web.getSingleNews(NEWS_ID,Config.UserID);
          		
          	//	 resp=web.onLogin(username, password);
          		//Log.d("Come","2"+result);
          		
            	
            	
            	
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            
        	 if(news_detail!=null)
        	 {
        		 news_title.setText(news_detail.getTitle());
        		 news_source.setText(news_detail.getNew_Source());
        		 news_details.setText(news_detail.getShort_Details());
        		 
        			Bitmap bmp;
        			try {
        			//	URL url = new URL(Config.IP+"/personal_news/uploads/"+m.getImage_Location());
        			//	bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        			//	thumbNail.setImageBitmap(bmp);
        				new DownloadImageTask(image_source).execute(Config.IP+"/personal_news/uploads/"+news_detail.getImage_Location());
        			} catch (Exception e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        	 }
        	// execution of result of Long time consuming operation
            progressDialog.dismiss();
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(User_NewsDetail.this,
                    "Process",
                    "Please wait...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);
            
        }
    }
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}
}
