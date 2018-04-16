package com.jamff.appupdate.utils;

import android.os.Environment;

import java.io.File;

public interface Constants {

    String PATCH_FILE = "apk.patch";

    String URL_PATCH_DOWNLOAD = "http://192.168.0.105:8080/" + PATCH_FILE;
    // linux remote
    // String URL_PATCH_DOWNLOAD = "http://www.dongnaoedu.com/" + PATCH_FILE;

    String SD_CARD = Environment.getExternalStorageDirectory() + File.separator;

    // 新版本apk的目录
    String NEW_APK_PATH = SD_CARD + "apk_new.apk";

    // 差分包路径
    String PATCH_FILE_PATH = SD_CARD + PATCH_FILE;
}
