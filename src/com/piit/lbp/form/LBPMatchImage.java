package com.piit.lbp.form;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LBPMatchImage extends Activity {
    
    private static final String TAG = null;
	static int hist[] = new int[LBPOperatorTestingActivity.Histogram.length];
	private int foundCount =0;
	private int foundMatch =0;
		
	private OnClickListener mGoHomeListener = new OnClickListener()
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent("com.piit.lbp.form.LBPFORM");
			startActivity(intent);
			finish();
		}
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchimage);
        
        TextView tv = (TextView) findViewById(R.id.tv1);
        
        Button exit = (Button) findViewById(R.id.bn1);
        exit.setOnClickListener(mGoHomeListener); 

        int count=0,start=0,end = 0;
        String Hist2String;
        
        LBPData lbp = new LBPData(this);
        try {
			lbp.open();
			count = lbp.count();
			System.out.println(count);
			double chiSquareArray[] = new double[count];
			
			for(int i=0;i<count;i++)
			{
				Hist2String = lbp.getUri(i+1);
				Log.d(TAG, Hist2String);
				
				convertStringToArray(Hist2String);
				
				double chiSquare = 0.0;
		    	
		        long temp,temp1;
		        double temp2;
		     	start=0;
		     	end=0;
		     	for(int k=0;k<2;k++)
		     	{
		     	start=end;
		        end=end+256;
		         for(int j=start;j<end;j++)
		 		{
		     		

		     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
		 				continue;
		 			else
		 			{
		 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
		 			temp = temp*temp;
		 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
		 			temp2= temp/temp1;
		 			chiSquare = chiSquare + temp2;
		 			}
		     		

				}
		         start=end;
		         end=end+118;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + 2*temp2;
			 			}
			     		

					}

		         start=end;
		         end=end+118;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+59;
		         
		         start=end;
		         end=end+118;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + temp2;
			 			}
			     		

					}
		         
		         start=end;
		         end=end+59;
		         
		         start=end;
		         end=end+236;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + 4*temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+59;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+118;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + 3*temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+59;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+59;

		         start=end;
		         end=end+118;
		         for(int j=start;j<end;j++)
			 		{
			     		

			     		if(hist[j] == 0 && LBPOperatorTestingActivity.Histogram[j] == 0)
			 				continue;
			 			else
			 			{
			 			temp = hist[j]-LBPOperatorTestingActivity.Histogram[j];
			 			temp = temp*temp;
			 			temp1 = hist[j]+LBPOperatorTestingActivity.Histogram[j];
			 			temp2= temp/temp1;
			 			chiSquare = chiSquare + 2*temp2;
			 			}
			     		

					}
		         start=end;
		         end=end+59;
		     	}
		         chiSquareArray[i]=chiSquare;
				Log.d(TAG, "" +chiSquare);
				//chiSquare[i]=LBPOperatorTestingActivity.chiSquareClassifier(hist, LBPOperatorTestingActivity.Histogram);
				
			}
			
		
        
			
	        
			
			for(int i=0;i<chiSquareArray.length;i++){
				System.out.println("Similarity Measure with "+(i+1)+ " is " +chiSquareArray[i]);
			}
			for(int i=0;i<chiSquareArray.length;i++){
				if(chiSquareArray[foundMatch]>chiSquareArray[i])
	        		foundMatch  = i;
			}
			String name = lbp.getName(foundMatch+1);
			String dob = lbp.getDob(foundMatch+1);
			String rollno = lbp.getRollNo(foundMatch+1);
			lbp.close();
			
			
	        tv.setText("No of Rows in Database = " +count +
	        		"\nBased on Assumption that the subject is part of the Database" +
	        		"\n\n" +
	        		"\nNearest Match Found : \nRow ID = " + (foundMatch+1) + " :ChiSquare Value : "+ chiSquareArray[foundMatch] +
	        		"\n Name: " +name+
	        		"\n Date of Birth: " +dob+
	        		"\n Roll No: " +rollno);
			
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

       
    }
    public static int[] convertStringToArray(String str){
        String[] arr = str.split(",");

        for(int i=0;i<arr.length;i++){
        	hist[i]=Integer.parseInt(arr[i]);
        	//Log.d(TAG, " " + hist[i]);
        }
        
        return hist;
    }
}
