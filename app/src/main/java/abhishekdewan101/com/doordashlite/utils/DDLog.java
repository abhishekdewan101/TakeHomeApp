package abhishekdewan101.com.doordashlite.utils;

import android.util.Log;

public class DDLog {

    public static void v(String tag, String message) {
        Log.v(tag,message + getThreadInfo());
    }

    public static void d(String tag, String message) {
        Log.d(tag,message + getThreadInfo());
    }

    public static void e(String tag, String message) {
        Log.e(tag,message + getThreadInfo());
    }

    public static void w(String tag, String message) {
        Log.w(tag,message + getThreadInfo());
    }

    public static void i(String tag, String message) {
        Log.i(tag,message + getThreadInfo());
    }


    private static String getThreadInfo() {
        return " ----> [" + Thread.currentThread().getName() + " ] - " + String.valueOf(System.nanoTime()) + " - " + Thread.currentThread().getId();
    }
}
