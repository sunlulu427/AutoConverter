#include <jni.h>
#include <string>

extern "C"  jstring JNICALL
Java_com_mato_stg4cpp_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}