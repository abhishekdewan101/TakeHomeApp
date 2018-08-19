package abhishekdewan101.com.doordashlite.features.resturantdetails;

import android.content.Context;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Items;
import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseView;

public interface ResturantDetailContract {
    interface ResturantDetailView extends BaseView {

        void showPopularItemList(List<Items> items);
        void showResturantDetails(Resturant resturant);
    }

    interface ResturantDetailActions {
        void loadResturantDetails(Context context, int resturantId);
        void loadResturantPopularItems(Context context,int resturantId);
    }
}
