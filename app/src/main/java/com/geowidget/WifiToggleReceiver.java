package com.geowidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class WifiToggleReceiver extends BroadcastReceiver {

    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent) {

        WifiManager wifi =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (wifi == null) return;

        boolean newState = !wifi.isWifiEnabled();
        wifi.setWifiEnabled(newState);

        // 🔑 IMMEDIATE state update (do NOT wait for NetworkCallback)
        WifiStateStore.set(context, newState);
        WifiWidgetProvider.updateAll(context);
    }
}
