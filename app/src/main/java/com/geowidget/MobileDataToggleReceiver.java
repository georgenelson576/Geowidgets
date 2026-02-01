package com.geowidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;

import java.lang.reflect.Method;

public class MobileDataToggleReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        toggleMobileData(context);

        boolean enabled = isMobileDataEnabled(context);

        RemoteViews views = new RemoteViews(
                context.getPackageName(),
                R.layout.widget_mobile_data
        );

        if (enabled) {
            // ===== MOBILE DATA ON =====
            views.setInt(
                    R.id.root,
                    "setBackgroundColor",
                    0xFF000000   // BLACK background
            );
            views.setImageViewResource(
                    R.id.img,
                    R.drawable.ic_data_white
            );

        } else {
            // ===== MOBILE DATA OFF =====
            views.setInt(
                    R.id.root,
                    "setBackgroundColor",
                    0xFFFFFFFF   // WHITE background

            );
            views.setImageViewResource(
                    R.id.img,
                    R.drawable.ic_data_black
            );
        }

        AppWidgetManager.getInstance(context)
                .updateAppWidget(
                        new ComponentName(context, MobileDataWidgetProvider.class),
                        views
                );
    }

    /* ================= CORE ================= */

    private void toggleMobileData(Context context) {
        try {
            TelephonyManager tm =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            Method getData =
                    TelephonyManager.class.getDeclaredMethod("getDataEnabled");
            Method setData =
                    TelephonyManager.class.getDeclaredMethod(
                            "setDataEnabled", boolean.class);

            boolean enabled = (boolean) getData.invoke(tm);
            setData.invoke(tm, !enabled);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private boolean isMobileDataEnabled(Context context) {
        try {
            TelephonyManager tm =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            Method m =
                    TelephonyManager.class.getDeclaredMethod("getDataEnabled");
            return (boolean) m.invoke(tm);

        } catch (Throwable t) {
            return false;
        }
    }
}
