package meets.chane.memloc;
import java.sql.Timestamp;
import java.util.Date;
import android.provider.BaseColumns;
import android.location.Location;

/**
 * Created by shanematthews on 9/15/15.
 */
public class Reminder {
    private boolean leaving;
    private double lat;
    private double lon;

    public Reminder(boolean leave, double lat, double lon) {
        this.leaving = leave;
        this.lat = lat;
        this.lon = lon;
    }
}
