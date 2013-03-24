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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LBPSelectImages extends Activity {
	
	private static final int SELECT_PICTURE = 1;
	int selectedimages=0;
	
	public static String selectedImagePath;
	String selectedImagePaths[] = new String[10];
	
	LinearLayout myLinearLayout;
	EditText et;
	
	private OnClickListener l = new OnClickListener()
	{
		
		public void onClick(View v) {
			// TODO Auto-generated method stub

			String temp = et.getText().toString();
			int count=Integer.parseInt(temp);
			
			final int N = count; // total number of textviews to add
			

			
			final TextView[] myTextViews = new TextView[N]; // create an empty array;

			for (int i = 0; i < N; i++) {
			    
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
                
                

			}
			System.out.println(selectedimages);
			if(selectedimages==count)
			{
			for (int i = 0; i < N; i++) {
			    // create a new textview
			    final TextView rowTextView = new TextView(LBPSelectImages.this);
			    
			    // set some properties of rowTextView or something
			    rowTextView.setText("Image #" + i +" saved to " + selectedImagePaths[i]);

			    // add the textview to the linearlayout
			    myLinearLayout.addView(rowTextView);

			    // save a reference to the textview for later
			    myTextViews[i] = rowTextView;
			}
			}
		}
			
			
		
	};
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        if (requestCode == SELECT_PICTURE) {
	            Uri selectedImageUri = data.getData();
	            selectedImagePath = getPath(selectedImageUri);
	            selectedImagePaths[selectedimages]=selectedImagePath;
	            Toast.makeText(getBaseContext(), selectedImagePath, Toast.LENGTH_LONG).show();
	            selectedimages++;
	            System.out.println("Image Path : " + selectedImagePath);
	   /*         
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
	            
	            img1.setImageBitmap(sourceImage); */


           
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectimages);
		

		myLinearLayout = (LinearLayout) findViewById(R.id.mylinearlayout);
		
		et = (EditText) findViewById(R.id.etNoOfRows);
				
		Button bn = new Button(this);
		bn = (Button) findViewById(R.id.bnNoOfRows);
		
		bn.setOnClickListener(l);
	}



}
