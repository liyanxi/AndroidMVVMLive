package com.itingchunyu.m.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.itingchunyu.m.BuildConfig;
import com.itingchunyu.m.data.TraderService;
import com.itingchunyu.m.data.retrofit.BaseIntercepter;
import com.itingchunyu.m.data.source.DataSourceInterface;
import com.itingchunyu.m.data.source.Remote;
import com.itingchunyu.m.data.source.TraderRepository;
import com.itingchunyu.m.data.source.remote.RemoteDataSource;
import com.itingchunyu.m.data.source.remote.cache.PersistentCookieStore;
import com.itingchunyu.m.util.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
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
    static OkHttpClient.Builder provideOkHttpClickBuilder(Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.cookieJar(new CookieJar() {
            private final PersistentCookieStore cookieStore = new PersistentCookieStore(application);

            @Override
            public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                if (cookies.size() > 0) {
                    LogUtil.d("saveCookie:");
                    for (Cookie item : cookies) {
                        cookieStore.add(url, item);
                        if (item != null) {
                            LogUtil.d("cookie:" + item.toString());
                        }
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                LogUtil.d("loadCookie:");
                List<Cookie> cookies = cookieStore.get(url);
                for (Cookie item : cookies) {
                    if (item != null) {
                        LogUtil.d("cookie:" + item.toString());
                    }
                }
                return cookies;
            }
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
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
