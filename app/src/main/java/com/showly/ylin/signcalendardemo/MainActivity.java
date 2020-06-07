package com.showly.ylin.signcalendardemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.showly.ylin.signcalendardemo.signcalendar.I8ShowSignCalendarActivity;
import com.showly.ylin.signcalendardemo.signcalendar.SignCalendarReq;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private TextView signBtn;
    private SignCalendarReq signCalendarReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signBtn = findViewById(R.id.text_sign_calendar);

        initData();
        initListener();
    }

    private void initData() {
        //模拟请求后台返回初始化数据
        signCalendarReq = new SignCalendarReq();

        SignCalendarReq.StateBean state = new SignCalendarReq.StateBean();
        state.setCode(1);
        state.setMsg("成功");
        signCalendarReq.setState(state);

        SignCalendarReq.DataBean data = new SignCalendarReq.DataBean();
        data.setConSign(1);
        data.setIsSign(0);
        data.setSignDay("1,2");
        data.setUid("3347922");
        signCalendarReq.setData(data);
    }

    private void initListener() {
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(MainActivity.this, I8ShowSignCalendarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfos", (Serializable) signCalendarReq);
                intentSign.putExtras(bundle);
                intentSign.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentSign);
            }
        });
    }
}
