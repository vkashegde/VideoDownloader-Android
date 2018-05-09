package downloader.video.technodots.videodownloaders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

/**
 * Created by VIKAS on 5/5/2018.
 */

public class ContactUs extends AppCompatActivity {

    private AppLovinAd loadedAd;

    private ImageView img1,img2,img3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

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

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );

        interstitialAd.showAndRender( loadedAd );



        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com/i.technodots");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("https://plus.google.com/109473271658722998126");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("https://technodots-studios.blogspot.in/?m=1");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }
}
