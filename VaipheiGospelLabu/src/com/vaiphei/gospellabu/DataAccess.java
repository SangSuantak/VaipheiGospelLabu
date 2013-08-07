package com.vaiphei.gospellabu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataAccess extends SQLiteAssetHelper {
	
	private static final String DATABASE_NAME = "vaipheilabu";
	private static final int DATABASE_VERSION = 1;
	
	public DataAccess(Context context) {		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}
	

	public List<SongBook> getSongBooks(){
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String[] sqlSelect = {"_id", "name", "description"};
		String sqlTables = "songbooks";
		
		qb.setTables(sqlTables);
		Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
		cursor.moveToFirst();
		
		List<SongBook> songBooks = new ArrayList<SongBook>();
		while(!cursor.isAfterLast()){
			SongBook songBook = cursorToSongBook(cursor);
			songBooks.add(songBook);
			cursor.moveToNext();
		}
		cursor.close();		
		return songBooks;
	}	
	
	public List<Song> getSongs(int songbookid){
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT _id, name, songnumber"
				+ " FROM songs "
				+ " WHERE songbookid = " + songbookid
				+ " ORDER BY name";
				
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		List<Song> songs = new ArrayList<Song>();
		while(!cursor.isAfterLast()){
			Song song = cursorToSong(cursor);
			songs.add(song);
			cursor.moveToNext();
		}
		cursor.close();		
		return songs;
	}
	
	public List<Song> getSongs(int songbookid, String songFirstChar){
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT _id, name, songnumber"
				+ " FROM songs "
				+ " WHERE songbookid = " + songbookid 
				+ " AND substr(name, 1, 1) = '" + songFirstChar + "'"
				+ " ORDER BY name";
				
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		List<Song> songs = new ArrayList<Song>();
		while(!cursor.isAfterLast()){
			Song song = cursorToSong(cursor);
			songs.add(song);
			cursor.moveToNext();
		}
		cursor.close();		
		return songs;
	}
	
	public Song getSong(int songid){
		SQLiteDatabase db = getReadableDatabase();
		String query = "SELECT a._id, a.name, songnumber, lyric, a.songbookid, b.name AS songbookname, IFNULL(c.name,'') AS writername"
				+ " FROM songs a INNER JOIN songbooks b ON a.songbookid = b._id"
				+ " LEFT JOIN writers c ON a.writerid = c._id"
				+ " WHERE a._id = " + songid;
		
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		Song song = new Song();
		song.setId(cursor.getInt(0));
		song.setName(cursor.getString(1));
		song.setSongnumber(cursor.getInt(2));
		song.setLyric(cursor.getString(3));
		song.setSongbookid(cursor.getInt(4));
		song.setSongbookname(cursor.getString(5));
		song.setWritername(cursor.getString(6));
		cursor.close();		
		return song;		
	}
	
	public int getSongIdByNumber(int songNumber, int songBookId){
		SQLiteDatabase db = getReadableDatabase();
		String query = "SELECT _id "
				+ "FROM songs "
				+ "WHERE songnumber = " + songNumber + " AND songbookid = " + songBookId;
		
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		int songId = cursor.getInt(0);		
		cursor.close();		
		return songId;		
	}
	
	public Song getSongByNumber(int songNumber, int songBookId){
		SQLiteDatabase db = getReadableDatabase();
		String query = "SELECT a._id, a.name, songnumber, lyric, a.songbookid, b.name AS songbookname, IFNULL(c.name,'') AS writername"
				+ " FROM songs a INNER JOIN songbooks b ON a.songbookid = b._id"
				+ " LEFT JOIN writers c ON a.writerid = c._id"
				+ " WHERE a.songnumber = " + songNumber + " AND a.songbookid=" + songBookId;
		
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		Song song = new Song();
		song.setId(cursor.getInt(0));
		song.setName(cursor.getString(1));
		song.setSongnumber(cursor.getInt(2));
		song.setLyric(cursor.getString(3));
		song.setSongbookid(cursor.getInt(4));
		song.setSongbookname(cursor.getString(5));
		song.setWritername(cursor.getString(6));
		cursor.close();		
		return song;		
	}
	
	public List<Song> getMatchingSongs(String queryToMatch){
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT _id, name, songnumber "
				+ "FROM songs "
				+ "WHERE name LIKE '%" + queryToMatch + "%' "
				+ "ORDER BY name";
				
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		List<Song> songs = new ArrayList<Song>();
		while(!cursor.isAfterLast()){
			Song song = cursorToSong(cursor);
			songs.add(song);
			cursor.moveToNext();
		}
		cursor.close();		
		return songs;
	}
	
	public List<SongFirstChar> getSongFirstChars(int songBookId) {
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT substr(name, 1, 1) AS FirstChar, songbookid, COUNT(substr(name,1,1)) AS Occurence"
				+ " FROM songs WHERE songbookid = " + songBookId
				+ " GROUP BY substr(name, 1,1) ORDER BY substr(name, 1,1);";
				
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		List<SongFirstChar> songFirstChars = new ArrayList<SongFirstChar>();
		while(!cursor.isAfterLast()){
			SongFirstChar objSongFirstChar = cursorToSongFirstChar(cursor);
			songFirstChars.add(objSongFirstChar);
			cursor.moveToNext();
		}
		cursor.close();		
		return songFirstChars;
	}
	
	public int getSongCount(int songBookId) {
		SQLiteDatabase db = getReadableDatabase();
		
		String query = "SELECT count(_id) AS SongCount FROM songs where songbookid=" + songBookId;
				
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		
		int songCount = cursor.getInt(0);		
		cursor.close();		
		return songCount;
	}
	
	public SongBook cursorToSongBook(Cursor cursor){
		SongBook songBook = new SongBook();
		songBook.setId(cursor.getInt(0));
		songBook.setName(cursor.getString(1));
		return songBook;
	}
	
	public Song cursorToSong(Cursor cursor){
		Song song = new Song();
		song.setId(cursor.getInt(0));
		song.setName(cursor.getString(1));
		song.setSongnumber(cursor.getInt(2));
		return song;
	}
	
	public SongFirstChar cursorToSongFirstChar(Cursor cursor) {
		SongFirstChar objFirstChar = new SongFirstChar();
		objFirstChar.setSongFirstChar(cursor.getString(0));
		objFirstChar.setSongBookId(cursor.getInt(1));		
		objFirstChar.setSongCount(cursor.getInt(2));
		return objFirstChar;
	}	

}
