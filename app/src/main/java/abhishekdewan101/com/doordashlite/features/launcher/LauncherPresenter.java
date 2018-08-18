package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.core.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiClient;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        String.valueOf(location.getLongitude()),
                        DDResturantApiClient.mCurrentOffset
                        ).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()))
                        .flatMap(resturants -> mLocalDBRepository.resetDBForNewLocation(context).flatMap(
                                result -> Flowable.fromIterable(resturants)
                        ))
                        .flatMap(resturant -> mLocalDBRepository.insertResturantIntoDB(context,resturant))
                .subscribe(result -> {
                }, error -> {
                    DDLog.e(TAG, error.getMessage());
                    mBaseView.handleError(error);
                }, () -> {
                    DDResturantApiClient.mCurrentOffset++;
                    mBaseView.onResturantsDownloaded();
                });
    }

    @Override
    public void doesLocalDBHaveData(Context context) {
        DDLog.d(TAG,"doesLocalDBHaveData");
        mLocalDBRepository.getNumberOfResturantsInDB(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
