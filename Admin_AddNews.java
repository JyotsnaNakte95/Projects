package com.example.newsapp;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
 
import com.java.adapters.Admin_PanelAdapter;
import com.java.models.Admin_PanelModel;

public class Admin_AddNews extends ActionBarActivity{
	private int PICK_IMAGE_REQUEST = 1;
	 Bitmap bitmap=null;
	private ImageView image_source;
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.admin_addnews);
	        setTitle("Add News");
	        
	        Initialize_Data();
	      //  listView = (ListView) findViewById(R.id.list);
	        
	      //List View Event
			//ListView_Event();
	        
	       // AsyncTaskRunner runner = new AsyncTaskRunner();
	  	//	runner.execute("Ok");
	      
	}
	
	
	private void Initialize_Data() {
		 
		image_source=(ImageView)findViewById(R.id.image_source);
	}

	public void Click_Save(View v)
	{
		
	}

	public void onImageClick(View v)
	{
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                image_source.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	
	public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
