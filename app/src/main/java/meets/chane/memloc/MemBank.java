package meets.chane.memloc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.location.meets.chane.memloc.R;

import java.util.ArrayList;


public class MemBank extends AppCompatActivity {

    protected static final String TAG = "memloc sample";
    protected TextView Reminder1;
    MemLocDbHelper dbHelper;
    SQLiteDatabase db;
    private ListView locsListView;
    private ArrayAdapter arrayAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_bank);
        dbHelper = new MemLocDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        ArrayList<String> locs = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Reminders", null);
        StringBuffer buffer=new StringBuffer();

        while(c.moveToNext())
        {
            String remind = "";
            //locs.add(c.getString(2));
            String output = c.getString(1).substring(0, 1).toUpperCase() + c.getString(1).substring(1);
            remind += (output+" ");
            if(c.getString(0).compareTo("1") == 0) {
                remind += ("when I arrive at "+c.getString(2)+": ");
            }
            else {
                remind += ("before I leave "+c.getString(2)+": ");
            }
            remind += ("Lat: "+c.getString(3).substring(0, 6) + " ");
            remind += ("Lon: "+c.getString(4).substring(0, 7) + "\n");
            locs.add(remind);
        }


        locsListView = (ListView) findViewById(R.id.listView);

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locs);
        locsListView.setAdapter(arrayAdapter);


        //Reminder1 = (TextView) findViewById(R.id.Reminder1);
        //Reminder1.setText(buffer);d
        // Set the text view as the activity layout
        // setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mem_bank, menu);
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
