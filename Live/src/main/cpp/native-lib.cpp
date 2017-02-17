#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jstring JNICALL
Java_hit_campo_live_pub_WorkerThread_stringFromJNI(JNIEnv *env, jobject instance) {

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
