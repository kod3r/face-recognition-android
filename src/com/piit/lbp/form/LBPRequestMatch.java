package com.piit.lbp.form;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class LBPRequestMatch extends Activity {
	
	private static final int SELECT_PICTURE = 1;

	private static final String TAG = null;

	public static String selectedImagePath;
	private ImageView img1;

	public static Bitmap sourceImage;
	



	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.requestmatch);

	    img1 = (ImageView)findViewById(R.id.imageView1);

	    
	    ((Button) findViewById(R.id.button1))
	            .setOnClickListener(new OnClickListener() {

	                public void onClick(View arg0) {

	                    // in onCreate or any event where your want the user to
	                    // select a file
	                    Intent intent = new Intent();
	                    intent.setType("image/*");
	                    intent.setAction(Intent.ACTION_GET_CONTENT);
	                    startActivityForResult(Intent.createChooser(intent,
	                            "Select Picture"), SELECT_PICTURE);
	                }
	            });
	    ((Button) findViewById(R.id.button2))
        .setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

    			Intent intent = new Intent("com.piit.lbp.form.FACETEST");
    			startActivity(intent);
    			finish();
            }
        });
	    ((Button) findViewById(R.id.button3))
        .setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

    			Intent intent = new Intent("com.piit.lbp.form.CAMERACAPTURE");
    			startActivity(intent);
            }
        });
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        if (requestCode == SELECT_PICTURE) {
	            Uri selectedImageUri = data.getData();
	            selectedImagePath = getPath(selectedImageUri);
	            System.out.println("Image Path : " + selectedImagePath);
	            
		        //Decode image size
		        BitmapFactory.Options o = new BitmapFactory.Options();
		        o.inJustDecodeBounds = true;

		        BitmapFactory.decodeFile(selectedImagePath,o);

		        //The new size we want to scale to
		        final int REQUIRED_SIZE=500;

		        //Find the correct scale value. It should be the power of 2.
		        int scale=1;
		        while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
		            scale*=2;
		        Log.d(TAG," "+scale);
		        //Decode with inSampleSize
		        BitmapFactory.Options o2 = new BitmapFactory.Options();
		        o2.inSampleSize=scale;
		        o2.inPreferredConfig = Bitmap.Config.RGB_565;
		       sourceImage = BitmapFactory.decodeFile(selectedImagePath,o2);
	            
	            img1.setImageBitmap(sourceImage);


           
	        }
	    }
	}

	public String getPath(Uri uri) {
	    String[] projection = { MediaStore.Images.Media.DATA };
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}

}
