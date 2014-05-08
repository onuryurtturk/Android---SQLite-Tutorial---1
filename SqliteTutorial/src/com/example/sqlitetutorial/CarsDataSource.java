package com.example.sqlitetutorial;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CarsDataSource {
	
	
	private SQLiteDatabase db;
	private MySqliteHelper dbHelper;
	private String[] allColumns={MySqliteHelper.COLUMN_ID,MySqliteHelper.COLUMN_CAR,MySqliteHelper.COLUMN_MODEL};
	Context context;
	public CarsDataSource(Context context)
	{
		this.context=context;
		dbHelper=new MySqliteHelper(context);
	}

	public void open()
	{
		try {
			db=dbHelper.getWritableDatabase();
		} catch (Exception e) {
			// TODO: handle exception
			dbHelper=new MySqliteHelper(context);
			db=dbHelper.getWritableDatabase();
		}
	}
	
	public Car createCar(String car,String model)
	{
		ContentValues values=new ContentValues();
		values.put(MySqliteHelper.COLUMN_CAR,car);
		values.put(MySqliteHelper.COLUMN_MODEL,model);
		
		long insertedId=db.insert(MySqliteHelper.TABLE_CARS, null, values);
		
		Cursor cursor =db.query(MySqliteHelper.TABLE_CARS, allColumns, MySqliteHelper.COLUMN_ID + " = " + insertedId,null,null,null,null);
		cursor.moveToFirst();
		Car newcar=CursorToCar(cursor);
		cursor.close();
				
		return newcar;		
		
	}

	private Car CursorToCar(Cursor cursor) {
		
		Car car=new Car();
		car.setId(cursor.getLong(0));
		car.setCar(cursor.getString(1));
		car.setModel(cursor.getString(2));
		
		// TODO Auto-generated method stub
		return car;
	}
	
	public void DeleteCar(Car car)
	{
		
		long id=car.getId();
        db.delete(MySqliteHelper.TABLE_CARS, MySqliteHelper.COLUMN_ID +"="+id,null);
        
	}
	
	public ArrayList<Car> getAllCars()
	{
		ArrayList<Car> cars=new ArrayList<Car>();
		Cursor cursor=db.query(MySqliteHelper.TABLE_CARS,allColumns,null,null,null,null,null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast())
		{
			Car car=CursorToCar(cursor);
			cars.add(car);
			cursor.moveToNext();
		}
		
		cursor.close();
		return cars;
	}
	
}
