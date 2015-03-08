package com.example.s2rspricepad;

import android.database.Cursor;

public class Item {
	private long id;
	private String itemName;
	private String category;
	private double price;
	public final String ITEMNAME = "itemName";
	public final String CATEGORY = "category";
	public final String PRICE = "price";
	
	public Item(Cursor c) {
		try{
			int idIndex = c.getColumnIndex(MySQLiteHelper.COLUMN_ID);
			int itemNameIndex = c.getColumnIndex(MySQLiteHelper.COLUMN_ItemName);
			int categoryIndex = c.getColumnIndex(MySQLiteHelper.COLUMN_Category);
			int priceIndex = c.getColumnIndex(MySQLiteHelper.COLUMN_Price);
			
			this.id = c.getLong(idIndex);
			this.itemName = c.getString(itemNameIndex);
			this.category = c.getString(categoryIndex);
			this.price = c.getDouble(priceIndex);
		}catch (Exception e) {
			
		}
	}
	public Item(){
		
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	public String getItemName(){
		return itemName;
	}
	public String getCategory() {
		return category;
	}
	public double getPrice() {
		return price;
	}
	public void setItemName(String name) {
		this.itemName = name;
	}
	public void setCategory(String cat) {
		this.category = cat;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String toString() {
		return itemName;
	}
}
