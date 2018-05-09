package downloader.video.technodots.videodownloaders.constants;

import downloader.video.technodots.videodownloaders.R;


public interface iConstants {
    public static final String[] DISABLE_DOWNLOADING = {"youtube.com"}; // separated with comma

    public static final String WEB_DISABLE = "We cannot allow to download videos form this website.";

    public static final String PREF_APPNAME = "xmatevidedownloader";


    public static final String DOWNLOADING_MSG = "Generating download links";

    public  static  final String URL_NOT_SUPPORTED = "This url not supported or no media found!";

    public static final String DOWNLOAD_DIRECTORY="apex_video_dwonloader";

    public Integer[] HomePageThumbs = {
            R.drawable.img_logo_instagram,
            R.drawable.img_logo_facebook,
            R.drawable.img_logo_soundcloud,
            R.drawable.img_logo_vimeo,
            R.drawable.img_logo_twitch,
            R.drawable.img_logo_tumblr,
            R.drawable.img_logo_tune,
            R.drawable.dailymotion,
            R.drawable.img_logo_metacafe,

    };

    public String[] HomePageURI = {
            "http://instagaram.com",
            "http://facebook.com",
            "http://soundcloud.com",
            "http://vimeo.com",
            "http://tumbler.com",
            "http://tune.pk", "http://dailymotion.com",
            "https://www.twitch.tv",
            "http://metacafe.com",
            };

    public  static  final String EMAIL_ID = "v.vckysoft@gmail.com";

    public  static final String SEARCH_ENGINE="https://www.google.com/search?q=%1$s"; //Search Engine

    ///Save It Offline Api

    public  static  final String API_URL = "https://www.saveitoffline.com/process/?url=%1$s&type=json";
    public  static boolean isAdloding = false;








}
