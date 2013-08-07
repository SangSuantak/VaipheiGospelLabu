package com.vaiphei.gospellabu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class SongDisplayActivity extends FragmentActivity {

	public static DataAccess dataAccess;
	private Song song;
	private Boolean isInfoDisplayed = false;
	//private Boolean isInfoBound = false;
	
	private View lyricHolder;
	private View infoHolder;
	
	private static int NUM_SONGS;
	
	private ViewPager sPager;
	private PagerAdapter sPagerAdapter;
	
	private int songNumber;
	private int songBookId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song_display);
						
		lyricHolder = (View) findViewById(R.id.lyricHolder);
		infoHolder = (View) findViewById(R.id.infoHolder);
		
		//sPager = (ViewPager) findViewById(R.id.pager);
		//sPagerAdapter = new SongSlidePagerAdapter(getSupportFragmentManager());
		//sPager.setAdapter(sPagerAdapter);
				
		displaySong(getIntent());
		
		sPager = (ViewPager) findViewById(R.id.pager);
		sPagerAdapter = new SongSlidePagerAdapter(getSupportFragmentManager(), this);
		sPager.setAdapter(sPagerAdapter);
	}
	
	protected void displaySong(Intent intent) {
		//int songId = intent.getIntExtra("SONG_ID", 0);
		songNumber = intent.getIntExtra("SONG_NUMBER", 0);
		songBookId = intent.getIntExtra("SONG_BOOK_ID", 0);
		//Toast.makeText(this, "Song display: " + songNumber + ", " + songBookId, Toast.LENGTH_SHORT).show();
		dataAccess = new DataAccess(this);
		//song = dataAccess.getSong(songId);
		NUM_SONGS = dataAccess.getSongCount(songBookId);
		//Toast.makeText(this, "Song Count: " + NUM_SONGS, Toast.LENGTH_SHORT).show();
		//song = dataAccess.getSongByNumber(songNumber, songBookId);
		//TextView txtLyric = (TextView) findViewById(R.id.txtSongLyric);
		//txtLyric.setText(Html.fromHtml(song.getLyric()));		
		//setTitle(song.getSongnumber() + ". " + song.getName());	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.song_display, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataAccess.close();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_info:
				toggleInfoDisplay();
				return true;
			default:
				return false;
		}		
	}
	
	private void toggleInfoDisplay() {		
		if(isInfoDisplayed) {
			infoHolder.setVisibility(View.GONE);
			lyricHolder.setVisibility(View.VISIBLE);
			isInfoDisplayed = false;
		}
		else {
//			if(!isInfoBound) {
//				bindSongInfo();
//			}
			infoHolder.setVisibility(View.VISIBLE);
			lyricHolder.setVisibility(View.GONE);
			isInfoDisplayed = true;
		}
	}
	
//	private void bindSongInfo() {
//		TextView txtSongBookName = (TextView) findViewById(R.id.txtSongBookName);
//		TextView txtSongWriter = (TextView) findViewById(R.id.txtSongWriter);
//		txtSongBookName.setText(song.getSongbookname());
//		txtSongWriter.setText(song.getWritername());
//		isInfoBound = true;
//		//Toast.makeText(this, "Song Info Bound: " + song.getSongbookname() + ", " + song.getWritername(), Toast.LENGTH_SHORT).show();
//	}
	
	private class SongSlidePagerAdapter extends FragmentStatePagerAdapter {
		Context ctx;
		public SongSlidePagerAdapter(FragmentManager fm, Context ctx) {
			super(fm);
			this.ctx = ctx;
		}				

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Toast.makeText(ctx, "Song Position: " + position, Toast.LENGTH_SHORT).show();			
			return SongFragment.create(position, fetchSong());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return NUM_SONGS;
		}
		
		public Song fetchSong() {			
			song = dataAccess.getSongByNumber(songNumber, songBookId);
			songNumber +=1;
			return song;
		}
		
	}

}

//public class SongDisplayActivity extends Activity {
//
//	private DataAccess dataAccess;
//	private Song song;
//	private Boolean isInfoDisplayed = false;
//	private Boolean isInfoBound = false;
//	
//	private View lyricHolder;
//	private View infoHolder;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_song_display);
//		
//		lyricHolder = (View) findViewById(R.id.lyricHolder);
//		infoHolder = (View) findViewById(R.id.infoHolder);
//		
//		displaySong(getIntent());
//	}
//
//	protected void displaySong(Intent intent) {
//		int songId = intent.getIntExtra("SONG_ID", 0);
//		dataAccess = new DataAccess(this);
//		song = dataAccess.getSong(songId);
//		TextView txtLyric = (TextView) findViewById(R.id.txtSongLyric);
//		txtLyric.setText(Html.fromHtml(song.getLyric()));		
//		setTitle(song.getSongnumber() + ". " + song.getName());	
//	}
//	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.song_display, menu);
//		return true;
//	}
//	
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		dataAccess.close();
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//			case R.id.action_info:
//				toggleInfoDisplay();
//				return true;
//			default:
//				return false;
//		}		
//	}
//	
//	private void toggleInfoDisplay() {		
//		if(isInfoDisplayed) {
//			infoHolder.setVisibility(View.GONE);
//			lyricHolder.setVisibility(View.VISIBLE);
//			isInfoDisplayed = false;
//		}
//		else {
//			if(!isInfoBound) {
//				bindSongInfo();
//			}
//			infoHolder.setVisibility(View.VISIBLE);
//			lyricHolder.setVisibility(View.GONE);
//			isInfoDisplayed = true;
//		}
//	}
//	
//	private void bindSongInfo() {
//		TextView txtSongBookName = (TextView) findViewById(R.id.txtSongBookName);
//		TextView txtSongWriter = (TextView) findViewById(R.id.txtSongWriter);
//		txtSongBookName.setText(song.getSongbookname());
//		txtSongWriter.setText(song.getWritername());
//		isInfoBound = true;
//		//Toast.makeText(this, "Song Info Bound: " + song.getSongbookname() + ", " + song.getWritername(), Toast.LENGTH_SHORT).show();
//	}
//
//}
