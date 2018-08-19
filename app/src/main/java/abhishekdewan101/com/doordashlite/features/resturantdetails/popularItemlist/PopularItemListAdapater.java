package abhishekdewan101.com.doordashlite.features.resturantdetails.popularItemlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.Items;

public class PopularItemListAdapater extends RecyclerView.Adapter<PopularListViewHolder> {

    List<Items> mAdapterData;

    @NonNull
    @Override
    public PopularListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item_viewholder,viewGroup,false);
        return new PopularListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListViewHolder popularListViewHolder, int i) {
        popularListViewHolder.mFeatureItemCost.setText("$" + Double.toString((double)mAdapterData.get(i).mPrice/100));
        Glide.with(popularListViewHolder.itemView).load(mAdapterData.get(i).mImageUrl)
                .apply(RequestOptions.centerCropTransform()).into(popularListViewHolder.mFeatureItemHeader);
        popularListViewHolder.mFeatureItemName.setText(mAdapterData.get(i).mName);
        popularListViewHolder.mFeatureItemDescription.setText(mAdapterData.get(i).mDescription);
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void setAdapterData(List<Items> items) {
        mAdapterData = items;
        this.notifyDataSetChanged();
    }
}
