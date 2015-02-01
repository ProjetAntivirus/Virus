package bdd;

import android.net.Uri;

/**
 * Created by LEFFY-PC on 04/01/2015.
 */
public class RemoteProviderConfig {

    public static final String CP_PATH = "cp";
    public static final String BD_PATH = "bd";
    public static final String CONTENT_AUTHORITY = "fr.virus.log.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri LOG_CP_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(CP_PATH).build();
    public static final Uri LOG_BD_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(BD_PATH).build();

    public static final String[] selection = new String[] { "name", "status" };
}
