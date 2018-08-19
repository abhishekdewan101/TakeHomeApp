package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import java.util.List;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.core.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApi;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiClient;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class LauncherPresenter extends BasePresenter<LauncherContract.LauncherView> implements LauncherContract.LauncherActions {

    private ResturantRepository mResturantRepository;
    private LocalDBRepository mLocalDBRepository;

    LauncherPresenter(LauncherContract.LauncherView view) {
        super(view);
        mResturantRepository = new ResturantRepository();
        mLocalDBRepository = new LocalDBRepository();
    }

    @Override
    public void getResturantsForCurrentLocation(Context context) {
        DDLog.d(TAG, "getResturantsForCurrentLocation");
        mBaseView.showLoading();
        mCompositeDisposable.add(
                mLocationRepository.getUserCurrentLocation(context)
                .flatMap(location -> mResturantRepository.getResturantListForLocation(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()))
                        .subscribeOn(Schedulers.io()))
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resturants -> {
                            saveResturantsToDB(resturants,context);
                        },error -> {
                            mBaseView.dismissLoading();
                            mBaseView.handleError(error);
                        }
                )
        );

//        mCompositeDisposable.add(
//                mLocationRepository.getUserCurrentLocation(context)
//                        .flatMap(location -> mResturantRepository.getResturantListForLocation(
//                                String.valueOf(location.getLatitude()),
//                                String.valueOf(location.getLongitude())
//                        )
//                                .flatMap(resturants -> mLocalDBRepository.resetDBForNewLocation(context)
//                                        .subscribeOn(Schedulers.io())
//                                        .observeOn(Schedulers.io())
//                                        .flatMap(
//                                                result -> Flowable.fromIterable(resturants)
//                                        ))
//                                .flatMap(resturant -> mLocalDBRepository.insertResturantIntoDB(context, resturant)
//                                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.io()))
//                                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
//                        .subscribe(result -> {
//                        }, error -> {
//                            DDLog.e(TAG, error.getMessage());
//                            mBaseView.dismissLoading();
//                            mBaseView.handleError(error);
//                        }, () -> {
//                            mBaseView.dismissLoading();
//                            DDResturantApiClient.mCurrentOffset++;
//                            mBaseView.onResturantsDownloaded();
//                        })
//        );
    }

    private void saveResturantsToDB(List<Resturant> resturants,Context context) {
        DDLog.d(TAG,"saveResturantsToDB");
        mCompositeDisposable.add(
        mLocalDBRepository.resetDBForNewLocation(context)
                .flatMap(result -> Flowable.fromIterable(resturants))
                .flatMap(resturant -> mLocalDBRepository.insertResturantIntoDB(context,resturant))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result1 -> {},
                        error -> {},
                        () -> {
                            DDResturantApiClient.mCurrentOffset++;
                            mBaseView.onResturantsDownloaded();
                        }
                )
        );
    }

    @Override
    public void doesLocalDBHaveData(Context context) {
        DDLog.d(TAG, "doesLocalDBHaveData");
        mLocalDBRepository.getNumberOfResturantsInDB(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        size -> {
                            DDLog.d(TAG, "Number of Resturants is " + size);
                            mBaseView.showOfflineAccessMode();
                        }, error -> {
                            DDLog.e(TAG, error.getMessage());
                        }
                );
    }
}
