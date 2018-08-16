package abhishekdewan101.com.doordashlite.features.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;

public class BaseActivity extends AppCompatActivity {

    protected String TAG = DDConstants.PREFIX + this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
