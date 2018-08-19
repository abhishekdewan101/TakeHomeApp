package abhishekdewan101.com.doordashlite.features.home;

import android.content.Context;

import java.util.List;

import abhishekdewan101.com.doordashlite.data.model.Resturant;
import abhishekdewan101.com.doordashlite.features.base.BaseView;

public interface HomeScreenContract {
    interface HomeScreenView extends BaseView {
        void onResturantsLoaded(List<Resturant> resturants);

        void onMoreResturantsDownloaded(List<Resturant> resturants);

        void replaceResturants(List<Resturant> resturants);
    }

    interface HomeScreenActions {
        void getResturantList(Context context);

        void loadMoreResturants(Context context);

        void getResturantsStartingWith(Context context,String s);

        void getResturantsFilterByPouplarity(Context context);

        void getResturantsFilterByPrice(Context context);

        void getResturantsFilterByDeliveryTime(Context context);

        void getAllOpenResturants(Context context);

        void getResturantsFilterByDeliveryFee(Context context);
    }
}
