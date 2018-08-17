package abhishekdewan101.com.doordashlite.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DDResturantApiClient {

    private Retrofit mDDRetrofitClient;
    private DDResturantApi mResturantApi;

    public DDResturantApiClient() {
        createDDRetrofitClient();
        createDDResturantApis();
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

    private void createDDResturantApis() {
        mResturantApi = mDDRetrofitClient.create(DDResturantApi.class);
    }

    public DDResturantApi getResturantApi() {
        return mResturantApi;
    }
}
