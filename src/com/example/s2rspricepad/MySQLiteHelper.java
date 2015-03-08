package com.example.s2rspricepad;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TBL_ITEMS = "items";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ItemName = "itemName";
	public static final String COLUMN_Category = "category";
	public static final String COLUMN_Price = "price";
	
	private static final String DATABASE_NAME = "s2re.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DB_CREATE = "create table "
			+ TBL_ITEMS + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_ItemName + " text not null, "
			+ COLUMN_Category + " text, "
			+ COLUMN_Price + " real"
			+ ");";
	
	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private String insertString(String itemName, String category, double price) {
		return "INSERT INTO " + TBL_ITEMS 
				+ " ("
					+ COLUMN_ItemName + ", "
					+ COLUMN_Category + ", "
					+ COLUMN_Price
				+ ")"
				+ " VALUES ('"
					+ itemName + "', '"
					+ category + "', "
					+ price + ");"
				;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DB_CREATE);
		db.execSQL(insertString("V-Cut", "Junk Food", 13));
		db.execSQL(insertString("Cream-O", "Biscuit", 7));
		db.execSQL(insertString("Tinapay", "bread", 6));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(),
		    "Upgrading database from version " + oldVersion + " to "
		    + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TBL_ITEMS);
	    onCreate(db);
	}
}
