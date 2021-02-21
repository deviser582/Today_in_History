package com.example.today_in_history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Write_memo extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_memo);

        editText = findViewById(R.id.add_text);

        Button button_back = (Button)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取num，确认添加后修改num
                SharedPreferences preferences_num = getSharedPreferences("data", MODE_PRIVATE);
                int num = preferences_num.getInt("num", 0);
                if (num <= 6) {
                    num++;
                }
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putInt("num",num);
                editor.apply();


                //返回添加的备忘录给上一个界面
                Intent intent = new Intent();
                intent.putExtra("key", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}