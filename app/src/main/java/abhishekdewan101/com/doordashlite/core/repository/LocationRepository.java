package abhishekdewan101.com.doordashlite.core.repository;

import android.content.Context;
import android.location.Location;

import abhishekdewan101.com.doordashlite.core.managers.DDLocationManager;
import abhishekdewan101.com.doordashlite.data.local.AppCache;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;

public class LocationRepository {

    private final String TAG = DDConstants.PREFIX + LocationRepository.class.getSimpleName();

    DDLocationManager mLocationManager;
    AppCache mAppCache;

    public LocationRepository() {
        mLocationManager = new DDLocationManager();
        mAppCache = AppCache.getInstance();
    }

    public Flowable<Location> getUserCurrentLocation(Context context) {
        DDLog.d(TAG,"getUserCurrentLocation");
        if (mAppCache.getLastKnownLocation() != null) {
            return Flowable.just(mAppCache.getLastKnownLocation());
        } else {
            return mLocationManager.getUserCurrentLocation(context).flatMap(
                    location -> {
                        mAppCache.setLastKnownLocation(location);
                        return Flowable.just(location);
                    }
            );
        }
    }
}
