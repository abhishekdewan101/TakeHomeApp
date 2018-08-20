package abhishekdewan101.com.doordashlite.features.home;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.data.local.ResturantDatabase;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.features.home.resturantlist.ResturantListAdapter;
import abhishekdewan101.com.doordashlite.features.resturantdetails.ResturantDetailActivity;
import abhishekdewan101.com.doordashlite.features.tagfilter.TagFilterActivity;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static abhishekdewan101.com.doordashlite.features.resturantdetails.ResturantDetailActivity.INTENT_RESTURANT_ID;
import static abhishekdewan101.com.doordashlite.features.tagfilter.TagFilterActivity.TAG_LIST;

public class HomeScreenActivity extends BaseActivity<HomeScreenPresenter>
        implements HomeScreenContract.HomeScreenView, ResturantListAdapter.ResturantListAdapterInterface, FilterBottomSheetDialog.FilterBottomSheetInterface{

    public static final String FILTER_BOTTOM_SHEET_DIALOG = "FILTER_BOTTOM_SHEET_DIALOG";
    public static final int FILTER_TAG_ACTIVITY_CODE = 2029;
    public static final String FILTER_TAG_ACTIVITY_RESULT = "ACTIVITY_TAG_RESULT";
    @BindView(R.id.mainResturantList)
    RecyclerView mResturantList;

    @BindView(R.id.searchBar)
    EditText mSearchBar;

    ResturantListAdapter mAdapter;

    boolean mIsManaulChange = false;

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
        setupSearchBar();
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


    @OnClick(R.id.filterButton)
    public void showFilterBottomSheet() {
        FilterBottomSheetDialog dialog = FilterBottomSheetDialog.newInstance();
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager(), FILTER_BOTTOM_SHEET_DIALOG);
    }

    @Override
    public void filterByPopularity() {
        mIsManaulChange = true;
        mSearchBar.setText("");
        mPresenter.getResturantsFilterByPouplarity(this);
    }

    @Override
    public void filterByLowestDelivery() {
        mIsManaulChange = true;
        mSearchBar.setText("");
        mPresenter.getResturantsFilterByDeliveryFee(this);
    }

    @Override
    public void filterByOpenResturants() {
        mIsManaulChange = true;
        mSearchBar.setText("");
        mPresenter.getAllOpenResturants(this);
    }

    @Override
    public void filterByTags() {
        DDLog.d(TAG,"filterByTagsClicked");
        startActivityForResult(new Intent(this, TagFilterActivity.class), FILTER_TAG_ACTIVITY_CODE);
    }

    @Override
    public void filterByPrice() {
        mIsManaulChange = true;
        mSearchBar.setText("");
        mPresenter.getResturantsFilterByPrice(this);
    }

    @Override
    public void filterByFastestDelivery() {
        mIsManaulChange = true;
        mSearchBar.setText("");
        mPresenter.getResturantsFilterByDeliveryTime(this);
    }


    @Override
    public void onResturantsLoaded(List<Resturant> resturants) {
        DDLog.d(TAG,"resturantsLoaded");
        DDLog.d(TAG,"Resturant Size - "+resturants.size());
        mResturantList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ResturantListAdapter();
        mAdapter.setAdapterData(resturants);
        mAdapter.setListener(this);
        mResturantList.setAdapter(mAdapter);
    }

    @Override
    public void onMoreResturantsDownloaded(List<Resturant> resturants) {
        mAdapter.addAdapaterData(resturants);
    }

    @Override
    public void replaceResturants(List<Resturant> resturants) {
        mAdapter.setAdapterData(resturants);
    }

    private void initialize() {
        mPresenter.getResturantList(this);
    }


    private void setupSearchBar() {
        RxTextView.textChanges(mSearchBar)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString())
                .subscribe(
                        s -> {
                            if (!mIsManaulChange) {
                                if (s.length() > 0) {
                                    mPresenter.getResturantsStartingWith(getApplicationContext(),s);
                                } else {
                                    mPresenter.getResturantList(getApplicationContext());
                                }
                            } else {
                                mIsManaulChange = false;
                            }
                        },this::handleError
                );
    }

    @Override
    public void onResturantClicked(int id) {
        startActivity(new Intent(this, ResturantDetailActivity.class).putExtra(INTENT_RESTURANT_ID,id));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FILTER_TAG_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getBundleExtra(FILTER_TAG_ACTIVITY_RESULT);
                mPresenter.filterResturantsByTags(this,b.getStringArrayList(TAG_LIST));
            }
        }
    }
}
