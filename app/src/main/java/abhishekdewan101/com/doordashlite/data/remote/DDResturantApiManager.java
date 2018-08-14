package abhishekdewan101.com.doordashlite.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DDResturantApiManager {

    private static DDResturantApiManager mInstance;
    private Retrofit mDDRetrofitClient;

    private DDResturantApiManager() {
        createDDRetrofitClient();
    }

    public static DDResturantApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new DDResturantApiManager();
        }
        return mInstance;
    }


    private void createDDRetrofitClient() {
        mDDRetrofitClient = new Retrofit.Builder().baseUrl("https://api.doordash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildClientWithLoggingEnabled())
                .build();
    }

    private OkHttpClient buildClientWithLoggingEnabled() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return client;
    }

    public Flowable<List<Resturant>> getAllResturants(String lat, String lng) {
        return mDDRetrofitClient.create(DDResturantApi.class).getResturantListForLocation(lat,lng);
    }
}
