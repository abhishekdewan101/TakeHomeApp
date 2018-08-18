package abhishekdewan101.com.doordashlite.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResturant(Resturant resturant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Items item);

    @Query("SELECT COUNT(*) FROM resturants")
    Flowable<Integer> provideNumberOfResturantsInDB();

    @Query("DELETE FROM resturants")
    void resetResturantsForNewLocation();

    @Query("DELETE FROM items")
    void resetItemsForNewLocation();

}
