package com.piit.lbp.form;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LBPVectorCalculate extends Activity {
    
	TextView tv;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchimage);
        
        tv = (TextView) findViewById(R.id.tv1);
        tv.setText("Please Wait !!! Calculating Feature Vector");
        
        calculateFeature();
        
    }
    
    public void calculateFeature()
    {
    	Toast.makeText(getBaseContext(), "Calculating Feature Vector", Toast.LENGTH_SHORT).show();
        
		LBPOperatorTestingActivity.HistogramReset();
        LBPOperatorTestingActivity.featureVectorExtraction(FaceView.croppedImage);
        int Hist1[] = new int[LBPOperatorTestingActivity.Histogram.length];

        for(int i=0;i<LBPOperatorTestingActivity.Histogram.length;i++){
        	Hist1[i]=LBPOperatorTestingActivity.Histogram[i];
        	System.out.print(Hist1[i]+", ");
        }
        LBPFormAdd.histFeatureVector=LBPFormAdd.convertArrayToString(Hist1);

        Toast.makeText(getBaseContext(), "Feature Vector Calculated", Toast.LENGTH_SHORT).show();

        if(LBPForm.ADD_RECORD==1 && LBPForm.MATCH_IMAGE==0){
		LBPForm.ADD_RECORD=0;
		tv.setText("");
        Intent intent = new Intent("com.piit.lbp.form.LBPFORMADD");
		startActivity(intent);
		
        }
        if(LBPForm.ADD_RECORD==0 && LBPForm.MATCH_IMAGE==1){
			LBPForm.MATCH_IMAGE=0;
			tv.setText("");
	        Intent intent = new Intent("com.piit.lbp.form.LBPMATCHIMAGE");
			startActivity(intent);
			
        }
        else{
            tv = (TextView) findViewById(R.id.tv1);
            tv.setText("ADD_RECORD="+LBPForm.ADD_RECORD+";MATCH_IMAGE=;"+LBPForm.MATCH_IMAGE);
            		
        }
        	
        
    }
}
