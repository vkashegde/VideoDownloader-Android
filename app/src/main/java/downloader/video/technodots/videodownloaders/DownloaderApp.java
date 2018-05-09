package downloader.video.technodots.videodownloaders;

import android.app.Application;


public class DownloaderApp extends Application {
    public static final String TAG = DownloaderApp.class
            .getSimpleName();

    private static DownloaderApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);




    }

}