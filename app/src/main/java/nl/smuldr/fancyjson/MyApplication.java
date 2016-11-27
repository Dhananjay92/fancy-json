package nl.smuldr.fancyjson;

import android.app.Application;

import nl.smuldr.fancyjson.shared.AppModule;
import nl.smuldr.fancyjson.shared.LogUtils;
import nl.smuldr.fancyjson.shared.network.NetworkModule;


public final class MyApplication extends Application {

    private MainComponent mainComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.initLogging();
    }

    public MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule("https://jsonplaceholder.typicode.com/"))
                    .build();
        }
        return mainComponent;
    }
}
