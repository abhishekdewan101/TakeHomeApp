package abhishekdewan101.com.doordashlite.core.repository;

import java.util.List;

import abhishekdewan101.com.doordashlite.core.managers.DDResturantManager;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;

public class ResturantRepository {

    private final String TAG = DDConstants.PREFIX + ResturantRepository.class.getSimpleName();

    DDResturantManager mResturantManager;

    public ResturantRepository() {
        mResturantManager = new DDResturantManager();
    }

    public Flowable<List<Resturant>> getResturantListForLocation(String lat, String lng,int offset) {
        DDLog.d(TAG,"getResturantListForLocation");
        return mResturantManager.getResturantsForLocation(lat,lng,offset);
    }

    public Flowable<Resturant> getResturantDetailsForID(long id) {
        DDLog.d(TAG,"getResturantDetailsForID");
        return mResturantManager.getResturantDetailsForResturant(id);
    }
}
