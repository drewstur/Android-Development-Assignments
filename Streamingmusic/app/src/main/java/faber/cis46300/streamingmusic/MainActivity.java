package faber.cis46300.streamingmusic;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();

    String[] url = {
            "http://radio.classicalarchives.com:8000/;stream.nsv",
            "http://2453.live.streamtheworld.com:80/KYGOFM_SC",
            "http://vprbbc.streamguys.net:80/vprbbc24.mp3"};

    String[] title = {
            "Classical Radio",
            "Country KYGO Denver, CO",
            "BBC - Vermont Public Radio"};

    int playing = -1;
    int clicked = -1;

    Button btPlayClassical = null, btPlayCountry = null;
    ImageButton btPlayBBC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPlayClassical = (Button) findViewById(R.id.btnPlayClassical);
        btPlayClassical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = 0;
                beginPlay(clicked);
            }
        });

        btPlayCountry = (Button) findViewById(R.id.btnPlayCountry);
        btPlayCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                clicked = 1;
                beginPlay(clicked);
            }
        });

        btPlayBBC = (ImageButton) findViewById(R.id.imgbtPlayBBC);
        btPlayBBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                clicked = 2;
                beginPlay(clicked);
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.reset();
                return false;
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                Toast.makeText(MainActivity.this, "Streaming" + title[playing], Toast.LENGTH_LONG).show();
            }
        });

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Toast.makeText(MainActivity.this, "mediaPlayer created!", Toast.LENGTH_LONG).show();
    }

    private void beginPlay(int clicked) {
        if (clicked == playing) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                Toast.makeText(MainActivity.this, "Paused", Toast.LENGTH_LONG).show();
            } else
                mediaPlayer.start();
        } else {
            if (playing != -1) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                mediaPlayer.reset();
            }

            try {
                mediaPlayer.setDataSource(this, Uri.parse(url[clicked]));
                Toast.makeText(MainActivity.this, "setDataSource OK for mediaPlayer!", Toast.LENGTH_SHORT).show();

                mediaPlayer.prepareAsync();
                Toast.makeText(MainActivity.this, "Preparing mediaPlayer!", Toast.LENGTH_SHORT).show();
            } catch (Exception o) {
                Toast.makeText(MainActivity.this, "IOException on mediaPlayer: " + o, Toast.LENGTH_LONG).show();
            }
            playing = clicked;
        }
    }

    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)
            mediaPlayer.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
