package com.example.sqlitetutorial;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<Car>{
	
	private final Activity context;
   private ArrayList<Car> array1;
	
	public CustomList(Activity context,ArrayList<Car> array)
	{
		super(context,R.layout.list_single,array);
		this.context=context;
		this.array1=array;
	}
	@Override
	public View getView(int position,View view, ViewGroup parent)
	{
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.list_single,null,true);
		TextView txtTitle=(TextView)rowView.findViewById(R.id.textView1);
		TextView txtsub=(TextView)rowView.findViewById(R.id.textView2);
		txtTitle.setText(array1.get(position).getCar());
		txtsub.setText(array1.get(position).getModel());
	
		
		return rowView;		
	}

}