package abhishekdewan101.com.doordashlite.features.launcher;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.repository.LocationRepository;
import abhishekdewan101.com.doordashlite.data.repository.ResturantRepository;
import abhishekdewan101.com.doordashlite.features.base.BaseActivity;
import abhishekdewan101.com.doordashlite.features.home.HomeScreenActivity;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import abhishekdewan101.com.doordashlite.utils.PermissionCheckUtil;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LauncherActivity extends BaseActivity {

    public static final int REQUIRED_PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        verifyRequiredPermissions();
    }

    private void verifyRequiredPermissions() {
        DDLog.d(TAG, "verifyRequiredPermission");
        boolean appHasPermission = PermissionCheckUtil.checkForPermission(this, DDConstants.REQUIRED_PERMISSIONS);
        if (appHasPermission) {
            DDLog.d(TAG, "App Has Permission to Get Location.");
            getUserLocation();
        } else {
            requestRequiredPermission();
        }
    }

    private void getUserLocation() {
        LocationRepository locationRepository = new LocationRepository();
        ResturantRepository resturantRepository = new ResturantRepository();
        locationRepository.getUserCurrentLocation(this)
                .flatMap(location -> resturantRepository.getResturantListForLocation(String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude()))
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io()))
                .subscribe(resturants -> {
                    DDLog.d(TAG, "Number of Resturants - " + resturants.size());
                }, error -> {
                    DDLog.e(TAG, error.getMessage());

                }, () -> {
                    startActivity(new Intent(this, HomeScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    this.finish();
                });
    }

    private void requestRequiredPermission() {
        ActivityCompat.requestPermissions(this, DDConstants.REQUIRED_PERMISSIONS, REQUIRED_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean appHasPermission = PermissionCheckUtil.doesAppHaveAllPermissions(permissions, grantResults);
        if (appHasPermission) {
            getUserLocation();
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


    //    DDResturantDBManager mRemoteDBManger;
//
//    private ResturantDatabase mDatabase;


//    private void nukeTable() {
//        mDatabase.resturantDao().resetForNewLocation();
//    }
//
//    mDatabase = Room.databaseBuilder(this.getApplication(), ResturantDatabase.class,"resturants.db").build();
////
//    mRemoteDBManger = DDResturantDBManager.getInstance();
//
//        Flowable.create(e -> {
//        nukeTable();
//        e.onNext(1);
//        e.onComplete();
//    }, BackpressureStrategy.LATEST)
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//            result -> {
//
//    },
//    error -> {
//        Log.e(TAG,error.getLocalizedMessage());
//    }
//        );
//
//
//        mRemoteDBManger.getResturantsForLocation("37.422740","-122.139956")
//                .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())
//            .flatMap(resturants -> Flowable.fromIterable(resturants))
//            .subscribe(
//            resturant -> {
////                            Log.d(TAG,"Resturant Name - " + resturant.mName);
////                            Log.d(TAG,"Resturants Tags - " + resturant.mTags);
//        mDatabase.resturantDao().insertResturant(resturant);
//    },
//    error -> {
//        Log.e(TAG,error.getLocalizedMessage());
//    },
//            () -> {
//        startActivity(new Intent(this,HomeScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//        this.finish();
//    }
//                );
//
//    LocationRepository locationRepository = new LocationRepository();
//        locationRepository.getUserCurrentLocation(this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(location -> {
//        DDLog.d(TAG,"Lat - " + location.getLatitude());
//        DDLog.d(TAG,"Lng - " + location.getLongitude());
//    }, error -> {
//        DDLog.e(TAG,error.getMessage());
//    });
}
