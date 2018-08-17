package abhishekdewan101.com.doordashlite.features.launcher;

import android.content.Context;

import abhishekdewan101.com.doordashlite.features.base.BaseView;

public interface LauncherContract {
    interface LauncherView extends BaseView {
        void onResturantsDownloaded();
        void showOfflineAccessMode();
    }

    interface LauncherActions {
        void getResturantsForCurrentLocation(Context context);
        void doesLocalDBHaveData(Context context);
    }
}
