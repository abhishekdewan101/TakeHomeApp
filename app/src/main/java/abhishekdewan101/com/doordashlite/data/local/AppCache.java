package abhishekdewan101.com.doordashlite.data.local;

import android.location.Location;

public class AppCache {
    private static AppCache mInstance;

    private Location mLastKnownLocation;

    public static AppCache getInstance() {
        if (mInstance == null) mInstance = new AppCache();
        return mInstance;
    }

    public Location getLastKnownLocation() {
        return mLastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        mLastKnownLocation = lastKnownLocation;
    }
}
