package abhishekdewan101.com.doordashlite.core.managers;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiClient;
import io.reactivex.Flowable;

/**
 * DDResturantManager is a class that repositories a easier way to interface
 * with the DDResturantApiClient to talk to the api's provided for the exercise
 */
public class DDResturantManager {

    private DDResturantApiClient mApiClient;

    public DDResturantManager() {
        mApiClient = new DDResturantApiClient();
    }


    public Flowable<List<Resturant>> getResturantsForLocation(String lat, String lng) {
        return mApiClient.getResturantApi().getResturantListForLocation(lat,lng);
    }

    public Flowable<Resturant> getResturantDetailsForResturant(long id) {
        return mApiClient.getResturantApi().getResturantDetailsForID(id);
    }
}
