package abhishekdewan101.com.doordashlite.features.base;

import abhishekdewan101.com.doordashlite.core.repository.LocationRepository;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends BaseView> {

    protected final String TAG = DDConstants.PREFIX + getClass().getSimpleName();

    protected LocationRepository mLocationRepository;

    protected V mBaseView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V view) {
        attachView(view);
        mLocationRepository = new LocationRepository();
    }

    private void attachView(V view) {
        mBaseView = view;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    public void detachView() {
        disposeDisposable();
        mBaseView = null;
    }

    private void disposeDisposable() {
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }


}
