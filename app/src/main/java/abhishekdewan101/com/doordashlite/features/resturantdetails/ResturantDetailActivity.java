package abhishekdewan101.com.doordashlite.features.resturantdetails;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Arrays;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantDetailActivity extends BaseActivity<ResturantDetailPresenter> implements ResturantDetailContract.ResturantDetailView{

    public static final String INTENT_RESTURANT_ID = "INTENT_RESTURANT_ID";
    public int mResturantId;

    @BindView(R.id.header_image)
    ImageView mHeaderImage;

    @BindView(R.id.resturantName)
    TextView mResturantName;

    @BindView(R.id.resturantTags)
    TextView mResturantTag;

    @BindView(R.id.resturantDetails)
    TextView mResturantDetails;

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

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void showResturantDetails(Resturant resturant) {
        if (!resturant.mHeaderImageUrl.isEmpty()) {
            Glide.with(this).load(resturant.mHeaderImageUrl).apply(RequestOptions.centerCropTransform()).into(mHeaderImage);
        } else if (!resturant.mCoverImageUrl.isEmpty()) {
            Glide.with(this).load(resturant.mCoverImageUrl).apply(RequestOptions.centerCropTransform()).into(mHeaderImage);
        } else {
            mHeaderImage.setVisibility(View.GONE);
        }

        mResturantName.setText(resturant.mBusiness.mBusinessName);
        mResturantTag.setText(Arrays.toString(resturant.mTags.toArray()));
        mResturantDetails.setText(resturant.mDescription);
    }
}
