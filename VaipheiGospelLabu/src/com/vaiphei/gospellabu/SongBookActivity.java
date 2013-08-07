package com.vaiphei.gospellabu;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongBookActivity extends ListActivity {

	private DataAccess dataAccess;
	private List<SongBook> songBooks;
	private ArrayAdapter<SongBook> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dataAccess = new DataAccess(this);		
		songBooks = dataAccess.getSongBooks();		
		adapter = new ArrayAdapter<SongBook>(this, android.R.layout.simple_list_item_1, songBooks);		
		setListAdapter(adapter);
		
		//setContentView(R.layout.activity_song_book);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataAccess.close();
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		SongBook songBook = adapter.getItem(position);
		Intent intent = new Intent(this, SongListActivity.class);
		intent.putExtra("SONG_BOOK_ID", songBook.getId());
		intent.putExtra("SONG_BOOK_NAME", songBook.getName());
		//super.onListItemClick(lv, v, position, id);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_book, menu);
		return true;
	}

}
