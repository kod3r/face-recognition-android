package com.piit.lbp.form;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LBPReadData extends Activity implements OnClickListener {

	public static String rowID;
	
	EditText sqlRow;

	public static int UPDATE_RECORDS =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readdata);
		
		TextView tv = (TextView) findViewById(R.id.tvSqlView);
		
		sqlRow =(EditText) findViewById(R.id.editText1);
		
		LBPData info = new LBPData(this);
		
		try {
		info.open();
		String data = info.getData();
		info.close();
		
		tv.setMovementMethod(new ScrollingMovementMethod());

		tv.setText(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Button delData = (Button) findViewById(R.id.button2);
        delData.setOnClickListener(this);   


	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		

			rowID = sqlRow.getText().toString();
		//	long l = Long.parseLong(rowID);
			
			UPDATE_RECORDS = 1;
		/*	LBPData entry = new LBPData(this);
			try {
				entry.open();
				entry.deleteEntry(l);
				entry.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			Intent intent = new Intent("com.piit.lbp.form.LBPFORMADD");
			startActivity(intent); 
			finish();

	}

		
		
}
	    

		


	


	
			


