package abhishekdewan101.com.doordashlite.features.launcher;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.w3c.dom.Text;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.home.HomeScreenActivity;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import abhishekdewan101.com.doordashlite.utils.PermissionCheckUtil;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LauncherActivity extends BaseActivity<LauncherPresenter> implements LauncherContract.LauncherView {

    public static final int REQUIRED_PERMISSION_REQUEST_CODE = 200;

    @BindView(R.id.offlineModeLayout)
    LinearLayout mOfflineModeLayout;

    @BindView(R.id.loadingAnimation)
    LottieAnimationView mLoadingAnimation;

    @BindView(R.id.noButton)
    TextView mNoButton;

    @BindView(R.id.yesButton)
    TextView mYesButton;
    private long mStartTime;

    @Override
    protected LauncherPresenter createPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mUnBinder = ButterKnife.bind(this);
        verifyRequiredPermissions();
        mStartTime = System.currentTimeMillis();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @OnClick(R.id.noButton)
    public void noButtonClicked() {
        this.finish();
    }

    @OnClick(R.id.yesButton)
    public void yesButtonClicked() {
        onResturantsDownloaded();
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable.getMessage().contains("Timed Out")) {
            mPresenter.doesLocalDBHaveData(this);
        }
    }

    @Override
    public void onResturantsDownloaded() {
        DDLog.e(TAG,"Total Load Time Taken - " + (System.currentTimeMillis() - mStartTime));
        startActivity(new Intent(this, HomeScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        this.finish();
    }

    @Override
    public void showOfflineAccessMode() {
        DDLog.d(TAG,"Showing Offline Mode");
        mLoadingAnimation.setVisibility(View.GONE);
        mOfflineModeLayout.setVisibility(View.VISIBLE);
    }

    private void verifyRequiredPermissions() {
        DDLog.d(TAG, "verifyRequiredPermission");
        boolean appHasPermission = PermissionCheckUtil.checkForPermission(this, DDConstants.REQUIRED_PERMISSIONS);
        if (appHasPermission) {
            DDLog.d(TAG, "App Has Permission to Get Location.");
            appHasRequiredPermissions();
        } else {
            requestRequiredPermission();
        }
    }

    private void appHasRequiredPermissions() {
        mLoadingAnimation.setVisibility(View.VISIBLE);
        mPresenter.getResturantsForCurrentLocation(this);
    }

    private void requestRequiredPermission() {
        ActivityCompat.requestPermissions(this, DDConstants.REQUIRED_PERMISSIONS, REQUIRED_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean appHasPermission = PermissionCheckUtil.doesAppHaveAllPermissions(permissions, grantResults);
        if (appHasPermission) {
            appHasRequiredPermissions();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Location Permission Required")
                    .setMessage("We require the location permission in order to show you relevant resturants close to you. Please give us access to your location.")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        requestRequiredPermission();
                    })
                    .setNegativeButton("CANCEL", ((dialogInterface, i) -> {
                        finish();
                    })).create();
            alertDialog.show();
        }
    }
}
