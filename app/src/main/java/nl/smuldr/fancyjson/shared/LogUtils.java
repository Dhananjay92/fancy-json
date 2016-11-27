package nl.smuldr.fancyjson.shared;


import nl.smuldr.fancyjson.BuildConfig;
import timber.log.Timber;

public final class LogUtils {

    private LogUtils() {
        // do not instantiate
    }

    public static void initLogging() {
        // only log in debug builds
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
