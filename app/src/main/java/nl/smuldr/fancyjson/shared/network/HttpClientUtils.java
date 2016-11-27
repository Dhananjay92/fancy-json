package nl.smuldr.fancyjson.shared.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;


final class HttpClientUtils {

    private HttpClientUtils() {
        // do not instantiate
    }

    static OkHttpClient getHttpClient() {

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // log requests
        final HttpLoggingInterceptor logger = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
        logger.setLevel(BASIC);
        builder.addInterceptor(logger);

        return builder.build();
    }

    static Retrofit getRetrofit(final String baseUrl, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }
}
