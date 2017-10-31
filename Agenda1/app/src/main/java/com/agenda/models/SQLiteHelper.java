package com.agenda.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class helps with database setup. None of its methods (except for the constructor)
 * should be called directly.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	
	// Table name
	public static final String TABLE_EVENTS = "events";
	
	// Table columns
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_START = "start";
	public static final String COLUMN_END = "end";
	public static final String COLUMN_DETAILS = "details";
	
	// Database name
	private static final String DATABASE_NAME = "agenda.db";
	
	// Increment this number to clear everything in database
	private static final int DATABASE_VERSION = 1;

	/**
	 * Returns an instance of this helper object given the activity
	 * @param context
	 */
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_EVENTS +
				" ( " +
				COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUMN_TITLE + " TEXT NOT NULL," +
				COLUMN_LOCATION + " TEXT NOT NULL," +
				COLUMN_START + " TEXT NOT NULL," +
				COLUMN_END + " TEXT NOT NULL," +
				COLUMN_DETAILS + " TEXT NOT NULL" +
		" ); ";
		db.execSQL(query);
		
	}

	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		onCreate(db);
	}

}