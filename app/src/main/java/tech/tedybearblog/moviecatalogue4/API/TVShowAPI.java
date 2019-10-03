package tech.tedybearblog.moviecatalogue4.API;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

import tech.tedybearblog.moviecatalogue4.BuildConfig;

public class TVShowAPI {
    public static URL getURL(String t) {
        Uri uri = Uri.parse(BuildConfig.TMDB_URL_TV).buildUpon()
                .appendPath(t)
                .appendQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
