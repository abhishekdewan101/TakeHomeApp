package abhishekdewan101.com.doordashlite.features.tagfilter.tagview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.TagListModel;

public class TagListAdapater extends RecyclerView.Adapter<TagListViewHolder> {

    List<TagListModel> mAdapterData;

    @NonNull
    @Override
    public TagListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tags_layout_item,viewGroup,false);
        return new TagListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagListViewHolder tagListViewHolder, int i) {
        tagListViewHolder.mTagName.setText(mAdapterData.get(i).mTag);
        tagListViewHolder.mTagCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            mAdapterData.get(i).mChecked = b;
        });
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void setAdapterData(List<TagListModel> data) {
        mAdapterData = data;
    }

    public List<String> getSelectedTags() {
        List<String> strings = new ArrayList<>();
        for(TagListModel model : mAdapterData) {
            if (model.isChecked()) {
                strings.add(model.mTag);
            }
        }
        return strings;
    }
}
