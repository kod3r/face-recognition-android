package com.piit.lbp.form;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LBPData {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "full_name";
	public static final String KEY_ROLLNO = "roll_number";
	public static final String KEY_DOB = "dob";
	public static final String KEY_BRANCH = "branch";
	public static final String KEY_URI = "uripath";
	
	private static final String DATABASE_NAME = "LBPDatadb";
	private static final String DATABASE_TABLE = "personalInfo";
	private static final int DATABASE_VERSION = 1;
	
	private DatabaseHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_BRANCH + " TEXT NOT NULL, " +
					KEY_ROLLNO + " TEXT NOT NULL, " +
					KEY_DOB + " TEXT NOT NULL, " +
					KEY_URI + " TEXT NOT NULL);"	
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE );
			onCreate(db);
		}
		
	}
	
	public LBPData (Context c)
	{
		ourContext = c;
	}
	
	public LBPData open() throws SQLException {
		ourHelper = new DatabaseHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long newEntry(String name, String branch, String rollno, String dob, String uripath) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_BRANCH, branch);
		cv.put(KEY_ROLLNO, rollno);
		cv.put(KEY_DOB, dob);
		cv.put(KEY_URI, uripath);
		return ourDatabase.insert(DATABASE_TABLE, null,cv);
	}
	public void updateEntry(long l, String name, String branch, String rollno, String dob, String uripath) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_BRANCH, branch);
		cv.put(KEY_ROLLNO, rollno);
		cv.put(KEY_DOB, dob);
		cv.put(KEY_URI, uripath);
		ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID +  "=" + l , null);
	}
	public String getData() throws SQLException{
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_BRANCH,KEY_ROLLNO, KEY_DOB, KEY_URI};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);		
		int iBranch = c.getColumnIndex(KEY_BRANCH);
		int iRollNo = c.getColumnIndex(KEY_ROLLNO);	
		int iDob = c.getColumnIndex(KEY_DOB);			
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + "\t" + c.getString(iName) + "\t" + c.getString(iBranch) + "\t" + c.getString(iRollNo) + "\t" + c.getString(iDob) + "\n";
		}
		
		
		return result;
	}
	public String getName(long l)throws SQLException{
			
		String query = "SELECT full_name FROM personalInfo WHERE _id = " +l;
		
		Cursor c = ourDatabase.rawQuery(query, null);
		if (c != null) {
	         c.moveToFirst();
	       }
	    return c.getString(c.getColumnIndex(KEY_NAME));
		
		

		
	}
	public String getClass(long l) throws SQLException{
		String query = "SELECT branch FROM personalInfo WHERE _id = " +l;
		
		Cursor c = ourDatabase.rawQuery(query, null);
		if (c != null) {
	         c.moveToFirst();
	       }
	    return c.getString(c.getColumnIndex(KEY_BRANCH));
		
	}
	public String getRollNo(long l) throws SQLException{
		String query = "SELECT roll_number FROM personalInfo WHERE _id = " +l;
		
		Cursor c = ourDatabase.rawQuery(query, null);
		if (c != null) {
	         c.moveToFirst();
	       }
	    return c.getString(c.getColumnIndex(KEY_ROLLNO));
		
	}
	public String getDob(long l) throws SQLException{
		String query = "SELECT dob FROM personalInfo WHERE _id = " +l;
		
		Cursor c = ourDatabase.rawQuery(query, null);
		if (c != null) {
	         c.moveToFirst();
	       }
	    return c.getString(c.getColumnIndex(KEY_DOB));
		
	}
	public String getUri(int l) throws SQLException{
		
		String query = "SELECT uripath FROM personalInfo WHERE _id = " +l;
		
		Cursor c = ourDatabase.rawQuery(query, null);
		if (c != null) {
            c.moveToFirst();
        }
        return c.getString(c.getColumnIndex(KEY_URI));
		
		
		
		/*
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_BRANCH,KEY_ROLLNO, KEY_DOB, KEY_URI};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		
		int iUri = c.getColumnIndex(KEY_URI);
		{
			c.moveToFirst();
			return c.getString(iUri);
		}
		*/
	}
	public int count()
	{
		String count = "SELECT count(*) FROM " +DATABASE_TABLE;
		Cursor mcursor = ourDatabase.rawQuery(count, null);
		mcursor.moveToFirst();
		int icount = mcursor.getInt(0);
		return icount;
	}

	public void deleteEntry(long lRow) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow, null);
	}

}
