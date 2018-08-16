package abhishekdewan101.com.doordashlite.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import abhishekdewan101.com.doordashlite.data.model.Resturant;

@Database(entities = Resturant.class,version = 1,exportSchema = false)
@TypeConverters({DBTypeConverters.class})
public abstract class ResturantDatabase extends RoomDatabase{

    public abstract ResturantDao resturantDao();
}
