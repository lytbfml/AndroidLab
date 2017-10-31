package com.example.mediaplayerpreferences;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * CPRE 388 - Labs
 * <p>
 * Copyright 2013
 */
public class MyMediaPlayerActivity extends Activity implements View.OnClickListener {
	
	
	/**
	 * Other view elements
	 */
	private TextView songTitleLabel;
	
	/**
	 * media player:
	 * http://developer.android.com/reference/android/media/MediaPlayer.html
	 */
	private MediaPlayer mp;
	
	/**
	 * Index of the current song being played
	 */
	private int currentSongIndex = 0;
	
	boolean shuffle = false;
	
	/**
	 * List of Sounds that can be played in the form of SongObjects
	 */
	private static ArrayList<SongObject> songsList = new ArrayList<SongObject>();
	
	private static ArrayList<Integer> playList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_player_main);
		
		songTitleLabel = (TextView) findViewById(R.id.songTitle);
		
		findViewById(R.id.playpausebutton).setOnClickListener(this);
		findViewById(R.id.backbutton).setOnClickListener(this);
		findViewById(R.id.forwardbutton).setOnClickListener(this);
		
		// Initialize the media player
		mp = new MediaPlayer();
		
		prefsHolder pref = getPrefs();
		
		shuffle = pref.shuffle;
		// Getting all songs in a list
		populateSongsList(pref.listDetail);
		
		// By default play first song if there is one in the list
		playSong(pref.songStart);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.playpausebutton:
				if (mp.isPlaying()) {
					Log.d("debug", "is playing");
					v.setBackgroundResource(R.drawable.btn_play);
					mp.pause();
				} else {
					v.setBackgroundResource(R.drawable.btn_pause);
					mp.start();
				}
				break;
			case R.id.backbutton:
				if (currentSongIndex > 0 && !shuffle) {
					playSong(currentSongIndex - 1);
				}
				else if(shuffle && playList.size() > 1)
				{
					playSong(playList.get(playList.size() - 2));
					playList.remove(playList.size() - 1);
				}
				break;
			case R.id.forwardbutton:
				if (currentSongIndex < songsList.size() - 1 && !shuffle) {
					playSong(currentSongIndex + 1);
				}
				else if (shuffle)
				{
					Random r = new Random();
					int next = r.nextInt(songsList.size());
					playSong(next);
					playList.add(next);
				}
				break;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		prefsHolder pref = getPrefs();
		populateSongsList(pref.listDetail);
		shuffle = pref.shuffle;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media_player_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.menu_choose_song:
				// Open SongList to display a list of audio files to play
				Intent i = new Intent(this, SongList.class);
				startActivityForResult(i, 1);
				return true;
			case R.id.menu_preferences:
				// Display Settings page
				Intent p = new Intent(this, MediaPreferences.class);
				startActivityForResult(p, 2);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				int index = data.getIntExtra("songIndex", 0);
				songTitleLabel.setText(songsList.get(index).getTitle());
				playSong(index);
			}
		} else if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				
			}
		}
	}
	
	
	/**
	 * Helper function to play a song at a specific index of songsList
	 *
	 * @param songIndex - index of song to be played
	 */
	public void playSong(int songIndex) {
		// Play song if index is within the songsList
		if (songIndex < songsList.size() && songIndex >= 0) {
			try {
				mp.stop();
				mp.reset();
				mp.setDataSource(songsList.get(songIndex).getFilePath());
				mp.prepare();
				mp.start();
				// Displaying Song title
				String songTitle = songsList.get(songIndex).getTitle();
				songTitleLabel.setText(songTitle);
				
				// Changing Button Image to pause image
				findViewById(R.id.playpausebutton).setBackgroundResource(R.drawable.btn_pause);
				
				// Update song index
				currentSongIndex = songIndex;
			}
			catch (IllegalArgumentException | IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else if (songsList.size() > 0) {
			playSong(0);
		}
	}
	
	
	/**
	 * Get list of info for all sounds to be played
	 */
	public void populateSongsList(int listDetail) {
		songsList.clear();
		String mProjection[] = new String[]{MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media.DATA};
		String selection[] = {null, MediaStore.Audio.Media.IS_MUSIC + " = 1", MediaStore.Audio.Media.IS_RINGTONE + " " +
				"= 1"};
		String mSelectionClause = selection[listDetail - 1];
		String mSelectionArgs[] = null;
		String mSortOrder = null;
		
		// Get a Cursor object from the content URI 
		Cursor mCursor = getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
				mProjection, //  The  list  of  columns  to  return  for  each  row
				mSelectionClause, //  Selection  criteria
				mSelectionArgs, //  Arguments  of  the  Selection  criteria
				mSortOrder); //  The  sort  order  for  the  returned  rows
		
		// Use the cursor to loop through the results and add them to the songsList as SongObjects
		while (mCursor.moveToNext()) {
			String title = mCursor.getString(0);
			String path = mCursor.getString(1);
			songsList.add(new SongObject(title, path));
		}
	}
	
	/**
	 * Get song list for display in ListView
	 *
	 * @return list of Songs
	 */
	public static ArrayList<SongObject> getSongsList() {
		return songsList;
	}
	
	
	private prefsHolder getPrefs() {
		//get preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Resources res = getResources();
		
		boolean shuffle = prefs.getBoolean(res.getString(R.string.mp_shuffle_pref), false);
		
		int songStart = Integer.parseInt(prefs.getString(res.getString(R.string.mp_start_pref), "1"));
		
		int listDetail = Integer.parseInt(prefs.getString(res.getString(R.string.mp_list_detail), "1"));
		
		Log.d("DebugRealNow", shuffle + " " + songStart + " " + listDetail);
		return new prefsHolder(shuffle, songStart, listDetail);
	}
	
	static class prefsHolder {
		boolean shuffle;
		int songStart;
		int listDetail;
		
		public prefsHolder(boolean shuffle, int songStart, int listDetail) {
			this.shuffle = shuffle;
			this.songStart = songStart;
			this.listDetail = listDetail;
		}
	}
	
}
