package abhishekdewan101.com.doordashlite.features.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abhishekdewan101.com.doordashlite.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterBottomSheetDialog extends BottomSheetDialogFragment {

    private FilterBottomSheetInterface mListener;

    public static FilterBottomSheetDialog newInstance() {
        return new FilterBottomSheetDialog();
    }

    public void setListener(FilterBottomSheetInterface listener) {
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.filter_bottom_sheet, container,
                false);

        // get the views and attach the listener

        ButterKnife.bind(this,view);

        return view;

    }

    @OnClick(R.id.priceFilter)
    void priceFilterClicked() {
        mListener.filterByPrice();
        this.dismissAllowingStateLoss();
    }

    @OnClick(R.id.fastestDelivery)
    void fastDeliveryFilterClicked() {
        mListener.filterByFastestDelivery();
        this.dismissAllowingStateLoss();
    }

    @OnClick(R.id.popularityFilter)
    void popularityFilterClicked() {
        mListener.filterByPopularity();
        this.dismissAllowingStateLoss();
    }

    @OnClick(R.id.lowestDeliveryFee)
    void lowestDeliveryFeeFilterClicked() {
        mListener.filterByLowestDelivery();
        this.dismissAllowingStateLoss();
    }

    @OnClick(R.id.currentlyOpen)
    void currentOpenFilterClicked() {
        mListener.filterByOpenResturants();
        this.dismissAllowingStateLoss();
    }

    public interface FilterBottomSheetInterface {
        void filterByPrice();
        void filterByFastestDelivery();
        void filterByPopularity();
        void filterByLowestDelivery();
        void filterByOpenResturants();
    }
}
