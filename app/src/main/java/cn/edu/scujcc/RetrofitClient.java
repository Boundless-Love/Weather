package cn.edu.scujcc;

import com.squareup.moshi.Moshi;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * 使用单例模式创建Retrofit对象。
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;
    private RetrofitClient(){}
    public static Retrofit getInstance() {
        Moshi moshi = new Moshi.Builder()
                .add(new MyDateAdapter())
                .build();
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://47.112.134.96:8080")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return INSTANCE;
    }
}