package abhishekdewan101.com.doordashlite.core.managers;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiClient;
import io.reactivex.Flowable;

public class DDResturantManager {

    private DDResturantApiClient mApiClient;

    public DDResturantManager() {
        mApiClient = new DDResturantApiClient();
    }


    public Flowable<List<Resturant>> getResturantsForLocation(String lat, String lng,int offset) {
        return mApiClient.getResturantApi().getResturantListForLocation(lat,lng,50,offset);
    }

    public Flowable<Resturant> getResturantDetailsForResturant(long id) {
        return mApiClient.getResturantApi().getResturantDetailsForID(id);
    }
}
