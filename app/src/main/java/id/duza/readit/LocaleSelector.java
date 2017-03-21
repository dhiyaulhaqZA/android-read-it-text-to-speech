package id.duza.readit;

import android.util.Log;

import java.util.Locale;

/**
 * Created by dhiyaulhaqza on 3/21/17.
 */

public class LocaleSelector {

    public Locale getLocale(String language)    {
        Log.d("language", language);
        if (language.equals(MainActivity.LANG_INDONESIA))   {
            return new Locale("id", "ID");
        } else {
            return Locale.ENGLISH;
        }
    }
}
