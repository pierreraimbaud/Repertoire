package com.example.tp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RepertoireSQLiteHelper  extends SQLiteOpenHelper {

	public static final String TABLE_REPERTOIRES = "repertoire";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOM = "nom";
	public static final String COLUMN_PRENOM = "prenom";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_VILLE = "ville";

	private static final String DATABASE_NAME = "repertoire.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_REPERTOIRES + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NOM
			+ " text not null, " + COLUMN_PRENOM + " text not null, "
			+ COLUMN_DATE + " text not null, "
			+ COLUMN_VILLE + " text not null);";

	public RepertoireSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(RepertoireSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPERTOIRES);
		onCreate(db);
	}
}