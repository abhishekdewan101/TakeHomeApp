package abhishekdewan101.com.doordashlite.features.home;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.util.Log;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.local.ResturantDatabase;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeScreenActivity extends BaseActivity<HomeScreenPresenter> implements HomeScreenContract.HomeScreenView {

    private ResturantDatabase mDatabase;

    @Override
    protected HomeScreenPresenter createPresenter() {
        return new HomeScreenPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mDatabase = Room.databaseBuilder(this.getApplication(), ResturantDatabase.class,"resturants.db").build();

        mDatabase.resturantDao().getAllCachedResturants()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(
                        resturants -> {
                            Log.d(TAG,"Number of Resturants from DB is " + resturants.size());
                            Log.d(TAG,"Number of menu items is " + resturants.get(10).mMenus.size());
                        },
                        error -> {
                            Log.e(TAG,error.getLocalizedMessage());
                        }
                );
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
