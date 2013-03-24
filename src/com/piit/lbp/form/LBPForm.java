package com.piit.lbp.form;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;


public class LBPForm extends Activity{
    /** Called when the activity is first created. */
	
	
	public static int MATCH_IMAGE=0;
	public static int ADD_RECORD=0;
	public static int TAKE_PICTURE=0;

	
	private OnClickListener mAddFormListener = new OnClickListener()
	{
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MATCH_IMAGE=0;
			ADD_RECORD=1;
			
			Intent intent = new Intent("com.piit.lbp.form.LBPREQUESTMATCH");
			startActivity(intent);
			
			
		}
	};
	private OnClickListener mRequestMatchListener = new OnClickListener()
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ADD_RECORD=0;
			MATCH_IMAGE=1;
			
			Intent intent = new Intent("com.piit.lbp.form.LBPREQUESTMATCH");
			startActivity(intent);
			
		}
	};	
	
	private OnClickListener mReadDataListener = new OnClickListener()
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent("com.piit.lbp.form.LBPREADDATA");
			startActivity(intent);
			
		}
	};	
	
	private OnClickListener mCameraTestListener = new OnClickListener()
	{
		

		public void onClick(View v) {
			// TODO Auto-generated method stub
			TAKE_PICTURE =1;
			Intent intent = new Intent("com.piit.lbp.form.CAMERACAPTURE");
			startActivity(intent);
			
		}
	};	


    		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainform);
        

        Button reqMatch = (Button) findViewById(R.id.requestMatch);
        reqMatch.setOnClickListener(mRequestMatchListener);        
 
        Button addRec = (Button) findViewById(R.id.addRecords);
        addRec.setOnClickListener(mAddFormListener);
        
        Button rdData = (Button) findViewById(R.id.readData);
        rdData.setOnClickListener(mReadDataListener);        
        
        Button camTest = (Button) findViewById(R.id.cameraTest);
        camTest.setOnClickListener(mCameraTestListener);       

       
    }
}