package cn.edu.scujcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import cn.edu.scujcc.weather.R;

public class registerActivity extends AppCompatActivity {
    TextInputLayout birthadayInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
}