package com.myapplication.rest;

import android.content.Context;

import com.myapplication.utils.PreferenceUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitServiceGenerator {

    private static final String BASE_URL = "http://139.59.90.200/api/";
    private static Context context;


    public RetroFitServiceGenerator(Context context) {
        this.context = context;
    }


    public static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    private OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public <S> S createService(
            Class<S> serviceClass) {
        final PreferenceUtils preferenceUtils = new PreferenceUtils(context);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request;
                        if (preferenceUtils.isTokenAvailable(context) != null) {
                            request = original.newBuilder()
                                    .header("Content-Type", "application/json")
                                    .addHeader("Authorization", "Token " + preferenceUtils.isTokenAvailable(context))
                                    .build();
                        } else {
                            request = original.newBuilder()
                                    .header("Content-Type", "application/json")
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        response.cacheResponse();
                        return response;
                    }
                })
                .addInterceptor(logging)
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        builder.client(okHttpClient);
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

}
