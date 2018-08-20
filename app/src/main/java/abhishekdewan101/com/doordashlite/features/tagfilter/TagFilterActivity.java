package abhishekdewan101.com.doordashlite.features.tagfilter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.TagListModel;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.tagfilter.tagview.TagListAdapater;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static abhishekdewan101.com.doordashlite.features.home.HomeScreenActivity.FILTER_TAG_ACTIVITY_RESULT;

public class TagFilterActivity extends BaseActivity<TagFilterPresenter> implements TagFilterContract.TagFilterView {

    public static final String TAG_LIST = "TAG_LIST";
    @BindView(R.id.tagsList)
    RecyclerView mTagList;
    private TagListAdapater mAdapter;

    @Override
    protected TagFilterPresenter createPresenter() {
        return new TagFilterPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_tags);
        mUnBinder = ButterKnife.bind(this);
        mPresenter.getListOfTags(this);
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

    @OnClick(R.id.filterSelection)
    public void filterSelectionDone() {
        Intent returnIntent = new Intent();
        Bundle b = new Bundle();
        b.putStringArrayList(TAG_LIST, (ArrayList<String>) mAdapter.getSelectedTags());
        returnIntent.putExtra(FILTER_TAG_ACTIVITY_RESULT,b);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void showListOfTags(List<TagListModel> tags) {
        mTagList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TagListAdapater();
        mAdapter.setAdapterData(tags);
        mTagList.setAdapter(mAdapter);
    }
}
