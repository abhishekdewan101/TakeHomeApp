package abhishekdewan101.com.doordashlite.data.repository;

import android.content.Context;

import abhishekdewan101.com.doordashlite.utils.DDConstants;

public class BaseRepository {

    public static final String TAG = DDConstants.PREFIX + BaseRepository.class.getSimpleName();

    public Context mAppContext;

    public BaseRepository(Context context) {
        mAppContext = context;
    }
}
