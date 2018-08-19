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

    @Query("SELECT * FROM resturants where mBusinessName LIKE :name")
    Single<List<Resturant>> getAllResturantsStartingWith(String name);

    @Query("SELECT * FROM resturants where mBusinessId = :id")
    Single<Resturant> getResturantFromId(long id);

    @Query("SELECT * FROM resturants ORDER BY mAverageRating DESC")
    Single<List<Resturant>> getAllResturantsByPopularity();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResturant(Resturant resturant);

    @Query("SELECT COUNT(*) FROM resturants")
    Flowable<Integer> provideNumberOfResturantsInDB();

    @Query("DELETE FROM resturants")
    void resetResturantsForNewLocation();

}
