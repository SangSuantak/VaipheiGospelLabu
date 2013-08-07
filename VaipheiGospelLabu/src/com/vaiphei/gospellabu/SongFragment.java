package com.vaiphei.gospellabu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SongFragment extends Fragment {
	
	//private int songNumber;
	//private int songBookId;
	private int pageNumber;
	private static Song song;	
	
	public static SongFragment create(int pageNumber, Song pSong) {
		SongFragment fragment = new SongFragment();
		Bundle args = new Bundle();
		args.putInt("PAGE_NUMBER", pageNumber);
		song = pSong;
		//args.putInt("SONG_NUMBER", songNumber);
		//args.putInt("SONG_BOOK_ID", songBookId);
		fragment.setArguments(args);
		return fragment;
	}
	
	public SongFragment() {		
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//songNumber = getArguments().getInt("SONG_NUMBER");
		//songBookId = getArguments().getInt("SONG_BOOK_ID");
		pageNumber = getArguments().getInt("PAGE_NUMBER");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_song_fragment,  container, false);
		//song = SongDisplayActivity.dataAccess.getSongByNumber(songNumber, songBookId);
		((TextView)rootView.findViewById(R.id.txtSongLyric)).setText(song.getLyric());
		((TextView)rootView.findViewById(R.id.txtSongBookName)).setText(song.getSongbookname());
		((TextView)rootView.findViewById(R.id.txtSongWriter)).setText(song.getWritername());		
		return rootView;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public int getSongNumber() {
		return song.getSongnumber();
	}
	
	public int getSongBookId() {
		return song.getSongbookid();
	}

}
