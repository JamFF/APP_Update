package com.jamff.appupdate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jamff.appupdate.utils.APKUtils;

public class MainActivity extends AppCompatActivity {

    private ApkUpdateTask mApkUpdateTask;

    private Button bt_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_version = findViewById(R.id.bt_version);
        TextView tv_version = findViewById(R.id.tv_version);

        String versionName = APKUtils.getVersionName(this);
        // String versionName = BuildConfig.VERSION_NAME;
        tv_version.setText(versionName);
        if (versionName.equals("1.0")) {
            bt_version.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bt_version.setEnabled(false);
                    mApkUpdateTask = new ApkUpdateTask(MainActivity.this);
                    mApkUpdateTask.execute();
                }
            });
        } else {
            bt_version.setEnabled(false);
        }
    }

    @Override
    public void finish() {
        if (mApkUpdateTask != null && mApkUpdateTask.getStatus() == AsyncTask.Status.RUNNING) {
            mApkUpdateTask.cancel(true);
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        if (mApkUpdateTask != null) {
            mApkUpdateTask.onDestroy();
        }
        super.onDestroy();
    }
}
