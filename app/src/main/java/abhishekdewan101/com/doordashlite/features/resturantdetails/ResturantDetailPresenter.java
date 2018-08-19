package abhishekdewan101.com.doordashlite.features.resturantdetails;

import android.content.Context;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.core.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ResturantDetailPresenter extends BasePresenter<ResturantDetailActivity> implements ResturantDetailContract.ResturantDetailActions {

    LocalDBRepository mLocalDBRepository;
    ResturantRepository mResturantRepository;

    public ResturantDetailPresenter(ResturantDetailActivity view) {
        super(view);
        mLocalDBRepository = new LocalDBRepository();
        mResturantRepository = new ResturantRepository();
    }

    @Override
    public void loadResturantDetails(Context context, int resturantId) {
        DDLog.d(TAG,"loadResturantDetails");
        mCompositeDisposable.add(
                mLocalDBRepository.getResturantDetailsForId(context,resturantId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                resturant -> {
                                    mBaseView.showResturantDetails(resturant);
                                },error -> {
                                    mBaseView.handleError(error);
                                }
                        )
        );

    }

    @Override
    public void loadResturantPopularItems(int resturantId) {
        DDLog.d(TAG,"loadResturantPopularItems");
        mBaseView.showLoading();
        mCompositeDisposable.add(
                mResturantRepository.getResturantDetailsForID(resturantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resturant -> {
                            mBaseView.dismissLoading();
                            mBaseView.showPopularItemList(resturant.mMenus);
                        },error -> {
                            mBaseView.dismissLoading();
                            mBaseView.handleError(error);
                        }
                )

        );
    }
}
