package abhishekdewan101.com.doordashlite.data.remote.Api;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DDResturantApi {

    @GET("v2/restaurant")
    Flowable<List<Resturant>> getResturantListForLocation(@Query("lat") String lat,
                                                                 @Query("lng") String lng);

    @GET("v2/restaurant/{id}")
    Flowable<Resturant> getResturantDetailsForID(@Path("id") long id);
}
