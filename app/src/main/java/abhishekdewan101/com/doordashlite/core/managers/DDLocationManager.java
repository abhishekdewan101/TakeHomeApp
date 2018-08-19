package abhishekdewan101.com.doordashlite.core.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import abhishekdewan101.com.doordashlite.utils.ErrorCodes;
import abhishekdewan101.com.doordashlite.utils.FlowableLocationListener;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;

public class DDLocationManager {

    public static final String TAG = DDConstants.PREFIX + DDLocationManager.class.getSimpleName();
    public static final Long LOCATION_REQUEST_TIMEOUT = 15 * 1000L; // 15 seconds;

    public Flowable<Location> getUserCurrentLocation(Context context) {
        DDLog.d(TAG,"getUserCurrentLocation");
        return Flowable.create( (FlowableOnSubscribe<Location>)  emitter ->  {
            getLocationInternal(context,emitter);
        }, BackpressureStrategy.LATEST)
                .timeout(LOCATION_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS,getLocationTimeOutFlowable());
    }

    @SuppressLint("MissingPermission")
    public Flowable<Location> getLastKnownLocation(Context context) {
        DDLog.d(TAG,"getLastKnownLocation");
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            Criteria criteria = new Criteria();
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            return Flowable.just(locationManager.getLastKnownLocation("fused"))
                    .filter(location -> location!=null);
        } else {
            DDLog.e(TAG,"error getting location manager");
            return Flowable.error(new Throwable(String.valueOf(ErrorCodes.LOCATION_MANAGER_NOT_FOUND_EXCEPTION)));
        }
    }

    private Flowable<Location> getLocationTimeOutFlowable() {
        return Flowable.error(new Throwable(String.valueOf(ErrorCodes.LOCATION_TIMEOUT_EXCEPTION)));
    }


    @SuppressWarnings({"MissingPermission"})
    private void getLocationInternal(Context context, FlowableEmitter<Location> emitter) {
        DDLog.d(TAG,"getLocationInternal");
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            Criteria criteria = new Criteria();
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            locationManager.requestSingleUpdate(criteria,new FlowableLocationListener(emitter),Looper.getMainLooper());
        } else {
            DDLog.e(TAG,"error getting location manager");
            emitter.onError(new Throwable("Error getting Location Manager"));
        }
    }

}
