package com.jamff.appupdate.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtils {

    /**
     * 下载差分包
     *
     * @param url  下载url
     * @param path 保存路径
     * @return File
     */
    public static File download(String url, String path) {
        File file = null;
        InputStream is = null;
        FileOutputStream os = null;
        try {
            file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            is = conn.getInputStream();
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
