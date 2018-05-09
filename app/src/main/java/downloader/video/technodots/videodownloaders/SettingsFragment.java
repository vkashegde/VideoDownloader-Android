package downloader.video.technodots.videodownloaders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;


import com.io.tools.android.ramiloif.folderchooser.ChooseDirectoryDialog;

import java.io.File;

import downloader.video.technodots.videodownloaders.constants.iConstants;



public class SettingsFragment extends PreferenceFragment implements iConstants  {
        Preference path ;
    int REQUEST_DIRECTORY = 123;
    private String mBaseFolderPath;
     SharedPreferences  preferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.preferences);
        path = (Preference)findPreference("path");


          preferences = getActivity().getSharedPreferences(PREF_APPNAME, Context.MODE_PRIVATE);

        String folderName = DOWNLOAD_DIRECTORY;

        if(!preferences.getString("path","DEFAULT").equals("DEFAULT")){


            mBaseFolderPath = preferences.getString("path","DEFAULT");
        }else{


            mBaseFolderPath=android.os.Environment.getExternalStorageDirectory()+ File.separator+folderName;
        }
        path.setSummary(mBaseFolderPath);

        path.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            public boolean onPreferenceClick(Preference preference){


                ChooseDirectoryDialog dialog =
                        ChooseDirectoryDialog.builder(getActivity()). // Context
                                titleText("Choose directory"). // The title will be shown
                                startDir(Environment.getExternalStorageDirectory().getAbsoluteFile()).// File from where to start
                                showNeverAskAgain(false). // Enable or disable 'Never ask again checkbox
                                neverAskAgainText("Never ask again"). // Text of never ask again check box(if enabled)
                                onPickListener(new ChooseDirectoryDialog.DirectoryChooseListener() {
                            @Override
                            public void onDirectoryPicked(ChooseDirectoryDialog.DialogResult result) {
                                // Listener to users choice
                                // result.getPath() - The path of the folder picked by user
                                // result.isAskAgain()  - Did the user checked the 'Never ask again' Checkbox if true it means never ask again
//                                resultTV.setText("You picked \n " +
//                                        result.getPath() +
//                                        "\n Never ask again = " +
//                                        result.isAskAgain());
                                path.setSummary(result.getPath());

                                SharedPreferences.Editor editor = preferences.edit();

                               editor.putString("path",result.getPath()).commit();
                              //  new File(result.getPath())
                            }

                            @Override
                            public void onCancel() {
//                                resultTV.setText("operation canceled");
                            }
                        }).build();
                dialog.show();
                return true;

            }


        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}