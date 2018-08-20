package abhishekdewan101.com.doordashlite.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Items;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ResturantDao {

    @Query("SELECT * FROM resturants")
    Single<List<Resturant>> getAllCachedResturants();

    @Query("SELECT * FROM items WHERE mResturantId = :resturantId")
    Flowable<List<Items>> getAllItemsForResturant(long resturantId);

    @Query("SELECT * FROM resturants where mBusinessName LIKE :name")
    Single<List<Resturant>> getAllResturantsStartingWith(String name);

    @Query("SELECT * FROM resturants where mBusinessId = :id")
    Single<Resturant> getResturantFromId(long id);

    @Query("SELECT * FROM resturants ORDER BY mAverageRating DESC")
    Single<List<Resturant>> getAllResturantsByPopularity();

    @Query("SELECT * FROM resturants ORDER BY mPriceRange ASC")
    Single<List<Resturant>> getAllResturantsByPrice();

    @Query("SELECT * FROM resturants ORDER BY mDeliveryFee ASC")
    Single<List<Resturant>> getAllResturantsByDeliveryFee();

    @Query("SELECT * FROM resturants WHERE mStatusType = 'open'")
    Single<List<Resturant>> getAllOpenResturants();

    @Query("SELECT * FROM resturants WHERE mStatusType = 'open' ORDER BY mDelieveryTime ASC")
    Single<List<Resturant>> getAllResturantsByDeliveryTime();

    @Query("SELECT * FROM resturants WHERE mTags IN (:tags)")
    Single<List<Resturant>> getResturantsFilteredByTags(List<String> tags);

    @Query("SELECT mTags FROM resturants")
    Single<List<String>> getAllTagsFromResturants();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResturant(Resturant resturant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Items item);

    @Query("SELECT COUNT(*) FROM resturants")
    Single<Integer> provideNumberOfResturantsInDB();

    @Query("DELETE FROM resturants")
    void resetResturantsForNewLocation();

    @Query("DELETE FROM items")
    void resetItemsForNewLocation();

}
