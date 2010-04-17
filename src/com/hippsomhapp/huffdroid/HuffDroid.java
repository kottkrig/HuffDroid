package com.hippsomhapp.huffdroid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hippsomhapp.huffdroid.controller.HuffAdapter;
import com.hippsomhapp.huffdroid.model.HuffDuff;
import com.hippsomhapp.huffdroid.model.HuffDuffs;

public class HuffDroid extends ListActivity implements Runnable {
	
	private HuffDuffs items;
	private ProgressDialog progressDialog;
	private static final MediaPlayer mMediaPlayer = new MediaPlayer();
	private int[] resumeArray;
	
	private Button mPlayPause;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressDialog = ProgressDialog.show(this, "Working...", "Fetching your duffs", true, false);  
        new Thread(this).start();
    }
    
    private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
					
      
			// Adds the array to the arrayAdapter and listView
			HuffAdapter adapter = new HuffAdapter(HuffDroid.this, R.layout.row, items.getItems());
			HuffDroid.this.setListAdapter(adapter);
			
			//Button stopPlayback = (Button) findViewById(R.id.stop_playback);
	        //stopPlayback.setOnClickListener(stopClickListener);
	        
	        mPlayPause = (Button) findViewById(R.id.play_pause);
	        mPlayPause.setOnClickListener(playPauseListener);
			
		}
	};
    
    public void run(){
    	Gson gson = new Gson();
    	Reader r = new InputStreamReader(getJSONData("http://huffduffer.com/kottkrig/json"));
    	items = gson.fromJson(r, HuffDuffs.class);
    	mHandler.sendEmptyMessage(0);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
    	super.onListItemClick(l, v, position, id);
     
     // Get the item that was clicked
     HuffDuff hd = items.getItems().get(position);
     String url = hd.getUrl();
     Toast.makeText(HuffDroid.this, Uri.parse(url).toString(), Toast.LENGTH_SHORT).show();
     
     TextView description = (TextView) findViewById(R.id.description);
     description.setText(hd.getDescription());
     
     TextView title = (TextView) findViewById(R.id.title);
     title.setText(hd.getTitle());
     
     SlidingDrawer sd = (SlidingDrawer) findViewById(R.id.drawer);
     sd.animateOpen();
     
     try {
    	 if(mMediaPlayer != null) {
    		 mMediaPlayer.reset();
    		 mMediaPlayer.setDataSource(HuffDroid.this, Uri.parse(url));
    		 mMediaPlayer.prepare();
    		 mMediaPlayer.start();
    		 
    	 }
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    }
    
   
    
    public InputStream getJSONData(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        URI uri;
        InputStream data = null;
        try {
            uri = new URI(url);
            HttpGet method = new HttpGet(uri);
            HttpResponse response = httpClient.execute(method);
            data = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
    public HuffDuffs runJSONParser(){
        try{
        Log.i("MY INFO", "Json Parser started..");
        Gson gson = new Gson();
        Reader r = new InputStreamReader(getJSONData("http://huffduffer.com/kottkrig/json"));
        Log.i("MY INFO", r.toString());
        HuffDuffs objs = gson.fromJson(r, HuffDuffs.class);
        return objs;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        	return new HuffDuffs();
        }
    }
    
    OnClickListener stopClickListener = new OnClickListener() {
    	public void onClick(View v){
    		mMediaPlayer.stop();
    		Toast.makeText(HuffDroid.this, "Stopping...", Toast.LENGTH_SHORT).show();
    	}
    };
    
    OnClickListener playPauseListener = new OnClickListener() {
    	public void onClick(View v){
    		if (mMediaPlayer.isPlaying()) {
    			Toast.makeText(HuffDroid.this, "Pausing...", Toast.LENGTH_SHORT).show();
    			mMediaPlayer.pause();
    			mPlayPause.setText("Resume");
    		}
    		else if( !mMediaPlayer.isPlaying() ){
    			Toast.makeText(HuffDroid.this, "Resuming...", Toast.LENGTH_SHORT).show();
    			mMediaPlayer.start();
    			mPlayPause.setText("Pause");
    		}
    		
    	}
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
    };
    
 // Called when menu item is selected //
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
      
      switch(item.getItemId()){
      case R.id.preferences: 
    	  Intent i = new Intent(HuffDroid.this, Prefs.class);
          startActivity(i);
    	  break;
      }    
      return true;
    }
}