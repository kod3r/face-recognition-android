package com.piit.lbp.form;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class LBPFormAdd extends Activity implements OnClickListener {

	EditText sqlName,sqlDOB,sqlRollNo,sqlClass, sqlUri;
	Button submitEntry,mPickDate;
	String branch;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	private long l;


	public static String histFeatureVector;


	static final int DATE_DIALOG_ID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrecords);
		

        
		Spinner spinner = (Spinner) findViewById(R.id.spClass);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        branch = (String) spinner.getSelectedItem();
        
        
        
        
        sqlName = (EditText) findViewById(R.id.etPersonName);
        sqlDOB = (EditText) findViewById(R.id.etDateOfBirth);
        sqlRollNo = (EditText) findViewById(R.id.etRollNo);
        submitEntry = (Button) findViewById(R.id.submit);
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);
	    
        if(LBPReadData.UPDATE_RECORDS == 1){
        	
        	l = Long.parseLong(LBPReadData.rowID);
        	LBPData upd = new LBPData(this);
        	try {
				upd.open();
			
        	sqlName.setText(upd.getName(l));
        	sqlDOB.setText(upd.getDob(l));
        	sqlRollNo.setText(upd.getRollNo(l));
        	
        	upd.close();
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        
        submitEntry.setOnClickListener(this);
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        

        
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);



	} 
	
	public void onClick(View arg0){
		
		boolean didItWork = true;
		try
		{
		String name = sqlName.getText().toString();
		String dob = sqlDOB.getText().toString();
		String rollno = sqlRollNo.getText().toString();
		
		LBPData entry = new LBPData(this);
		entry.open();
		if(LBPReadData.UPDATE_RECORDS == 1){
			LBPReadData.UPDATE_RECORDS = 0;
			String uripath = entry.getUri((int) l);
			entry.updateEntry(l, name, branch, rollno, dob, uripath);
		}
		else
			entry.newEntry(name, branch, rollno, dob, histFeatureVector);
		
		entry.close();
		}
		catch (Exception e)	{
			didItWork = false;
		}
		finally{
			if(didItWork)
			{
				Dialog d = new Dialog(this);
				d.setTitle("Success");
				TextView tv = new TextView(this);
				tv.setText("Data Added Successfully");
				d.setContentView(tv);
				d.show();
				Intent intent = new Intent("com.piit.lbp.form.LBPFORM");
				d.dismiss();
				startActivity(intent);
				finish();
			}
		}
	}
	
	private void updateDisplay() {
	    this.sqlDOB.setText(
	        new StringBuilder()
	                // Month is 0 based so add 1
            		.append(mDay).append("-")
	                .append(mMonth + 1).append("-")
	                .append(mYear).append(" "));
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		    new DatePickerDialog.OnDateSetListener() {
		        public void onDateSet(DatePicker view, int year, 
		                              int monthOfYear, int dayOfMonth) {
		            mYear = year;
		            mMonth = monthOfYear;
		            mDay = dayOfMonth;
		            updateDisplay();
		        }
		    };
    @Override
    protected Dialog onCreateDialog(int id) {
       switch (id) {
       case DATE_DIALOG_ID:
          return new DatePickerDialog(this,
                    mDateSetListener,
                    mYear, mMonth, mDay);
       }
       return null;
    }
    
	
	public static String convertArrayToString(int[] array){
	    String str = "";
	    for (int i = 0;i<array.length; i++) {
	        str = str+array[i];
	        // Do not append comma at the end of last element
	        if(i<array.length-1){
	            str = str+",";
	        }
	    }
	    return str;
	}




}
