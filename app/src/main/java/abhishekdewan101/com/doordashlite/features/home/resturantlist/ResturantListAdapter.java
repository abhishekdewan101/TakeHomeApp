package abhishekdewan101.com.doordashlite.features.home.resturantlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.utils.DDConstants;

public class ResturantListAdapter extends RecyclerView.Adapter<ResturantListViewHolder> {

    List<Resturant> mAdapterData;

    final int LOADING_OFFSET = 20;
    boolean mHasLoadingDataBeenInitiated = false;
    private ResturantListAdapterInterface mListener;

    @NonNull
    @Override
    public ResturantListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resturant_list_view_holder,viewGroup,false);
        return new ResturantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantListViewHolder resturantListViewHolder, int i) {
        resturantListViewHolder.mViewHolderBackground.setBackgroundResource(getDrawable(i));
        resturantListViewHolder.mResturantName.setText(mAdapterData.get(i).mBusiness.mBusinessName);
        Glide.with(resturantListViewHolder.mResturantImage).load(mAdapterData.get(i).mCoverImageUrl)
                .apply(RequestOptions.fitCenterTransform())
                .into(resturantListViewHolder.mResturantImage);
        resturantListViewHolder.mResturantDetails.setText(mAdapterData.get(i).mDescription);
        resturantListViewHolder.mRatingText.setText(Float.toString(mAdapterData.get(i).mAverageRating));
        resturantListViewHolder.mRatingCount.setText(Long.toString(mAdapterData.get(i).mResturantRating) + " ratings");
        resturantListViewHolder.itemView.setOnClickListener(view -> {
            mListener.onResturantClicked(mAdapterData.get(i).mBusiness.mBusinessId);
        });

        if (mAdapterData.get(i).mIsPriceSurging) {
            resturantListViewHolder.mSurgePricingText.setText("Yes");
        } else {
            resturantListViewHolder.mSurgePricingText.setText("No");
        }

        if (mAdapterData.get(i).mAsapTime.contains("Pre")) {
            resturantListViewHolder.mResturantTimeText.setText("Pre-Order");
        } else {
            resturantListViewHolder.mResturantTimeText.setText(mAdapterData.get(i).mAsapTime);
        }

        if (mAdapterData.get(i).mPriceRange == 1) {
            resturantListViewHolder.mResturantCostText.setText("Low");
        } else if (mAdapterData.get(i).mPriceRange == 2) {
            resturantListViewHolder.mResturantCostText.setText("Medium");
        } else {
            resturantListViewHolder.mResturantCostText.setText("High");
        }

    }

    private int getDrawable(int i) {
        if (i % 4 == 0) {
            return DDConstants.RESTURANT_LIST_BG[3];
        } else if (i % 3 == 0) {
            return DDConstants.RESTURANT_LIST_BG[2];
        } else if (i % 2 == 0) {
            return DDConstants.RESTURANT_LIST_BG[1];
        } else {
            return DDConstants.RESTURANT_LIST_BG[0];
        }
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void setAdapterData(List<Resturant> data) {
        mAdapterData = data;
        this.notifyDataSetChanged();
    }

    public void addAdapaterData(List<Resturant> data) {
        if (data == null) {
            mHasLoadingDataBeenInitiated = false;
            return;
        }
        int lastPosition = mAdapterData.size();
        mAdapterData.addAll(data);
        mHasLoadingDataBeenInitiated = false;
        this.notifyItemInserted(lastPosition);
    }

    public void setListener(ResturantListAdapterInterface listener) {
        mListener = listener;
    }

    public interface ResturantListAdapterInterface {
        void onResturantClicked(int id);
    }
}
