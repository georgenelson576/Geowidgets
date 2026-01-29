package com.geowidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WifiWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager manager,
                         int[] widgetIds) {
        updateWidgets(context, manager, widgetIds);
    }

    /** Explicit update entry point (USED BY SERVICE) */
    public static void updateAll(Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int[] ids = manager.getAppWidgetIds(
                new ComponentName(context, WifiWidgetProvider.class));
        if (ids != null && ids.length > 0) {
            updateWidgets(context, manager, ids);
        }
    }

    static void updateWidgets(Context context,
                              AppWidgetManager manager,
                              int[] widgetIds) {

        boolean wifiOn = WifiStateStore.isOn(context);

        for (int id : widgetIds) {

            Intent toggle = new Intent(context, WifiToggleReceiver.class);

            PendingIntent pi = PendingIntent.getBroadcast(
                    context,
                    id,
                    toggle,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            int layout = wifiOn
                    ? R.layout.widget_wifi_on
                    : R.layout.widget_wifi_off;

            RemoteViews views =
                    new RemoteViews(context.getPackageName(), layout);

            views.setOnClickPendingIntent(R.id.widget_root, pi);
            manager.updateAppWidget(id, views);
        }
    }
}
