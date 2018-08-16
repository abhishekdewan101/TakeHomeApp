package abhishekdewan101.com.doordashlite.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Menu {

    @SerializedName("popular_items")
    public List<Items> mPopularItems;

    public Menu(List<Items> popularItems) {
        mPopularItems = popularItems;
    }

    public List<Items> getPopularItems() {
        return mPopularItems;
    }

    public void setPopularItems(List<Items> popularItems) {
        mPopularItems = popularItems;
    }
}
