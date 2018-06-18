#include <jni.h>
#include <android/log.h>

#define LOGE(TAG, FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,TAG,FORMAT,__VA_ARGS__)

// C++调用C的函数
extern "C" {
extern int patch_main(int argc, char *argv[]);
}

// 合并
extern "C" JNIEXPORT jint

JNICALL
Java_com_jamff_appupdate_utils_BsPatch_patch(JNIEnv *env, jclass type, jstring oldFile_,
                                             jstring newFile_, jstring patchFile_) {
    const char *oldFile = env->GetStringUTFChars(oldFile_, 0);
    const char *newFile = env->GetStringUTFChars(newFile_, 0);
    const char *patchFile = env->GetStringUTFChars(patchFile_, 0);

    int argc = 4;
    char *argv[4];
    argv[0] = const_cast<char *>("bspatch");
    argv[1] = const_cast<char *>(oldFile);
    argv[2] = const_cast<char *>(newFile);
    argv[3] = const_cast<char *>(patchFile);

    int ret = patch_main(argc, argv);

    LOGE("JamFF", "%s", "合并APK完成");

    env->ReleaseStringUTFChars(oldFile_, oldFile);
    env->ReleaseStringUTFChars(newFile_, newFile);
    env->ReleaseStringUTFChars(patchFile_, patchFile);
    return ret;
}