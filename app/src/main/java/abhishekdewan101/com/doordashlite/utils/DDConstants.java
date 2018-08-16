package abhishekdewan101.com.doordashlite.utils;

import android.Manifest;

public class DDConstants {

    public static final String PREFIX = "DDLite -> ";

    public static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
}
