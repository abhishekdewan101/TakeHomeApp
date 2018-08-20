package abhishekdewan101.com.doordashlite.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static abhishekdewan101.com.doordashlite.utils.DDConstants.BASE_URL;

/*
 * The DDResturantApiClient creates the retrofit client for talking to the provided
 * api and then exposes the DDResturantApi to be used.
 */

public class DDResturantApiClient {

    private Retrofit mDDRetrofitClient;
    private DDResturantApi mResturantApi;

    public static int mCurrentOffset = 0;

    public DDResturantApiClient() {
        createDDRetrofitClient();
        createDDResturantApis();
    }

    private void createDDRetrofitClient() {
        mDDRetrofitClient = new Retrofit.Builder().baseUrl(BASE_URL)
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

    private void createDDResturantApis() {
        mResturantApi = mDDRetrofitClient.create(DDResturantApi.class);
    }

    public DDResturantApi getResturantApi() {
        return mResturantApi;
    }
}
