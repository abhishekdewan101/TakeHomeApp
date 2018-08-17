package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.core.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

class LauncherPresenter extends BasePresenter<LauncherContract.LauncherView> implements LauncherContract.LauncherActions{

    private ResturantRepository mResturantRepository;
    private LocalDBRepository mLocalDBRepository;

    LauncherPresenter(LauncherContract.LauncherView view) {
        super(view);
        mResturantRepository = new ResturantRepository();
        mLocalDBRepository = new LocalDBRepository();
    }

    @Override
    public void getResturantsForCurrentLocation(Context context) {
        DDLog.d(TAG,"getResturantsForCurrentLocation");
        mLocationRepository.getUserCurrentLocation(context)
                .flatMap(location -> mResturantRepository.getResturantListForLocation(
                        String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude())
                        ).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()))
                        .flatMap(resturants -> Flowable.fromIterable(resturants))
                        .flatMap(resturant -> mLocalDBRepository.insertResturantIntoDB(context,resturant))
                .subscribe(resturant -> {

                }, error -> {
                    DDLog.e(TAG, error.getMessage());
                    mBaseView.handleError(error);
                }, () -> {
                    mBaseView.onResturantsDownloaded();
                });
    }

    @Override
    public void doesLocalDBHaveData(Context context) {
        DDLog.d(TAG,"doesLocalDBHaveData");
        mLocalDBRepository.getAllResturantsFromDB(context)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        size -> {
                           DDLog.d(TAG,"Number of Resturants is " + size);
                           mBaseView.showOfflineAccessMode();
                        }, error -> {
                            DDLog.e(TAG,error.getMessage());
                        }
                );
    }
}
