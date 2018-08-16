package abhishekdewan101.com.doordashlite.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class PermissionCheckUtil {

    private final String TAG = DDConstants.PREFIX + PermissionCheckUtil.class.getSimpleName();

    public static boolean checkForPermission(Context context, String[] permissions) {
        boolean isPermissionPresent = true;

        for(String permission: permissions) {
            if (ContextCompat.checkSelfPermission(context,permission) != PERMISSION_GRANTED) {
                isPermissionPresent = false;
                break;
            }
        }
        return isPermissionPresent;
    }

    public static boolean doesAppHaveAllPermissions(String[] permission, int[] results) {
        boolean isPermissionPresent = true;

        if (results.length == 0) {
            isPermissionPresent = false;
        } else {
            for(int result: results) {
                if (result != PERMISSION_GRANTED) {
                    isPermissionPresent = false;
                    break;
                }
            }
        }

        return isPermissionPresent;
    }
}
