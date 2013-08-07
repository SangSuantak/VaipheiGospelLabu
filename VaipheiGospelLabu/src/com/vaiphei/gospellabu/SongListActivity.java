package com.vaiphei.gospellabu;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongListActivity extends ListActivity {
	
	private static int songBookId;
	private static String songBookName;
	
	private Song song;
	private DataAccess dataAccess;
	private List<Song> songs;
	private ArrayAdapter<Song> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song_list);
		
		Intent intent = getIntent();
		songBookId = intent.getIntExtra("SONG_BOOK_ID", 0);
		songBookName = intent.getStringExtra("SONG_BOOK_NAME");
		
		setTitle(songBookName);		
		setSongListAdapter();
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		// TODO Auto-generated method stub
		// super.onListItemClick(lv, v, position, id);
		song = adapter.getItem(position);
		//Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
		displaySong();
	}

	private void setSongListAdapter() {
		dataAccess = new DataAccess(this);
		songs = dataAccess.getSongs(songBookId);
		adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songs);
		setListAdapter(adapter);
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_list, menu);
		return true;
	}
	
	private void displaySong() {
		//Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, SongDisplayActivity.class);
		intent.putExtra("SONG_NUMBER", song.getSongnumber());
		intent.putExtra("SONG_BOOK_ID", songBookId);
		//Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
		startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataAccess.close();
	}

}
