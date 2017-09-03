#include <jni.h>
#include <android/log.h>
#define LOGI(FORMAT, ...) __android_log_print(ANDROID_LOG_INFO, "JNI",FORMAT,__VA_ARGS__)
#define LOGD(FORMAT, ...) __android_log_print(ANDROID_LOG_DEBUG,"JNI",FORMAT,__VA_ARGS__)
#define LOGE(FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,"JNI",FORMAT,__VA_ARGS__)


JNIEXPORT jstring JNICALL
Java_demo_yc_joviality_conf_SafeUtil_getURLPassword(JNIEnv *env, jclass type) {

    LOGD("password=> 292936aa62644f0f83b76a33959e1e62",0);

    return (*env)->NewStringUTF(env,"292936aa62644f0f83b76a33959e1e62");
}