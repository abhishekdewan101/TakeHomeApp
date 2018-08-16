package abhishekdewan101.com.doordashlite.data.repository;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.Api.DDResturantApiManager;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;

public class ResturantRepository {

    private final String TAG = DDConstants.PREFIX + ResturantRepository.class.getSimpleName();

    DDResturantApiManager mResturantApiManager;

    public ResturantRepository() {
        mResturantApiManager = new DDResturantApiManager();
    }

    public Flowable<List<Resturant>> getResturantListForLocation(String lat, String lng) {
        DDLog.d(TAG,"getResturantListForLocation");
        return mResturantApiManager.getResturantApi().getResturantListForLocation(lat,lng);
    }

    public Flowable<Resturant> getResturantDetailsForID(long id) {
        DDLog.d(TAG,"getResturantDetailsForID");
        return mResturantApiManager.getResturantApi().getResturantDetailsForID(id);
    }
}
