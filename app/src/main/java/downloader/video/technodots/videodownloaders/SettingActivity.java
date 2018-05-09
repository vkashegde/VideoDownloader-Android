package downloader.video.technodots.videodownloaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.InterstitialAd;


public class SettingActivity  extends AppCompatActivity {
    public InterstitialAd mInterstitialAd;

    private AppLovinAd loadedAd;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
        getSupportActionBar().setTitle("Settings");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();



        // Load an Interstitial Ad
        AppLovinSdk.getInstance( this ).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        } );



        AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );

        interstitialAd.showAndRender( loadedAd );


    }
}