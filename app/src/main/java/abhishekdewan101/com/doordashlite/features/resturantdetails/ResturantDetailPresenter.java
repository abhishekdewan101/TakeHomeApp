package abhishekdewan101.com.doordashlite.features.resturantdetails;

import android.content.Context;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ResturantDetailPresenter extends BasePresenter<ResturantDetailActivity> implements ResturantDetailContract.ResturantDetailActions {

    LocalDBRepository mLocalDBRepository;

    public ResturantDetailPresenter(ResturantDetailActivity view) {
        super(view);
        mLocalDBRepository = new LocalDBRepository();
    }

    @Override
    public void loadResturantDetails(Context context, int resturantId) {
        mLocalDBRepository.getResturantDetailsForId(context,resturantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resturant -> {
                            mBaseView.showResturantDetails(resturant);
                        },error -> {
                            mBaseView.handleError(error);
                        }
                );
    }
}
