package downloader.video.technodots.videodownloaders.Tasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import downloader.video.technodots.videodownloaders.R;
import downloader.video.technodots.videodownloaders.constants.iConstants;
import downloader.video.technodots.videodownloaders.utils.JSONParser;
import downloader.video.technodots.videodownloaders.utils.iUtils;




public class GeneratingDownloadLinks implements iConstants  {

   public static Context Mcontext;
    public  static ProgressDialog pd;
    public  static  Dialog dialog;
    static  int error=1;
    public  static void Start(Context context , String url , String title){
        error=1;
            for (int i = 0; i < DISABLE_DOWNLOADING.length; i++) {

        if (url.contains(DISABLE_DOWNLOADING[i])) {

            error=0;

      }
   }
        Mcontext=context;

        if(error==1) {
            pd = new ProgressDialog(context);
            pd.setMessage(DOWNLOADING_MSG);
            pd.show();

            new GetUrls().execute(String.format(API_URL, url));
        }else{
            iUtils.ShowToast(Mcontext,WEB_DISABLE);

        }

    }




    public static class GetUrls extends AsyncTask<String, Void, JSONObject> {
        JSONParser FJson = new JSONParser();
        @Override
        protected JSONObject doInBackground(String... urls) {
            return FJson.getOJSONFromUrl(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(JSONObject result) {

            Log.e("ERROR", result.toString());
            pd.dismiss();
            String error = "";
            try {
                error = result.getString("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (error.contains("not-supported") || error.contains("no_media_found") || error.contains("miss")) {
                iUtils.ShowToast(Mcontext, URL_NOT_SUPPORTED);
            } else {
                try {
                    GenerateUI(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static void GenerateUI(JSONObject result) throws JSONException {

        String thumbnail = result.getString("thumbnail");
        final String title = result.getString("title");
        final  JSONArray urls = result.getJSONArray("urls");

        dialog = new Dialog(Mcontext);
        dialog.setContentView(R.layout.download_dialog);
        dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        ListView LV = (ListView)dialog.findViewById(R.id.ListView);


        String[] listItems = new String[urls.length()];

        text.setText(title);
        if(!thumbnail.equals("")) {
            Picasso.with(Mcontext).load(thumbnail).resize(100, 100).centerCrop().into(image);
        }
        String label="";
        for(int i = 0; i < urls.length(); i++){
            JSONObject list = urls.getJSONObject(i);

            label=list.getString("label");
            if(label.contains("(audio - no video) webm")){
                label=label.replace("(audio - no video) webm","mp3");
            }
            listItems[i] = label;

        }
        ArrayAdapter adapter = new ArrayAdapter(Mcontext, android.R.layout.simple_list_item_1, listItems);
        LV.setAdapter(adapter);





        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String ext = "";
                try {
                    final JSONObject m = urls.getJSONObject(position);

                    if(m.getString("label").contains(" mp4")){
                        ext=".mp4";
                    }else if(m.getString("label").contains(" mp3")){
                        ext=".mp3";
                    }else if(m.getString("label").contains(" 360p - webm")){
                        ext=".webm";
                    }else if(m.getString("label").contains(" webm")){
                        ext=".mp3";
                    }else if(m.getString("label").contains(" m4a")){
                        ext=".m4a";
                    }else if(m.getString("label").contains(" 3gp")){
                        ext=".3gp";
                    }else if(m.getString("label").contains(" flv")){
                        ext=".flv";
                    }else{
                        ext=".mp4";
                    }

                    DownloadFile.Downloading(Mcontext,m.getString("id"),title,ext);
                        dialog.dismiss();
                   // iUtils.ShowToast(Mcontext,"Something error" + m.toString());
                    // For interstitials


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();




            }

}
