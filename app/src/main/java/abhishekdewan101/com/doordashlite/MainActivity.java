package abhishekdewan101.com.doordashlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApi;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiManager;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    DDResturantApiManager mApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiManager = DDResturantApiManager.getInstance();

        mApiManager.getAllResturants("37.422740","-122.139956")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(resturants -> {
                    Log.d("DDLite","Number of Resturants is "+resturants.size());
                },error -> {
                    Log.e("DDLite",error.getLocalizedMessage());
                });

    }
}
