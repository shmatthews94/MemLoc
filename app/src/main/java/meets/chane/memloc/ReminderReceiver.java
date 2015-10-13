package meets.chane.memloc;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by shanematthews on 10/12/15.
 */
public class ReminderReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Pass right over to UtilityService class, the wakeful receiver is
        // just needed in case the geofence is triggered while the device
        // is asleep otherwise the service may not have time to trigger the
        // notification.
        intent.setClass(context, ReminderService.class);
        intent.setAction(ReminderService.ACTION_GEOFENCE_TRIGGERED);
        startWakefulService(context, intent);
    }

}
