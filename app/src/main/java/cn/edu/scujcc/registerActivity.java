package cn.edu.scujcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import cn.edu.scujcc.weather.R;

public class registerActivity extends AppCompatActivity {
    TextInputLayout birthadayInput;
    private Date birthady = new Date();
    private userLab lab = userLab.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage( Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case userLab.USER_REGISTER_SUCCESS:
                        Toast.makeText(registerActivity.this, "注册成功！欢迎你的加入！", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(registerActivity.this, loginActivity.class);
                        startActivity(intent);
                        break;
                    case userLab.USER_REGISTER_FAIL:
                        Toast.makeText(registerActivity.this, "注册失败！请稍候再试。", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton = findViewById(R.id.zhuc);
        registerButton.setOnClickListener(v -> {
            register();
        });
        //创建一个日历，新建一个Builder
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("选择生日");
        MaterialDatePicker picker = builder.build();
        picker.addOnPositiveButtonClickListener(selection -> {
           String date = picker.getHeaderText();
           birthadayInput.getEditText().setText(date);

        });
        birthadayInput = findViewById(R.id.r_birthday);
        birthadayInput.setEndIconOnClickListener(v -> {
            picker.show(getSupportFragmentManager(),picker.toString());
        });
    }
    private void register() {
        User u = new User();
        boolean error = false;
        String errorMessage;
        //获得用户名
        TextInputLayout usernameInput = findViewById(R.id.r_username);
        Editable username = usernameInput.getEditText().getText();
        u.setUsername(username != null ? username.toString() : "");

        //检查密码是否一致
        TextInputLayout passwordInput1 = findViewById(R.id.password);
        TextInputLayout passwordInput2 = findViewById(R.id.password1);
        Editable password1 = passwordInput1.getEditText().getText();
        Editable password2 = passwordInput2.getEditText().getText();
        if (password1 != null && password2 != null) {
            if (!password2.toString().equals(password1.toString())) { //两次密码不相同
                error = true;
                errorMessage = "两次密码不相同";
            } else {
                u.setPassword(password1.toString());
            }
        }

        //获得手机号
        TextInputLayout phoneInput = findViewById(R.id.iphone);
        Editable phone = phoneInput.getEditText().getText();
        u.setPhone(phone != null ? phone.toString() : "");

        //获得性别
        RadioGroup genderGroup = findViewById(R.id.r_g);
        int gender = genderGroup.getCheckedRadioButtonId();
        switch (gender) {
            case R.id.nan:
                u.setGender("男");
                break;
            case R.id.nv:
                u.setGender("女");
                break;
            default:
                u.setGender("外星人");
        }

        //获得生日
        u.setBirthday(birthady);

        //把u发送到服务器
        lab.register(u, handler);
    }
}