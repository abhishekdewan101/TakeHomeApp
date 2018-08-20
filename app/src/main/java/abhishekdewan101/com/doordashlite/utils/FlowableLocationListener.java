package abhishekdewan101.com.doordashlite.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import io.reactivex.FlowableEmitter;

/**
 * A LocationListener that emitts the information through a FlowableEmitter
 *
 * Written to work seamlessly within RxChains through out the app.
 */
public class FlowableLocationListener implements LocationListener {

    private final FlowableEmitter<Location> mLocationEmitter;

    public FlowableLocationListener(FlowableEmitter<Location> locationEmitter) {
        mLocationEmitter = locationEmitter;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLocationEmitter.onNext(location);
            mLocationEmitter.onComplete();
        } else {
            mLocationEmitter.onError(new Throwable("Error getting location from the phone. Please check permissions"));
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
