package cn.edu.scujcc;

import com.squareup.moshi.Moshi;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * 使用单例模式创建Retrofit对象。
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;
    private RetrofitClient(){}
    public static Retrofit getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://47.112.134.96:8080")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}