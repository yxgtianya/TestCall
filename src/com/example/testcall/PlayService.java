package com.example.testcall;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class PlayService extends Service {
	MediaPlayer myplayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		myplayer = MediaPlayer.create(this, R.raw.chinavoice);
		myplayer.setLooping(false);
	}
	
	@Override
	public void onStart(Intent intent, int startid){
		myplayer.start();
	}
	
	@Override
	public void onDestroy(){
		myplayer.stop();
	}
}
