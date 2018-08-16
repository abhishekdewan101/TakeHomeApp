package abhishekdewan101.com.doordashlite.features.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import abhishekdewan101.com.doordashlite.utils.DDConstants;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected String TAG = DDConstants.PREFIX + this.getClass().getSimpleName();

    protected P mPresenter;
    private Unbinder mUnBinder;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initButterKnife();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initButterKnife();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initButterKnife();
    }

    private void initButterKnife() {
        if(mUnBinder == null) {
            mUnBinder = ButterKnife.bind(this);
        }
    }
}
