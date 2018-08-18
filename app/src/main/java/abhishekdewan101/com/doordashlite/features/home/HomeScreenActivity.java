package abhishekdewan101.com.doordashlite.features.home;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.data.local.ResturantDatabase;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.features.home.resturantlist.ResturantListAdapter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeScreenActivity extends BaseActivity<HomeScreenPresenter>
        implements HomeScreenContract.HomeScreenView,ResturantListAdapter.ResturantListInterface {

    @BindView(R.id.mainResturantList)
    RecyclerView mResturantList;


    ResturantListAdapter mAdapter;

    @Override
    protected HomeScreenPresenter createPresenter() {
        return new HomeScreenPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mUnBinder = ButterKnife.bind(this);
        initialize();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void handleError(Throwable throwable) {
        mAdapter.addAdapaterData(null);
    }

    @Override
    public void onResturantsLoaded(List<Resturant> resturants) {
        DDLog.d(TAG,"resturantsLoaded");
        DDLog.d(TAG,"Resturant Size - "+resturants.size());
        mResturantList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ResturantListAdapter();
        mAdapter.setAdapterData(resturants);
        mAdapter.setResturantListInterface(this);
        mResturantList.setAdapter(mAdapter);
    }

    @Override
    public void onMoreResturantsDownloaded(List<Resturant> resturants) {
        mAdapter.addAdapaterData(resturants);
    }

    private void initialize() {
        mPresenter.getResturantList(this);
    }

    @Override
    public void loadMoreResturants() {
        DDLog.d(TAG,"loadMoreResturants-Activity");
        mPresenter.loadMoreResturants(this);
    }
}
