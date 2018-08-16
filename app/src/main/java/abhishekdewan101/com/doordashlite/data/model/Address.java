package abhishekdewan101.com.doordashlite.data.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("city")
    public String mCity;

    @SerializedName("state")
    public String mState;

    @SerializedName("street")
    public String mStreet;

    @SerializedName("lat")
    public float mLat;

    @SerializedName("lng")
    public float mLng;

    @SerializedName("printable_address")
    public String mPrintableAddress;

    public Address(String city, String state, String street, float lat, float lng, String printableAddress) {
        mCity = city;
        mState = state;
        mStreet = street;
        mLat = lat;
        mLng = lng;
        mPrintableAddress = printableAddress;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        mStreet = street;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float lat) {
        mLat = lat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float lng) {
        mLng = lng;
    }

    public String getPrintableAddress() {
        return mPrintableAddress;
    }

    public void setPrintableAddress(String printableAddress) {
        mPrintableAddress = printableAddress;
    }
}
