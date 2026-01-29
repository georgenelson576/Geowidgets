package com.geowidget;

import android.content.Context;
import android.content.SharedPreferences;

public final class WifiStateStore {

    private static final String PREF = "wifi_state_store";
    private static final String KEY_WIFI_ON = "wifi_on";

    private WifiStateStore() {}

    public static void set(Context context, boolean on) {
        SharedPreferences sp =
                context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_WIFI_ON, on).apply();
    }

    public static boolean isOn(Context context) {
        SharedPreferences sp =
                context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_WIFI_ON, false);
    }
}
