package com.java.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.java.config.Config;
import com.java.models.*;
public class Admin_PanelAdapter extends BaseAdapter{
	private Activity activity;
	private LayoutInflater inflater;
	List<Admin_PanelModel> pendingItems;
	
	   public Admin_PanelAdapter(Activity activity, List<Admin_PanelModel> pendingItems) {
	        this.activity = activity;
	        this.pendingItems = pendingItems;
	    }
	   
	 public View getView(int position, View convertView, ViewGroup parent) {
		 
	        if (inflater == null)
	            inflater = (LayoutInflater) activity
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null)
	            convertView = inflater.inflate(R.layout.list_row, null);
	 
	      //  if (imageLoader == null)
	        //    imageLoader = AppController.getInstance().getImageLoader();
	        ImageView thumbNail = (ImageView) convertView
	                .findViewById(R.id.list_image);
	        TextView title = (TextView) convertView.findViewById(R.id.line1);
	        TextView rating = (TextView) convertView.findViewById(R.id.line2);
	        TextView genre = (TextView) convertView.findViewById(R.id.line3);
	       // TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
	 
	        // getting movie data for the row
	        Admin_PanelModel m = pendingItems.get(position);
	 


		
			Bitmap bmp;
			try {
			//	URL url = new URL(Config.IP+"/personal_news/uploads/"+m.getImage_Location());
			//	bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			//	thumbNail.setImageBitmap(bmp);
				new DownloadImageTask(thumbNail).execute(Config.IP+"/personal_news/uploads/"+m.getImage_Location());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


	        // thumbnail image
	      //  thumbNail.setImageUrl(m.getImage_Location());
	        //thumbNail.setImageResource(R.drawable.car_icon);
	        // title
	        title.setText(m.getTitle()); 
	         
	        // rating
	        rating.setText("" + m.getNew_Source());
	          
	        // genre
	        //String genreStr = "Pending ";
	         
	       
	      
	        genre.setText(m.getShort_Details());
	         
	        // release year
	        //year.setText(""+2016);
	 
	        return convertView;
	    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return pendingItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
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
