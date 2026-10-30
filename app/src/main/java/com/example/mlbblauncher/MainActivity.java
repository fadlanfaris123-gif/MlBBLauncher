package com.example.mlbblauncher;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
import android.view.Gravity;

public class MainActivity extends Activity {

    private EditText deviceIdInput;
    private SharedPreferences preferences;

    private final String MLBB_PACKAGE = "com.mobile.legends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(
            "MLBBLauncherData",
            MODE_PRIVATE
        );

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 40, 32, 32);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("MLBB LAUNCHER");
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        layout.addView(title);

        TextView description = new TextView(this);
        description.setText("Device ID Manager");
        description.setTextSize(16);
        description.setGravity(Gravity.CENTER);
        layout.addView(description);

        deviceIdInput = new EditText(this);
        deviceIdInput.setHint("Masukkan Device ID");
        deviceIdInput.setSingleLine(true);

        deviceIdInput.setText(
            preferences.getString("device_id", "")
        );

        layout.addView(deviceIdInput);

        Button saveButton = new Button(this);
        saveButton.setText("SIMPAN DEVICE ID");

        saveButton.setOnClickListener(v -> {
            String deviceId = deviceIdInput
                .getText()
                .toString()
                .trim();

            preferences.edit()
                .putString("device_id", deviceId)
                .apply();

            Toast.makeText(
                this,
                "Device ID berhasil disimpan",
                Toast.LENGTH_SHORT
            ).show();
        });

        layout.addView(saveButton);

        Button launchButton = new Button(this);
        launchButton.setText("BUKA MOBILE LEGENDS");

        launchButton.setOnClickListener(v -> {
            PackageManager pm = getPackageManager();

            Intent launchIntent =
                pm.getLaunchIntentForPackage(MLBB_PACKAGE);

            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(
                    this,
                    "Mobile Legends tidak ditemukan",
                    Toast.LENGTH_LONG
                ).show();
            }
        });

        layout.addView(launchButton);

        setContentView(layout);
    }
}￼Enter
