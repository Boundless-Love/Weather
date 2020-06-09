package cn.edu.scujcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import cn.edu.scujcc.weather.MainActivity;
import cn.edu.scujcc.weather.R;


public class loginActivity extends AppCompatActivity {
    private Button loginbutton;
    private Button register;
    private userLab lab = userLab.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (null != msg) {
                switch (msg.what) {
                    case userLab.USER_LOGIN_SUCCESS:
                        loginSucess();
                        break;
                    case userLab.USER_LOGIN_ERROR:
                        loginPasswordError();
                        break;
                    case userLab.USER_LOGIN_NET_ERROR:
                        loginFailed();
                        break;
                }
            }
        }
    };
    private void loginSucess(){
        Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(loginActivity.this, MainActivity.class);
         startActivity(intent);
    }
    private void loginPasswordError(){
        Toast.makeText(loginActivity.this, "密码出错请重试", Toast.LENGTH_SHORT).show();
    }
    private void loginFailed(){
        Toast.makeText(loginActivity.this, "服务器出错请稍后再试", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbutton = findViewById(R.id.login_button);
        loginbutton.setOnClickListener(v -> {
            TextInputLayout username = findViewById(R.id.r_username);
            TextInputLayout password = findViewById(R.id.password);
            String u = username.getEditText().getText().toString();
            String p = password.getEditText().getText().toString();
            lab.login(u,p,handler);
        });
        register = findViewById(R.id.zhuce);
        register.setOnClickListener(v ->{
            Intent intent =new Intent (loginActivity.this,registerActivity.class);
            startActivity(intent);
        });
    }

}
