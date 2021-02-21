package com.example.today_in_history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Service_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service__menu);

        //打开百度的按钮
        Button open_baidu = (Button)findViewById(R.id.open_baidu);
        open_baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Service_Menu.this,OpenBaidu.class);
                startActivity(intent);
            }
        });

        //打开便签的按钮
        Button open_memo = (Button)findViewById(R.id.open_memo);
        open_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Service_Menu.this,Memo.class);
                startActivity(intent);
            }
        });

        //联系我的按钮
        Button call_me = (Button)findViewById(R.id.call_me);
        call_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:17783269045"));
                startActivity(intent);
            }
        });

    }
}