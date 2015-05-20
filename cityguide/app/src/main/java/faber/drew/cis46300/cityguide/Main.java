package faber.drew.cis46300.cityguide;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Main extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayShowHomeEnabled(true);

        String[] attraction = {"Alcatraz Island","Ferry Marketplace","Golden Gate Bridge","Cable Car Trolley","Fisherman's Wharf"};
        int[] icons = {R.drawable.alcatraz_icon,R.drawable.marketplace_icon,R.drawable.bridge_icon,R.drawable.trolley_icon,R.drawable.wharf_icon};

        setListAdapter(new MyCustomArrayAdapter(this,R.layout.main, R.id.travel, attraction, icons));
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        switch(position)
        {
            case 0:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://alcatrazcruises.com/")));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ferrybuildingmarketplace.com")));
                break;
            case 2:
                startActivity(new Intent(Main.this, Bridge.class));
                break;
            case 3:
                startActivity(new Intent(Main.this, Trolley.class));
                break;
            case 4:
                startActivity(new Intent(Main.this, Wharf.class));
                break;
        }
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
