package abhishekdewan101.com.doordashlite.utils;

import android.Manifest;

import abhishekdewan101.com.doordashlite.R;

public class DDConstants {

    public static final String PREFIX = "DDLite -> ";

    public static final String BASE_URL = "https://api.doordash.com";

    public static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static int [] RESTURANT_LIST_BG = new int[]{
            R.drawable.resturant_list_view_holder_background_purple,
            R.drawable.resturant_list_view_holder_background_green,
            R.drawable.resturant_list_view_holder_background_blue,
            R.drawable.resturant_list_view_holder_background_red
    };

}
