package com.jamff.appupdate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.jamff.appupdate.utils.APKUtils;
import com.jamff.appupdate.utils.BsPatch;
import com.jamff.appupdate.utils.Constants;
import com.jamff.appupdate.utils.DownloadUtils;

import java.io.File;

/**
 * 描述：下载任务
 * 作者：JamFF
 * 创建时间：2018/4/15 16:27
 */
public class ApkUpdateTask extends AsyncTask<Void, Void, Boolean> {

    private static final String TAG = "JamFF";

    private Context mContext;

    public ApkUpdateTask(Context context) {
        mContext = context.getApplicationContext();
    }

    public void onDestroy() {
        mContext = null;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            //1.下载差分包
            Log.d(TAG, "开始下载");
            File patch_File = DownloadUtils.download(Constants.URL_PATCH_DOWNLOAD, Constants.PATCH_FILE_PATH);

            //获取当前应用的apk文件/data/app/app
            String oldFile = APKUtils.getSourceApkPath(mContext, mContext.getPackageName());
            //2.合并得到最新版本的APK文件
            String newFile = Constants.NEW_APK_PATH;
            String patchFile = patch_File.getAbsolutePath();
            BsPatch.patch(oldFile, newFile, patchFile);

            Log.d(TAG, "oldFile:" + oldFile);
            Log.d(TAG, "newFile:" + newFile);
            Log.d(TAG, "patchFile:" + patchFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        //3.安装
        if (result) {
            Toast.makeText(mContext, "您正在进行无流量更新", Toast.LENGTH_SHORT).show();
            APKUtils.installApk(mContext, Constants.NEW_APK_PATH);
        }
    }
}