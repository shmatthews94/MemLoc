package meets.chane.memloc;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by shanematthews on 10/12/15.
 */
public class LocAlert extends BroadcastReceiver {
    public static final String EVENT_ID_INTENT_EXTRA = "EventIDIntentExtraKey";

    @Override
    public void onReceive(Context context, Intent intent) {
        long eventID = intent.getLongExtra(EVENT_ID_INTENT_EXTRA, -1);
        Log.v("gauntface", "Proximity Alert Intent Received, eventID = " + eventID);
    }

}
