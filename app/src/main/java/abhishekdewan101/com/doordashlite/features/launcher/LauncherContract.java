package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import abhishekdewan101.com.doordashlite.features.base.BaseView;

public interface LauncherContract {
    interface LauncherView extends BaseView {
        void onResturantsDownloaded();
    }

    interface LauncherActions {
        void getResturantsForCurrentLocation(Context context);
    }
}
