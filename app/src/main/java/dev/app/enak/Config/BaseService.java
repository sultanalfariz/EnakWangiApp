package dev.app.enak.Config;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    private static final String BASE_URL = "https://familyservices.cf/"; //enakwangi.000webhostapp.com
    private static final String MAIN_API = BASE_URL + "api/";

    public static final String PATH_IMAGE_PEMILIK = BASE_URL + "upload/pengguna/";
    public static final String PATH_IMAGE_PETUGAS = BASE_URL + "upload/petugas/";
    public static final String PATH_IMAGE_TERNAK = BASE_URL + "upload/ternak/";
    public static final String PATH_IMAGE_ARTIKEL = BASE_URL + "upload/artikel/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.retryOnConnectionFailure(true);

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(MAIN_API)
                    .client(okhttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
