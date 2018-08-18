package abhishekdewan101.com.doordashlite.features.home.resturantlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import abhishekdewan101.com.doordashlite.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.resturantName)
    public TextView mResturantName;

    public ResturantListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
