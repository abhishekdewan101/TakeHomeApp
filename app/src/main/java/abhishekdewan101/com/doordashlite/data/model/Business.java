package abhishekdewan101.com.doordashlite.data.model;

import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("name")
    public String mBusinessName;

    @SerializedName("id")
    public int mBusinessId;

    public Business(String businessName, int businessId) {
        mBusinessName = businessName;
        mBusinessId = businessId;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public void setBusinessName(String businessName) {
        mBusinessName = businessName;
    }

    public int getBusinessId() {
        return mBusinessId;
    }

    public void setBusinessId(int businessId) {
        mBusinessId = businessId;
    }
}
