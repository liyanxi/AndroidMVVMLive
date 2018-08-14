package com.itingchunyu.m.di;

import android.support.annotation.NonNull;

import com.itingchunyu.m.BuildConfig;
import com.itingchunyu.m.data.TraderService;
import com.itingchunyu.m.data.retrofit.BaseIntercepter;
import com.itingchunyu.m.data.source.DataSourceInterface;
import com.itingchunyu.m.data.source.Remote;
import com.itingchunyu.m.data.source.TraderRepository;
import com.itingchunyu.m.data.source.remote.RemoteDataSource;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is used by Dagger to inject the required arguments into the {@link TraderRepository}.
 */
@Module
abstract class HttpModule {

    @Provides
    @Singleton
    static OkHttpClient.Builder provideOkHttpClickBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }
        return builder;
    }

    @Provides
    @Singleton
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(@NonNull Retrofit.Builder builder, @NonNull OkHttpClient.Builder client) {
        client.addInterceptor(new BaseIntercepter());
        OkHttpClient httpClient = client.build();

        builder.client(httpClient);
        builder.baseUrl(TraderService.HOST);
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    static TraderService provideTraderService(Retrofit retrofit) {
        return retrofit.create(TraderService.class);
    }

    @Singleton
    @Binds
    @Remote
    abstract DataSourceInterface provideTraderRemoteDataSource(RemoteDataSource dataSource);

}
