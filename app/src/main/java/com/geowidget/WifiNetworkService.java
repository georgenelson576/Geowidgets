package com.geowidget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.IBinder;

public class WifiNetworkService extends Service {

    private ConnectivityManager.NetworkCallback callback;

    @Override
    public void onCreate() {
        super.onCreate();
        registerCallback();
    }

    private void registerCallback() {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest request =
                new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build();

        callback = new ConnectivityManager.NetworkCallback() {

            @Override
            public void onAvailable(Network network) {
                WifiStateStore.set(WifiNetworkService.this, true);
                WifiWidgetProvider.updateAll(WifiNetworkService.this);
            }

            @Override
            public void onLost(Network network) {
                WifiStateStore.set(WifiNetworkService.this, false);
                WifiWidgetProvider.updateAll(WifiNetworkService.this);
            }
        };

        cm.registerNetworkCallback(request, callback);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
