package abhishekdewan101.com.doordashlite.core.repository;

import android.content.Context;
import android.location.Location;

import abhishekdewan101.com.doordashlite.core.managers.DDLocationManager;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;

public class LocationRepository {

    private final String TAG = DDConstants.PREFIX + LocationRepository.class.getSimpleName();

    DDLocationManager mLocationManager;

    public LocationRepository() {
        mLocationManager = new DDLocationManager();
    }

    public Flowable<Location> getUserCurrentLocation(Context context) {
        DDLog.d(TAG,"getUserCurrentLocation");
        return mLocationManager.getUserCurrentLocation(context);
    }
}
