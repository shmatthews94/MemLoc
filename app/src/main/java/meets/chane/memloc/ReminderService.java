package meets.chane.memloc;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.TimeUnit;

import static com.google.android.gms.location.LocationServices.GeofencingApi;

/**
 * Created by shanematthews on 10/12/15.
 */
public class ReminderService extends IntentService {
    private static final String TAG = ReminderService.class.getSimpleName();
    public static final String ACTION_GEOFENCE_TRIGGERED = "geofence_triggered";

    public ReminderService() {
        super(TAG);
    }

    private void addInternalGeofences() {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();
        ConnectionResult connectionResult = googleApiClient.blockingConnect(
                10, TimeUnit.SECONDS);
        if (connectionResult.isSuccess() && googleApiClient.isConnected()) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this, 0, new Intent(this, ReminderReceiver.class), 0);
            //GeofencingApi.addGeofences(googleApiClient,
            //        LocMem.getGeofenceList(), pendingIntent);
            googleApiClient.disconnect();
        } else {
            Log.e(TAG, String.format("Failed to connect to GoogleApiClient (error code = %d)",
                    connectionResult.getErrorCode()));
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent != null ? intent.getAction() : null;
    }
}
