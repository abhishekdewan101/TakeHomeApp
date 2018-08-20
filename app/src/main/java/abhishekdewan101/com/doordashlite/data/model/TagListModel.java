package abhishekdewan101.com.doordashlite.data.model;

public class TagListModel {
    public String mTag;
    public boolean mChecked;

    public TagListModel(String tag) {
        mTag = tag;
        mChecked = false;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }
}
