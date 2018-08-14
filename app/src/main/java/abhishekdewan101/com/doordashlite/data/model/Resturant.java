package abhishekdewan101.com.doordashlite.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "resturants")
public class Resturant {

    @PrimaryKey
    @SerializedName("id")
    public int mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("cover_img_url")
    public String mCoverImageUrl;

    @SerializedName("delivery_fee")
    public long mDeliveryFee;

    public Resturant(int id, String name, String description, String coverImageUrl, long deliveryFee) {
        mId = id;
        mName = name;
        mDescription = description;
        mCoverImageUrl = coverImageUrl;
        mDeliveryFee = deliveryFee;
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

    public String getCoverImageUrl() {
        return mCoverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        mCoverImageUrl = coverImageUrl;
    }

    public long getDeliveryFee() {
        return mDeliveryFee;
    }

    public void setDeliveryFee(long deliveryFee) {
        mDeliveryFee = deliveryFee;
    }
}
