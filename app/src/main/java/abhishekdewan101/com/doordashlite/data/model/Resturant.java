package abhishekdewan101.com.doordashlite.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import butterknife.OnItemSelected;

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

    @SerializedName("is_time_surging")
    public boolean mIsPriceSurging;

    @Ignore
    @SerializedName("menus")
    public List<Menu> mMenus;

    @SerializedName("number_of_ratings")
    public long mResturantRating;

    @SerializedName("tags")
    public List<String> mTags;

    @SerializedName("yelp_review_count")
    public long mYelpReviewCount;

    @SerializedName("is_newly_added")
    public boolean mIsNewlyAdded;

    @SerializedName("average_rating")
    public float mAverageRating;

    @Embedded
    @SerializedName("address")
    public Address mAddress;

    @SerializedName("status")
    public String mAsapTime;

    @SerializedName("status_type")
    public String mStatusType;

    @SerializedName("business")
    @Embedded
    public Business mBusiness;

    @SerializedName("price_range")
    public int mPriceRange;

    @SerializedName("header_img_url")
    public String mHeaderImageUrl;


    public Resturant(int id, String name, String description, String coverImageUrl, long deliveryFee,
                     boolean isPriceSurging, List<Menu> menus, long resturantRating, List<String> tags,
                     long yelpReviewCount, boolean isNewlyAdded, float averageRating,
                     Address address, String asapTime, String statusType, Business business,
                     int priceRange, String headerImageUrl) {
        mId = id;
        mName = name;
        mDescription = description;
        mCoverImageUrl = coverImageUrl;
        mDeliveryFee = deliveryFee;
        mIsPriceSurging = isPriceSurging;
        mMenus = menus;
        mResturantRating = resturantRating;
        mTags = tags;
        mYelpReviewCount = yelpReviewCount;
        mIsNewlyAdded = isNewlyAdded;
        mAverageRating = averageRating;
        mAddress = address;
        mAsapTime = asapTime;
        mStatusType = statusType;
        mBusiness = business;
        mPriceRange = priceRange;
        mHeaderImageUrl = headerImageUrl;
    }

    public Resturant(int id, String name, String description, String coverImageUrl,
                     long deliveryFee, boolean isPriceSurging, long resturantRating,
                     List<String> tags, long yelpReviewCount, boolean isNewlyAdded, float averageRating,
                     Address address, String asapTime, String statusType, Business business,
                     int priceRange, String headerImageUrl) {
        mId = id;
        mName = name;
        mDescription = description;
        mCoverImageUrl = coverImageUrl;
        mDeliveryFee = deliveryFee;
        mIsPriceSurging = isPriceSurging;
        mResturantRating = resturantRating;
        mTags = tags;
        mYelpReviewCount = yelpReviewCount;
        mIsNewlyAdded = isNewlyAdded;
        mAverageRating = averageRating;
        mAddress = address;
        mAsapTime = asapTime;
        mStatusType = statusType;
        mBusiness = business;
        mPriceRange = priceRange;
        mHeaderImageUrl = headerImageUrl;
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

    public boolean isPriceSurging() {
        return mIsPriceSurging;
    }

    public void setPriceSurging(boolean priceSurging) {
        mIsPriceSurging = priceSurging;
    }

    public List<Menu> getMenus() {
        return mMenus;
    }

    public void setMenus(List<Menu> menus) {
        mMenus = menus;
    }

    public long getResturantRating() {
        return mResturantRating;
    }

    public void setResturantRating(long resturantRating) {
        mResturantRating = resturantRating;
    }

    public List<String> getTags() {
        return mTags;
    }

    public void setTags(List<String> tags) {
        mTags = tags;
    }

    public long getYelpReviewCount() {
        return mYelpReviewCount;
    }

    public void setYelpReviewCount(long yelpReviewCount) {
        mYelpReviewCount = yelpReviewCount;
    }

    public boolean isNewlyAdded() {
        return mIsNewlyAdded;
    }

    public void setNewlyAdded(boolean newlyAdded) {
        mIsNewlyAdded = newlyAdded;
    }

    public float getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(float averageRating) {
        mAverageRating = averageRating;
    }

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
        mAddress = address;
    }

    public Business getBusiness() {
        return mBusiness;
    }

    public void setBusiness(Business business) {
        mBusiness = business;
    }

    public String getAsapTime() {
        return mAsapTime;
    }

    public void setAsapTime(String asapTime) {
        mAsapTime = asapTime;
    }

    public String getStatusType() {
        return mStatusType;
    }

    public void setStatusType(String statusType) {
        mStatusType = statusType;
    }
}
