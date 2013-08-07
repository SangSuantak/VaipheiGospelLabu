package com.vaiphei.gospellabu;

public class Song {
	private int _id;
	private String name;
	private String nameineglish;
	private int songnumber;
	private String key;
	private String tune;
	private String bibleref;
	private String lyric;
	private String categoryname;
	private int categoryid;
	private String writername;
	private int writerid;
	private String songbookname;
	private int songbookid;
	
	public int getId() {
		return _id;
	}
	public void setId(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameineglish() {
		return nameineglish;
	}
	public void setNameineglish(String nameineglish) {
		this.nameineglish = nameineglish;
	}
	public int getSongnumber() {
		return songnumber;
	}
	public void setSongnumber(int songnumber) {
		this.songnumber = songnumber;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTune() {
		return tune;
	}
	public void setTune(String tune) {
		this.tune = tune;
	}
	public String getBibleref() {
		return bibleref;
	}
	public void setBibleref(String bibleref) {
		this.bibleref = bibleref;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getWriterid() {
		return writerid;
	}
	public void setWriterid(int writerid) {
		this.writerid = writerid;
	}
	public int getSongbookid() {
		return songbookid;
	}
	public void setSongbookid(int songbookid) {
		this.songbookid = songbookid;
	}
	
	@Override
	public String toString(){
		return name;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getWritername() {
		return writername;
	}
	public void setWritername(String writername) {
		this.writername = writername;
	}
	public String getSongbookname() {
		return songbookname;
	}
	public void setSongbookname(String songbookname) {
		this.songbookname = songbookname;
	}
}
