package abhishekdewan101.com.doordashlite.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.google.gson.annotations.SerializedName;


public class Items {

    @SerializedName("id")
    public int mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("price")
    public int mPrice;

    @SerializedName("image_url")
    public String mImageUrl;

    public int mResturantId;

    public Items(int id, String name, String description, int price, String imageUrl) {
        mId = id;
        mName = name;
        mDescription = description;
        mPrice = price;
        mImageUrl = imageUrl;
    }

    public int getResturantId() {
        return mResturantId;
    }

    public void setResturantId(int resturantId) {
        mResturantId = resturantId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}


