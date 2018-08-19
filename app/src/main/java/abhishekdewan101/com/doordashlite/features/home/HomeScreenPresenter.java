package abhishekdewan101.com.doordashlite.features.home;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.core.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.data.remote.DDResturantApiClient;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class HomeScreenPresenter extends BasePresenter<HomeScreenContract.HomeScreenView> implements HomeScreenContract.HomeScreenActions {

    private LocalDBRepository mLocalDBRepository;
    private ResturantRepository mResturantRepository;

    public HomeScreenPresenter(HomeScreenActivity view) {
        super(view);
        mLocalDBRepository = new LocalDBRepository();
        mResturantRepository = new ResturantRepository();
    }

    @Override
    public void getResturantList(Context context) {
        DDLog.d(TAG, "getResturantList");
        mLocalDBRepository.getAllResturantsInDB(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resturants -> {
                            mBaseView.onResturantsLoaded(resturants);
                        }, error -> {
                            mBaseView.handleError(error);
                        }
                );
    }

    @Override
    public void loadMoreResturants(Context context) {
        DDLog.d(TAG, "loadMoreResturantData");
        mLocationRepository.getUserCurrentLocation(context)
                .flatMap(location -> mResturantRepository.getResturantListForLocation(
                        String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude())
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
                .subscribe(
                        resturants -> {
                            mBaseView.onMoreResturantsDownloaded(resturants);
                            saveResturantsToDB(resturants, context);
                            DDResturantApiClient.mCurrentOffset++;
                        }, errors -> {
                            DDLog.e(TAG, errors.getMessage());
                            mBaseView.handleError(errors);
                        }
                );
    }

    @Override
    public void getResturantsStartingWith(Context context,String s) {
        DDLog.d(TAG,"getResturantsStartingWith");
        mLocalDBRepository.getAllResturantsStartingWith(context, s + "%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                  resturants -> {
                      mBaseView.replaceResturants(resturants);
                  }, error -> {
                      mBaseView.handleError(error);
                        }
                );
    }

    @Override
    public void getResturantsFilterByPouplarity(Context context) {
        DDLog.d(TAG,"getResturantsFilterByPopularity");
        mLocalDBRepository.getAllResturantsByPopularity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resturants -> {
                            mBaseView.replaceResturants(resturants);
                        },error -> {
                            mBaseView.handleError(error);
                        }
                );
    }

    private void saveResturantsToDB(List<Resturant> resturants, Context context) {
        DDLog.d(TAG, "saveResturantsToDB");
        Flowable.fromIterable(resturants).flatMap(
                resturant -> mLocalDBRepository.insertResturantIntoDB(context, resturant)
        ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        result -> {
                        },
                        error -> {
                            mBaseView.handleError(error);
                        }
                );
    }
}
