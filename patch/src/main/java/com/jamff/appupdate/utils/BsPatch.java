package com.jamff.appupdate.utils;

public class BsPatch {

    /**
     * 合并
     *
     * @param oldFile   已安装apk路径
     * @param newFile   生成的新apk路径
     * @param patchFile 差分包路径
     */
    public native static void patch(String oldFile, String newFile, String patchFile);

    static {
        System.loadLibrary("native-lib");
    }
}
