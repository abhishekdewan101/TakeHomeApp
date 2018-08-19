package abhishekdewan101.com.doordashlite.features.resturantdetails.popularItemlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import abhishekdewan101.com.doordashlite.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.featureItemCost)
    TextView mFeatureItemCost;

    @BindView(R.id.featureItemName)
    TextView mFeatureItemName;

    @BindView(R.id.featuredItemHeader)
    ImageView mFeatureItemHeader;

    @BindView(R.id.featureItemDescription)
    TextView mFeatureItemDescription;

    public PopularListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
