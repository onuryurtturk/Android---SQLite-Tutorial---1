package com.example.sqlitetutorial;

public class Car {
	
	private long id;
	public void setId(long id) {
		this.id = id;
	}

	private String car;
	private String model;
	
	public long getId() {
		return id;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return car;
	}

}
