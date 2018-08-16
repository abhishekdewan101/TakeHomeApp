package abhishekdewan101.com.doordashlite.data.managers;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.Api.DDResturantApiManager;
import io.reactivex.Flowable;

public class DDResturantDBManager {

    private DDResturantApiManager mApiManager;


    public Flowable<List<Resturant>> getResturantsForLocation(String lat, String lng) {
        return mApiManager.getResturantApi().getResturantListForLocation(lat,lng);
    }

    public Flowable<Resturant> getResturantDetailsForResturant(int id) {
        return mApiManager.getResturantApi().getResturantDetailsForID(id);
    }
}
