package meets.chane.memloc;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;
/**
 * Created by shanematthews on 9/30/15.
 */
public class MemLocDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "MemLocReminder.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "Reminders";
    public static final String TASK = "Task";
    public static final String ARRIVE = "Arrive";
    public static final String LOCATION = "Location";
    public static final String LAT_FIELD = "Latitude";
    public static final String LONG_FIELD = "Longitude";

    // Reminder.ReminderEntry._ID + " INTEGER PRIMARY KEY," +

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    TASK + TEXT_TYPE + COMMA_SEP +
                    ARRIVE + TEXT_TYPE + COMMA_SEP +
                    LOCATION + TEXT_TYPE + COMMA_SEP +
                    LAT_FIELD + TEXT_TYPE + COMMA_SEP +
                    LONG_FIELD + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MemLocDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void addReminder(SQLiteDatabase db, String arriving, String task, String loc, String lat, String lon) {
        db.execSQL("INSERT INTO Reminders VALUES('" + task + "','" + arriving + "','" + loc +
                "','" + lat + "','" + lon + "');");
        // db.delete(TABLE_NAME, null, null);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}