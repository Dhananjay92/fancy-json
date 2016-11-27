package nl.smuldr.fancyjson.shared.network;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


@Module
public class NetworkModule {

    private final String baseUrl;

    public NetworkModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return HttpClientUtils.getHttpClient();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        return HttpClientUtils.getRetrofit(baseUrl, okHttpClient);
    }
}
