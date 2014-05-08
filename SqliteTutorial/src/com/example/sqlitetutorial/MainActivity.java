package com.example.sqlitetutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	
	private CarsDataSource dataSource;
	private final String[] Cars={"BMW","Mercedes","Audi","Toyota","Saab","Seat","Rolls Royce"};
	private final String[] Models={"Sedan","Hatchback","StationWagon","Notchback","Coupe","Convertible","Roadster","MPV","SUV"};
    private String SelectedCar="default";
	private	String SelectedModel="default";
	
	CustomList listadapter;
	ArrayAdapter <Car> adapter;
	ArrayList<Car> ShowMyCars= new ArrayList<Car>();
	Car car=new Car();
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dataSource=new CarsDataSource(this);
		dataSource.open();
		ShowMyCars=dataSource.getAllCars();

		setContentView(R.layout.activity_main);
		list=(ListView)findViewById(android.R.id.list);
		listadapter=new CustomList(this,ShowMyCars);
		/*Log.i("Array------------------","ASASAS"+
		ShowMyCars.get(0).getCar()+ShowMyCars.get(0).getModel());*/
		list.setAdapter(listadapter);
		
		
		
		
		
		/*ArrayAdapter<Car> adapter=new ArrayAdapter<Car>(this,android.R.layout.simple_list_item_1, ShowMyCars);
		setListAdapter(adapter);*/
	}



	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		// TODO Auto-generated method stub
		
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which)
				{
					case DialogInterface.BUTTON_POSITIVE:
						
					    car = listadapter.getItem(position);
				        dataSource.DeleteCar(car);
				        listadapter.remove(car);
						break;
					case DialogInterface.BUTTON_NEGATIVE:
						dialog.cancel();
				}
				// TODO Auto-generated method stub
				
			}
		};

		AlertDialog.Builder SelectionWindow3=new AlertDialog.Builder(this);
		SelectionWindow3.setTitle("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
		
		
		
	
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view)
	{
		
		
		switch(view.getId())
		{
			case R.id.add:
				
				AlertDialog.Builder SelectionWindow2=new AlertDialog.Builder(this);
				SelectionWindow2.setTitle("Select Car Model!");
				SelectionWindow2.setItems(Models, new OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int selectedItem)
					{
						SelectedModel=Models[selectedItem];
						Car car1=new Car();
						car1.setCar(SelectedCar);
						car1.setModel(SelectedModel);
						dataSource.createCar(SelectedCar, SelectedModel);						
						//Car car1=new Car(SelectedCar,SelectedModel);
						listadapter.insert(car1, 0);
					
						//adapter.insert(car, 0);
						//adapter.notifyDataSetChanged();
						listadapter.notifyDataSetChanged();
				

					}
				});
				SelectionWindow2.show();
				
				AlertDialog.Builder SelectionWindow1 = new AlertDialog.Builder(this);
				//AlertDialog.Builder SelectionWindow2;
				SelectionWindow1.setTitle("Select Car Brand!");
				SelectionWindow1.setItems(Cars, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int selectedItem2)
					{
						SelectedCar=Cars[selectedItem2];
					}
				});
				SelectionWindow1.show();
				
				
				
				break;
				
			 case R.id.delete:
			      if (listadapter.getCount() > 0)
			      {
			        car = listadapter.getItem(0);
			        dataSource.DeleteCar(car);
			        listadapter.remove(car);
			      }
			      break;
		}
		//	adapter.notifyDataSetChanged();
	}	

				
				
			
				
//				SelectionWindow2=new AlertDialog.Builder(this);
//				SelectionWindow2.setMessage("Select Car Model!");
//				
//				SelectionWindow1.setItems(Models, new DialogInterface.OnClickListener() 
//				{
//
//				    public void onClick(DialogInterface dialog, int SelectedItem2)
//				    {
//				    	SelectedModel=Models[SelectedItem2];
//				    }
//
//				});
//				SelectionWindow2.create().show();

			
		
		
		
		
		/*ArrayAdapter<String> carAdapter=new ArrayAdapter<String>
		(this,android.R.layout.simple_list_item_1,android.R.id.text1,Cars);*/
		
		
		
		
	}


