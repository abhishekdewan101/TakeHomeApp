package abhishekdewan101.com.doordashlite.features.home.resturantlist;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import abhishekdewan101.com.doordashlite.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.resturantName)
    public TextView mResturantName;

    @BindView(R.id.viewHolderBackground)
    public LinearLayout mViewHolderBackground;

    @BindView(R.id.resturantImage)
    public ImageView mResturantImage;

    @BindView(R.id.ratingText)
    public TextView mRatingText;

    @BindView(R.id.ratingCount)
    public TextView mRatingCount;

    @BindView(R.id.resturantCostText)
    public TextView mResturantCostText;

    @BindView(R.id.resturantTimeText)
    public TextView mResturantTimeText;

    @BindView(R.id.resturantDescription)
    public TextView mResturantDetails;

    @BindView(R.id.resturantSurgePriceText)
    public TextView mSurgePricingText;

    public ResturantListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
