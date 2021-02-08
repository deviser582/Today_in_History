package com.example.today_in_history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class test_1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RvData>list = new ArrayList<>();
    private RV_Adapter rv_adapter = new RV_Adapter(list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_1);

        recyclerView = findViewById(R.id.rv_test);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rv_adapter);

        list.add(new RvData("199年","0207","death","三国时期名将吕布逝世","吕布（？－199年2月7日），字奉先，汉族，东汉末年名将，汉末群雄之一，五原郡九原县人（今内蒙古包头九原区）。"));
        list.add(new RvData("199年","0207","death","三国时期名将吕布逝世","吕布（？－199年2月7日），字奉先，汉族，东汉末年名将，汉末群雄之一，五原郡九原县人（今内蒙古包头九原区）。"));
        rv_adapter.notifyDataSetChanged();
    }

}
