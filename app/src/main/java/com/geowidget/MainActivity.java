package com.geowidget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout instagram = findViewById(R.id.instagram);
        LinearLayout gmail = findViewById(R.id.gmail);
        LinearLayout github = findViewById(R.id.github);

        instagram.setOnClickListener(v ->
                openLink("https://instagram.com/ge0rge.nels0n"));

        gmail.setOnClickListener(v ->
                openLink("mailto:georgenelson576@gmail.com"));

        github.setOnClickListener(v ->
                openLink("https://github.com/georgenelson576"));
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
