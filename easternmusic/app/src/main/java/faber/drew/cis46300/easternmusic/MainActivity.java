package faber.drew.cis46300.easternmusic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    Button btBamboo, btPalace;
    MediaPlayer mpBamboo, mpPalace;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btBamboo = (Button) findViewById(R.id.btnBamboo);
        btPalace = (Button) findViewById(R.id.btnPalace);

        btBamboo.setOnClickListener(bBamboo);
        btPalace.setOnClickListener(bPalace);
        mpBamboo = new MediaPlayer();
        mpBamboo = MediaPlayer.create(this, R.raw.bamboo);
        mpPalace = new MediaPlayer();
        mpPalace = MediaPlayer.create(this, R.raw.palace);
        playing = 0;
    }
        Button.OnClickListener bBamboo = new Button.OnClickListener() {
            public void onClick(View V) {
                    switch (playing) {
                        case 0:
                            mpBamboo.start();
                            playing = 1;
                            btBamboo.setText("Pause Bamboo Song");
                            btPalace.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            mpBamboo.pause();
                            playing = 0;
                            btBamboo.setText("Play Bamboo Song");
                            btPalace.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            };

        Button.OnClickListener bPalace = new Button.OnClickListener() {
            public void onClick(View v) {
                    switch (playing) {
                        case 0:
                            mpPalace.start();
                            btPalace.setText("Pause Palace Song");
                            btBamboo.setVisibility(View.INVISIBLE);
                            playing = 1;
                            break;
                        case 1:
                            mpPalace.pause();
                            playing = 0;
                            btPalace.setText("Play Palace Song");
                            btBamboo.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            };



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
