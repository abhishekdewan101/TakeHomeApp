package abhishekdewan101.com.doordashlite.features.resturantdetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.Items;
import abhishekdewan101.com.doordashlite.data.model.Menu;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.resturantdetails.popularItemlist.PopularItemListAdapater;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResturantDetailActivity extends BaseActivity<ResturantDetailPresenter> implements ResturantDetailContract.ResturantDetailView{

    public static final String INTENT_RESTURANT_ID = "INTENT_RESTURANT_ID";
    public int mResturantId;


    @BindView(R.id.resturantName)
    TextView mResturantName;

    @BindView(R.id.resturantDetails)
    TextView mResturantDetails;

    @BindView(R.id.resturantDelieveryTimeText)
    TextView mResturantDelieveryTime;

    @BindView(R.id.resturantDelieveryCost)
    TextView mResturantDelieveryCost;

    @BindView(R.id.popularItemList)
    RecyclerView mPopularItemList;

    @BindView(R.id.loadingAnimation)
    LottieAnimationView mLoadingAnimation;

    @BindView(R.id.noFeaturedItemLayout)
    LinearLayout mNoListLayout;

    @BindView(R.id.yelpRating)
    TextView mYelpRating;

    private PopularItemListAdapater mPopularItemListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_detail);
        mUnBinder = ButterKnife.bind(this);
        mResturantId = getIntent().getIntExtra(INTENT_RESTURANT_ID,-1);
        mPresenter.loadResturantDetails(this.getApplicationContext(),mResturantId);
    }

    @Override
    protected ResturantDetailPresenter createPresenter() {
        return new ResturantDetailPresenter(this);
    }

    @Override
    public void showLoading() {
        mLoadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        mLoadingAnimation.setVisibility(View.GONE);
    }

    @Override
    public void handleError(Throwable throwable) {
        DDLog.e(TAG,throwable.getMessage());
    }

    @OnClick(R.id.backButtonLayout)
    public void onBackButtonClicked() {
        this.finish();
    }

    @Override
    public void showPopularItemList(List<Items> items) {
        if(items.isEmpty()) {
            showEmptyListView();
            return;
        }
        mPopularItemList.setLayoutManager(new LinearLayoutManager(this));
        mPopularItemListAdapter = new PopularItemListAdapater();
        mPopularItemList.setAdapter(mPopularItemListAdapter);
        mPopularItemListAdapter.setAdapterData(items);
        mPopularItemList.setVisibility(View.VISIBLE);
    }

    private void showEmptyListView() {
        mPopularItemList.setVisibility(View.GONE);
        mNoListLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResturantDetails(Resturant resturant) {
        mResturantName.setText(resturant.mBusiness.mBusinessName);
        mResturantDetails.setText(resturant.mDescription);
        mResturantDelieveryCost.setText("$" + (int) resturant.mDeliveryFee);
        mResturantDelieveryTime.setText(Integer.toString(resturant.mDelieveryTime));
        mYelpRating.setText(String.format("%.2f", resturant.mAverageRating));
        mPresenter.loadResturantPopularItems(this,resturant.mId);
    }
}
