package meets.chane.memloc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.ArrayList;

public class LocMem extends AppCompatActivity {

    private Button Add;
    private CheckBox Leaving;
    private CheckBox Arriving;
    private EditText Added;
    private EditText Task;
    private EditText Location;
    private ArrayList<Reminder> Reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_mem);

        Add = (Button) findViewById(R.id.Add);
        Leaving = (CheckBox) findViewById(R.id.Leaving);
        Arriving = (CheckBox) findViewById(R.id.Arriving);
        Added = (EditText) findViewById(R.id.Added);
        Task = (EditText) findViewById(R.id.Task);
        Location = (EditText) findViewById(R.id.Location);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loc_mem, menu);
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

    public void createReminder(View v) {
        /*
        Reminder q = new Reminder(true, "home");
        //Reminder r = new Reminder(this.Leaving.isChecked(), this.Location.getText().toString());
        Reminders.add(q);
        if(Reminders.contains(q)) {
            this.Added.setText("Added!");
        }
         */
        this.Added.setText("Added!");
    }
}
