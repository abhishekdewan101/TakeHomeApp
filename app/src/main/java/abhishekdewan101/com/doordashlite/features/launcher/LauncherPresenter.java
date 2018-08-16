package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import abhishekdewan101.com.doordashlite.data.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.features.base.BaseView;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.schedulers.Schedulers;

class LauncherPresenter extends BasePresenter<LauncherContract.LauncherView> implements LauncherContract.LauncherActions{

    private ResturantRepository mResturantRepository;

    LauncherPresenter(LauncherContract.LauncherView view) {
        super(view);
        mResturantRepository = new ResturantRepository();
    }

    @Override
    public void getResturantsForCurrentLocation(Context context) {
        DDLog.d(TAG,"getResturantsForCurrentLocation");
        mLocationRepository.getUserCurrentLocation(context)
                .flatMap(location -> mResturantRepository.getResturantListForLocation(
                        String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude())
                        ).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()))
                .subscribe(resturants -> {
                    DDLog.d(TAG, "Number of Resturants - " + resturants.size());
                }, error -> {
                    DDLog.e(TAG, error.getMessage());
                }, () -> {
                    mBaseView.onResturantsDownloaded();
                });
    }
}
