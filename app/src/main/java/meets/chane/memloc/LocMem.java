package meets.chane.memloc;

//import android.Manifest;
//import android.content.pm.PackageManager;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.location.Location;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.meets.chane.memloc.R;

import java.util.ArrayList;



public class LocMem extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener{

    private Button Add;
    private RadioButton Leaving;
    private RadioButton Arriving;
    private TextView Added;
    private EditText Task;
    private EditText Location;
    private ArrayList<Reminder> Reminders;
    MemLocDbHelper mDbHelper;
    SQLiteDatabase db;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;
    protected static final String TAG = "memloc sample";
    LocationManager manager;
    public final static String EXTRA_MESSAGE = "chane.meets.MemLoc.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_mem);
        Add = (Button) findViewById(R.id.Add);
        Leaving = (RadioButton) findViewById(R.id.Leaving);
        Arriving = (RadioButton) findViewById(R.id.Arriving);
        Added = (TextView) findViewById(R.id.Added);
        Task = (EditText) findViewById(R.id.Task);
        Location = (EditText) findViewById(R.id.Location);
        Reminders = new ArrayList<Reminder>();
        mLatitudeText = (TextView) findViewById((R.id.mLatitudeText));
        mLongitudeText = (TextView) findViewById((R.id.mLongitudeText));
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        buildGoogleApiClient();
        mDbHelper = new MemLocDbHelper(getApplicationContext());
        db = mDbHelper.getWritableDatabase();

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
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

        Reminder q = new Reminder(true, "home");
        mDbHelper.addReminder(db, "task", "location", "latitude", "longitude");

        //Cursor c = db.rawQuery("SELECT * FROM Reminders", null);
        //if(c.moveToFirst()) {
        //    Log.i(TAG, "First position!");
        //}
        //Reminder q = new Reminder(this.Leaving.isChecked(), this.Location.getText().toString());
        Reminders.add(q);
        if (Reminders.contains(q)) {
            this.Added.setText("Added!");
            if (mLastLocation != null) {
                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            } else {
                Log.i(TAG, "no location detected");
            }
        }
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.Leaving:
                if (checked)
                    break;
            case R.id.Arriving:
                if (checked)
                    break;
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            Log.i(TAG, "no location detected");
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    public void dispMemBank(View view){
        Intent intent = new Intent(this, MemBank.class);
        //EditText editText = (EditText) findViewById(R.id.Location);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
