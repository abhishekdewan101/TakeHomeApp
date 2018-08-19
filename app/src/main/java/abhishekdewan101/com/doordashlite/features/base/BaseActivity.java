package abhishekdewan101.com.doordashlite.features.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import abhishekdewan101.com.doordashlite.utils.DDConstants;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected String TAG = DDConstants.PREFIX + this.getClass().getSimpleName();

    protected P mPresenter;
    public Unbinder mUnBinder;

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

    protected boolean isThrowableNetworkError(Throwable throwable) {
        return (throwable instanceof SocketException) || (throwable instanceof UnknownHostException) ||
                (throwable instanceof SocketTimeoutException) || (throwable instanceof SSLException);
    }
}
