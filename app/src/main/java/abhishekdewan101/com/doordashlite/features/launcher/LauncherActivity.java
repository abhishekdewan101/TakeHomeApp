package abhishekdewan101.com.doordashlite.features.launcher;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Callable;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.local.ResturantDatabase;
import abhishekdewan101.com.doordashlite.data.remote.RemoteDBManger;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.home.HomeScreenActivity;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class LauncherActivity extends BaseActivity {

    RemoteDBManger mRemoteDBManger;

    private ResturantDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mDatabase = Room.databaseBuilder(this.getApplication(), ResturantDatabase.class,"resturants.db").build();

        mRemoteDBManger = RemoteDBManger.getInstance();

        Flowable.create(e -> {
            nukeTable();
           e.onNext(1);
           e.onComplete();
        }, BackpressureStrategy.LATEST)
        .subscribeOn(Schedulers.io())
        .subscribe(
                result -> {

                },
                error -> {
                    Log.e(TAG,error.getLocalizedMessage());
                }
        );


        mRemoteDBManger.getResturantsForLocation("37.422740","-122.139956")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(resturants -> Flowable.fromIterable(resturants))
                .subscribe(
                        resturant -> {
                            Log.d(TAG,"Inserting Resturant  - " + resturant.mName);
                            mDatabase.resturantDao().insertResturant(resturant);
                        },
                        error -> {
                         Log.e(TAG,error.getLocalizedMessage());
                        },
                        () -> {
                            startActivity(new Intent(this,HomeScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            this.finish();
                        }
                );
    }

    private void nukeTable() {
        mDatabase.resturantDao().resetForNewLocation();
    }
}
