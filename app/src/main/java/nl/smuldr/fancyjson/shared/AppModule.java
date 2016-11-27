package nl.smuldr.fancyjson.shared;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final Application application;

    public AppModule(final Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(final Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
