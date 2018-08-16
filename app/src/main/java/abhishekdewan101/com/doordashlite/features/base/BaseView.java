package abhishekdewan101.com.doordashlite.features.base;

public interface BaseView {

    void showLoading();

    void dismissLoading();

    void handleError(Throwable throwable);
}
