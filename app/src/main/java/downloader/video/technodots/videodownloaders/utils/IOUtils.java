package downloader.video.technodots.videodownloaders.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;


public class IOUtils {
    private final static String TAG = IOUtils.class.getSimpleName();

    public static void writeString(String mDirectory, String mNameFile, String mStrData) {
        if (mDirectory == null || mNameFile == null || mStrData == null) {
            new Exception(TAG + ": Some content can not null").printStackTrace();
            return;
        }
        File mFile = new File(mDirectory);
        if ((!mFile.exists())) {
            mFile.mkdirs();
        }
        try {
            File newTextFile = new File(mDirectory, mNameFile);
            BufferedSink sink = Okio.buffer(Okio.sink(newTextFile));
            sink.writeUtf8(mStrData);
            sink.close();
        }
        catch (Exception iox) {
            iox.printStackTrace();
        }
    }

    public static String readString(String mDirectory, String mNameFile) {
        try {
            File mFile = new File(mDirectory, mNameFile);
            if (mFile.exists() && mFile.isFile()) {
                BufferedSource source = Okio.buffer(Okio.source(mFile));
                String data = source.readUtf8();
                source.close();
                return data;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "--->error when read string" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static String readStringFromAssets(Context mContext, String mNameFile) {
        try {
            InputStream mInputStream = mContext.getAssets().open(mNameFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(mInputStream));
            StringBuilder contents = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                contents.append(line);
                contents.append("\n");
            }
            return contents.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }
    public static boolean hasLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
    public static boolean hasMarsallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
