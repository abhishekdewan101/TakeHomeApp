package abhishekdewan101.com.doordashlite.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import io.reactivex.Flowable;

@Dao
public interface ResturantDao {

    @Query("SELECT * FROM resturants")
    Flowable<List<Resturant>> getAllCachedResturants();

    @Insert
    void insertResturant(Resturant resturant);
}
