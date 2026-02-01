package com.geowidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MobileDataWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        for (int widgetId : appWidgetIds) {

            // Click → MobileDataToggleReceiver
            Intent clickIntent = new Intent(
                    context,
                    MobileDataToggleReceiver.class
            );

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.widget_mobile_data
            );

            // Attach click handler to icon (or root if you prefer)
            views.setOnClickPendingIntent(R.id.img, pendingIntent);

            // Push widget update
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
}
