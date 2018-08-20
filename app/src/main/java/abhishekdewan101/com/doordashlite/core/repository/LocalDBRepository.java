package abhishekdewan101.com.doordashlite.core.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.local.ResturantDatabase;
import abhishekdewan101.com.doordashlite.data.model.Items;
import abhishekdewan101.com.doordashlite.data.model.Menu;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.utils.DDConstants;
import abhishekdewan101.com.doordashlite.utils.DDLog;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Repository that exposes the local sqllite database functionality to be used
 * by the presenters.
 */

public class LocalDBRepository {

    protected final String TAG = DDConstants.PREFIX + getClass().getSimpleName();

    private static ResturantDatabase mResturantDatabase;

    //Get total number of resturants in the DB
    public Single<Integer> getNumberOfResturantsInDB(Context context) {
        DDLog.d(TAG,"getAllResturantFromDB");
        return getDBFlowable(context).firstOrError().flatMap(
          resturantDatabase -> resturantDatabase.resturantDao().provideNumberOfResturantsInDB()
        );
    }

    //Get All the Resturants in the DB
    public Single<List<Resturant>> getAllResturantsInDB(Context context) {
        DDLog.d(TAG,"getAllResturantsInDB");
        return getDBFlowable(context).firstOrError().flatMap(
          resturantDatabase -> resturantDatabase.resturantDao().getAllOpenResturants()
        );
    }

    // Get resturants who's name start with provided String
    public Single<List<Resturant>> getAllResturantsStartingWith(Context context,String name) {
        DDLog.d(TAG,"getAllResturantsStartingWith");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllResturantsStartingWith(name)
        );
    }

    // Get resturants filtered by popularity (DESC)
    public Single<List<Resturant>> getAllResturantsByPopularity(Context context) {
        DDLog.d(TAG,"getAllResturantsByPopularity");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllResturantsByPopularity()
        );
    }

    // Get Resturants filtered by Price Range (DESC)
    public Single<List<Resturant>> getAllResturantsByPrice(Context context) {
        DDLog.d(TAG,"getAllResturantsByPopularity");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllResturantsByPrice()
        );
    }

    // Get Resturants filtered by Delivery Time(ASC)
    public Single<List<Resturant>> getAllResturantsByDeliveryTime(Context context) {
        DDLog.d(TAG,"getAllResturantsByDeliveryTime");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllResturantsByDeliveryTime()
        );
    }

    // Get resturants that are tagged with provided tags.
    public Single<List<Resturant>> getAllResturantsFilteredByTags(Context context,List<String> tags) {
        DDLog.d(TAG,"getAllResturantsByDeliveryTime");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getResturantsFilteredByTags(tags)
        );
    }

    // Get all the tags
    public Single<List<String>> getAllTagsFromResturant(Context context) {
        DDLog.d(TAG,"getAllResturantsByDeliveryTime");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllTagsFromResturants()
        );
    }

    // Get resturants filtered by delievery fee (ASC)
    public Single<List<Resturant>> getAllResturantsByDelieveryFee(Context context) {
        DDLog.d(TAG,"getAllResturantsByDeliveryTime");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllResturantsByDeliveryFee()
        );
    }

    // Get all resturants that are opened.
    public Single<List<Resturant>> getAllOpenResturants(Context context) {
        DDLog.d(TAG,"getAllOpenResturants");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllOpenResturants()
        );
    }

    // Get resturants details for a particular resturant.
    public Single<Resturant> getResturantDetailsForId(Context context,long id) {
        DDLog.d(TAG,"getResturantDetailsForId");
        return getDBFlowable(context).firstOrError().flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getResturantFromId(id)
        );
    }

    // Reset database to be populated with new information.
    public Flowable<Integer> resetDBForNewLocation(Context context) {
        DDLog.d(TAG,"resetDBForNewLocation");
        return getDBFlowable(context).flatMap(
                resturantDatabase -> {
                    resturantDatabase.resturantDao().resetItemsForNewLocation();
                    resturantDatabase.resturantDao().resetResturantsForNewLocation();
                    return Flowable.just(1);
                }
        );
    }

    // Get all popular items if present for a resturant.
    public Flowable<List<Items>> getItemsForResturant(Context context,long id) {
        DDLog.d(TAG,"getItemsForResturant");
        return getDBFlowable(context).flatMap(
                resturantDatabase -> resturantDatabase.resturantDao().getAllItemsForResturant(id)
        );
    }

    // Insert a resturant into the database.
    public Flowable<Integer> insertResturantIntoDB(Context context,Resturant resturant) {
        DDLog.d(TAG,"insertResturantIntoDB with Resturant - " + resturant.mName);
        return getDBFlowable(context).flatMap(
          resturantDatabase -> {
            resturantDatabase.resturantDao().insertResturant(resturant);
            if (resturant.mMenus != null && resturant.mMenus.size() >= 1) {
                for (Menu menu : resturant.mMenus) {
                    if (menu.mPopularItems != null && menu.mPopularItems.size() > 1) {
                        for (Items item : menu.mPopularItems) {
                            item.mResturantId = resturant.getId();
                            resturantDatabase.resturantDao().insertItem(item);
                        }
                    }
                }
            }
            return Flowable.just(1);
          }
        );
    }


    // Get a database object as a Flowable to be used as part of a RxChain
    private Flowable<ResturantDatabase> getDBFlowable(Context context) {
        DDLog.d(TAG,"getDBFlowable");

        if (mResturantDatabase != null) {
            return Flowable.just(mResturantDatabase);
        } else {
            mResturantDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ResturantDatabase.class,"resturants.db")
                    .fallbackToDestructiveMigration().build();
            return Flowable.just(mResturantDatabase);
        }
    }


}
