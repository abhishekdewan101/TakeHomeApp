package abhishekdewan101.com.doordashlite.features.tagfilter;

import android.content.Context;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.TagListModel;
import abhishekdewan101.com.doordashlite.features.base.BaseView;

public interface TagFilterContract {
    interface TagFilterView extends BaseView {
        void showListOfTags(List<TagListModel> tags);
    }

    interface TagFilterActions {

        void getListOfTags(Context context);
    }
}
