package abhishekdewan101.com.doordashlite.features.home.resturantlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import abhishekdewan101.com.doordashlite.R;
import abhishekdewan101.com.doordashlite.data.model.Resturant;

public class ResturantListAdapter extends RecyclerView.Adapter<ResturantListViewHolder> {

    List<Resturant> mAdapterData;
    ResturantListInterface mResturantListListener;

    final int LOADING_OFFSET = 20;
    boolean mHasLoadingDataBeenInitiated = false;

    @NonNull
    @Override
    public ResturantListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resturant_list_view_holder,viewGroup,false);
        return new ResturantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantListViewHolder resturantListViewHolder, int i) {
        isLoadingNewDataNeeded(i);
        resturantListViewHolder.mResturantName.setText(i+ "  -  " + mAdapterData.get(i).mName);
    }

    private void isLoadingNewDataNeeded(int i) {
        if (i >= mAdapterData.size() - LOADING_OFFSET && !mHasLoadingDataBeenInitiated) {
            mResturantListListener.loadMoreResturants();
            mHasLoadingDataBeenInitiated = true;
        }
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    public void setAdapterData(List<Resturant> data) {
        mAdapterData = data;
        this.notifyDataSetChanged();
    }

    public void addAdapaterData(List<Resturant> data) {
        if (data == null) {
            mHasLoadingDataBeenInitiated = false;
            return;
        }
        int lastPosition = mAdapterData.size();
        mAdapterData.addAll(data);
        mHasLoadingDataBeenInitiated = false;
        this.notifyItemInserted(lastPosition);
    }

    public void setResturantListInterface(ResturantListInterface listener) {
        mResturantListListener = listener;
    }

    public interface ResturantListInterface {
        void loadMoreResturants();
    }
}
