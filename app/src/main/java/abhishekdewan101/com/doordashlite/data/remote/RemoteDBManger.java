package abhishekdewan101.com.doordashlite.data.remote;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.Api.DDResturantApiManager;
import io.reactivex.Flowable;

public class RemoteDBManger {

    private DDResturantApiManager mApiManager;

    private static RemoteDBManger mInstance;

    private RemoteDBManger() {
        mApiManager = new DDResturantApiManager();
    }

    public static RemoteDBManger getInstance() {
        if (mInstance == null) {
            mInstance = new RemoteDBManger();
        }
        return mInstance;
    }

    public Flowable<List<Resturant>> getResturantsForLocation(String lat, String lng) {
        return mApiManager.getResturantApi().getResturantListForLocation(lat,lng);
    }

    public Flowable<Resturant> getResturantDetailsForResturant(int id) {
        return mApiManager.getResturantApi().getResturantDetailsForID(id);
    }
}
