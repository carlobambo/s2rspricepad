package com.example.s2rspricepad;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ItemsDataSource {
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {
		MySQLiteHelper.COLUMN_ID,
		MySQLiteHelper.COLUMN_ItemName,
		MySQLiteHelper.COLUMN_Category,
		MySQLiteHelper.COLUMN_Price
	};
	
	public ItemsDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Item createItem(String itemName, String category, int price) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_Category, category);
		values.put(MySQLiteHelper.COLUMN_ItemName, itemName);
		values.put(MySQLiteHelper.COLUMN_Price, price);
		long insertId = db.insert(MySQLiteHelper.TBL_ITEMS, null, values);
		
		Cursor cursor = db.query(MySQLiteHelper.TBL_ITEMS, 
				allColumns, MySQLiteHelper.COLUMN_ID + "=" + insertId, null,
				null,null,null);
		
		cursor.moveToFirst();
		Item newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}
	
	private Item cursorToItem(Cursor c){
		Item item = new Item(c);
		return item;
	}
	
	public List<Item> getAllItems() {
		List <Item> items = new ArrayList<Item>();
		
		Cursor cursor = db.query(MySQLiteHelper.TBL_ITEMS,
		        allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Item item = new Item(cursor);
			items.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return items;
	}
	public List<Item> searchItems() {
		return null;
	}
	
}
