package cn.edu.scujcc;

import android.os.Handler;
import android.os.Message;
import android.util.Log;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class userLab {
    private static userLab INSTANCE = null;
    private final static String TAG = "weather";
    public final static int USER_LOGIN_SUCCESS = 1;
    public final static int USER_LOGIN_ERROR = -1;
    public final static int USER_LOGIN_NET_ERROR = -2;

    private userLab() {
    }

    public static userLab getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new userLab();
        }
        return INSTANCE;
    }

    public void login(String username, String password, Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        Call<Result<String>> call = api.login(username, password);
        call.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                boolean loginSuccess = false;
                String token = null;
                if (response.body() != null) {
                    Log.d(TAG, "服务器返回结果" + response.body());
                    Result<String> result = response.body();
                    if (result.getStatus() == Result.OK) {  //登录成功
                        loginSuccess = true;
                        token = result.getData();
                    }
                }
                if (loginSuccess) {
                    Message msg = new Message();
                    msg.what = USER_LOGIN_SUCCESS;
                    msg.obj = token;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = USER_LOGIN_ERROR;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                Log.e(TAG, "登录失败！", t);
                Message msg = new Message();
                msg.what = USER_LOGIN_NET_ERROR;
                handler.sendMessage(msg);
            }
        });
    }
}