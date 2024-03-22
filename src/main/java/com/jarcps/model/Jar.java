package com.jarcps.model;


public class Jar {
	private int id;
    private String name;
    private double quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Jar(int id, String name, double quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

}
