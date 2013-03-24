package com.piit.lbp.form;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;


public class FaceTest extends Activity {
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        FaceView iv = new FaceView(this);
        setContentView(iv);
        if(FaceView.NO_FACE==1){
        	Toast.makeText(getBaseContext(), "No Faces Found, Please Try Again", Toast.LENGTH_LONG).show();
        }
        
    }
    
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			
	        Intent intent = new Intent("com.piit.lbp.form.LBPVECTORCALCULATE");
			startActivity(intent);
			finish();

		}
	        return true;

	}
    
}
