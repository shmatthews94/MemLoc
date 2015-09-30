package meets.chane.memloc;
import java.sql.Timestamp;
import java.util.Date;
import android.provider.BaseColumns;
import android.location.Location;

/**
 * Created by shanematthews on 9/15/15.
 */
public class Reminder {
    private Date created;
    private boolean leaving;
    private String location;

    public Reminder(boolean leave, String location) {
        this.location = location;
        this.leaving = leave;
        Date date = new java.util.Date();
        this.created = date;
    }
}
