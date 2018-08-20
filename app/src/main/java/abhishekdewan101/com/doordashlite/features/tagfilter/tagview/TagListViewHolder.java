package abhishekdewan101.com.doordashlite.features.tagfilter.tagview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import abhishekdewan101.com.doordashlite.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TagListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tagSelect)
    CheckBox mTagCheckBox;

    @BindView(R.id.tagName)
    TextView mTagName;

    public TagListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
