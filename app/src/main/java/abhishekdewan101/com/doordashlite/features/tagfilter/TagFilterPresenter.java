package abhishekdewan101.com.doordashlite.features.tagfilter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abhishekdewan101.com.doordashlite.core.repository.LocalDBRepository;
import abhishekdewan101.com.doordashlite.data.model.TagListModel;
import abhishekdewan101.com.doordashlite.features.base.BasePresenter;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class TagFilterPresenter extends BasePresenter<TagFilterActivity> implements TagFilterContract.TagFilterActions {

    LocalDBRepository mLocalDBRepository;

    public TagFilterPresenter(TagFilterActivity view) {
        super(view);
        mLocalDBRepository = new LocalDBRepository();
    }

    @Override
    public void getListOfTags(Context context) {
        DDLog.d(TAG, "getListOfTags");
        mCompositeDisposable.add(
                mLocalDBRepository.getAllTagsFromResturant(context)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                tags -> {
                                   processListOfTags(tags);
                                }, error -> {
                                    mBaseView.handleError(error);
                                }
                        )
        );
    }

    private void processListOfTags(List<String> tags) {
        DDLog.d(TAG,"processListOfTags");
        mCompositeDisposable.add(
                Flowable.fromIterable(tags)
                        .filter(tag -> !tag.isEmpty())
                        .flatMap(tag1 -> {
                            List<String> mList = new ArrayList<>();
                            if (tag1.contains("::")) {
                                mList.addAll(Arrays.asList(tag1.split("::")));
                            } else {
                                mList.add(tag1);
                            }
                            return Flowable.fromIterable(mList);
                        })
                        .map(s -> new TagListModel(s))
                        .toList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                tags1 -> {
                                    mBaseView.showListOfTags(tags1);
                                },error -> {
                                    DDLog.e(TAG,error.getMessage());
                                }
                        )
        );
    }
}
