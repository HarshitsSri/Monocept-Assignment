package com.monocept.InventorySystem.model;

public class Product {
	private int id;
	private String name;
	private int quantity;
	private double price;
	private int reorderLevel;

	public Product(int id, String name, int quantity, double price, int reorderLevel) {
		if (quantity < 0 || price < 0 || reorderLevel < 0) {
	        throw new IllegalArgumentException("Values cannot be negative.");
	    }
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.reorderLevel = reorderLevel;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void addStock(int qty) {
		quantity += qty;
	}

	public void removeStock(int qty) {
		quantity -= qty;
	}

	public void display() {
		System.out.println("ID: " + id + ", Name: " + name + ", Qty: " + quantity + ", Price: " + price);
	}
}
