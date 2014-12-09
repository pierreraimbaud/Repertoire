package com.example.tp1;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO {

	// Database fields
	private SQLiteDatabase database;
	private RepertoireSQLiteHelper dbHelper;
	private String[] allColumns = { RepertoireSQLiteHelper.COLUMN_ID,
			RepertoireSQLiteHelper.COLUMN_NOM ,RepertoireSQLiteHelper.COLUMN_PRENOM,
			RepertoireSQLiteHelper.COLUMN_DATE,
	RepertoireSQLiteHelper.COLUMN_VILLE };

	public DAO(Context context) {
		dbHelper = new RepertoireSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Repertoire createRepertoire(Repertoire repertoire) {
		ContentValues values = new ContentValues();
		values.put(RepertoireSQLiteHelper.COLUMN_NOM, repertoire.getNom());
		values.put(RepertoireSQLiteHelper.COLUMN_PRENOM, repertoire.getPrenom());
		values.put(RepertoireSQLiteHelper.COLUMN_DATE, repertoire.getDate());
		values.put(RepertoireSQLiteHelper.COLUMN_VILLE, repertoire.getVille());
		long insertId = database.insert(RepertoireSQLiteHelper.TABLE_REPERTOIRES, null,
				values);
		Cursor cursor = database.query(RepertoireSQLiteHelper.TABLE_REPERTOIRES,
				allColumns, RepertoireSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Repertoire newComment = cursorToRepertoire(cursor);
		cursor.close();
		return newComment;
	}

	public void deleteComment(Repertoire repertoire) {
		long id = repertoire.getId();
		System.out.println("Client deleted with id: " + id);
		database.delete(RepertoireSQLiteHelper.TABLE_REPERTOIRES, RepertoireSQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Repertoire> getAllRepertoires() {
		List<Repertoire> repertoires = new ArrayList<Repertoire>();

		Cursor cursor = database.query(RepertoireSQLiteHelper.TABLE_REPERTOIRES,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Repertoire repertoire = cursorToRepertoire(cursor);
			repertoires.add(repertoire);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return repertoires;
	}

	private Repertoire cursorToRepertoire(Cursor cursor) {
		Repertoire repertoire = new Repertoire();
		repertoire.setId(cursor.getLong(0));
		repertoire.setNom(cursor.getString(1));
		repertoire.setPrenom(cursor.getString(2));
		repertoire.setDate(cursor.getString(3));
		repertoire.setVille(cursor.getString(4));
		return repertoire;
	}
}