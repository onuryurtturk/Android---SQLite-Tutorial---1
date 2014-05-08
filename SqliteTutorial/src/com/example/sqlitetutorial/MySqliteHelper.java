package com.example.sqlitetutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {

	
	
	 public static final String TABLE_CARS="cars";
	 public static final String COLUMN_ID="_id";
	 public static final String COLUMN_CAR="car";
	 public static final String COLUMN_MODEL="model";
	 
	private static final String DATABASE_NAME="cars.db";
	private static final int DATABASE_VERSION =1;
	
	private String DATABASE_CREATE="create table " + TABLE_CARS + " (" + COLUMN_ID + " integer primary key autoincrement, " +
COLUMN_CAR + " text not null,"+ COLUMN_MODEL + " text not null);";
	
	
	public MySqliteHelper(Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySqliteHelper.class.getName(), "Old version :" + oldVersion + "New Version :" + newVersion + ", old datas will be removed" );
		db.execSQL("DROP TABLE IF EXISTS" +TABLE_CARS);
		onCreate(db);		
		
	}
	

}
