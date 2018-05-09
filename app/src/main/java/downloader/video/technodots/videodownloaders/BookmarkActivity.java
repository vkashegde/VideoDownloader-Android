package downloader.video.technodots.videodownloaders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Map;

import downloader.video.technodots.videodownloaders.constants.iConstants;
import downloader.video.technodots.videodownloaders.utils.iUtils;



public class BookmarkActivity extends AppCompatActivity implements iConstants {

    private  InterstitialAd mInterstitialAd;
    private AppLovinAd loadedAd;
        ListView lv;
    SharedPreferences pref;
    Map<String, ?> bmList;
    ArrayAdapter adapter;
    String[] listItems;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);

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


        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().show();
        lv=(ListView)findViewById(R.id.ListView);
        pref = getSharedPreferences(PREF_APPNAME, 0);
        bmList=pref.getAll();
       listItems = new String[bmList.size()];

       // bmList.toString();
            int i=0;
        for (Map.Entry<String, ?> entry : bmList.entrySet()) {
            //Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());

           listItems[i] = entry.getKey();

            i++;
        }
       adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String uri = "";
                 uri=listItems[position];
                iUtils.ShowToast(BookmarkActivity.this,uri);

                Intent intent = new Intent(BookmarkActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("URL", uri); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bookmarks, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_delete) {

            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Do you really want to delete all bookmarks?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                        pref.edit().clear().commit();

iUtils.ShowToast(BookmarkActivity.this,"Deleted!");
                            finish();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }
}
