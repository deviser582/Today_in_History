package com.example.today_in_history;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Memo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Rv_Data_memo> list = new ArrayList<>();
    private Rv_Adapter_memo rvAdapter = new Rv_Adapter_memo(list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);

        recyclerView = findViewById(R.id.rv_memo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);


        //向添加便签界面跳转
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Memo.this, Write_memo.class);
                startActivityForResult(intent, 1);
            }
        });

        //子项点击事件
        rvAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                list.remove(position);
                rvAdapter.notifyDataSetChanged();
                //根据position的值选择删除某一项后键值对信息的向上移动方式
                switch (position+1){
                    case 1:
                        write_1(read_2());
                        write_2(read_3());
                        write_3(read_4());
                        write_4(read_5());
                        write_5(read_6());
                        break;
                    case 2:
                        write_2(read_3());
                        write_3(read_4());
                        write_4(read_5());
                        write_5(read_6());
                        break;
                    case 3:
                        write_3(read_4());
                        write_4(read_5());
                        write_5(read_6());
                        break;
                    case 4:
                        write_4(read_5());
                        write_5(read_6());
                        break;
                    case 5:
                        write_5(read_6());
                        break;
                    case 6:
                        write_6(null);
                        break;
                    default:
                }
                //删除某一项后修改num的值
                SharedPreferences num_delete = getSharedPreferences("data", MODE_PRIVATE);
                int num_deleteInt = num_delete.getInt("num", 0);
                SharedPreferences.Editor editor_delete = getSharedPreferences("data",MODE_PRIVATE).edit();
                if(num_deleteInt<7) {
                    editor_delete.putInt("num", num_deleteInt - 1);
                }else {
                    editor_delete.putInt("num",num_deleteInt-2);
                }
                editor_delete.apply();
            }

            @Override
            public void OnLongClick(View view, int position) {
                Toast.makeText(Memo.this,"aaaa",Toast.LENGTH_SHORT).show();

            }
        });

        //读取num的值，获取需要读取键值对的个数
        SharedPreferences preferences_num = getSharedPreferences("data", MODE_PRIVATE);
        int num = preferences_num.getInt("num", 0);
        if(num<1){
            SharedPreferences.Editor editor_num = getSharedPreferences("data",MODE_PRIVATE).edit();
            editor_num.putInt("num",0);
            editor_num.apply();
        }

        switch (num) {
            case 1:
                read_1_1();
                break;
            case 2:
                read_1_2();
                break;
            case 3:
                read_1_3();
                break;
            case 4:
                read_1_4();
                break;
            case 5:
                read_1_5();
                break;
            case 6:
                read_1_6();
                break;
            case 7:
                read_1_6();
                break;
            default:
        }

    }

    //下一个界面返回信息的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                //获得num
                SharedPreferences grtNum = getSharedPreferences("data", MODE_PRIVATE);
                int num = grtNum.getInt("num", 0);
                String value = data.getStringExtra("key");
                //根据num选择对哪个键值对赋值
                if(value != null) {
                    switch(num){
                        case 1:
                            write_1(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            write_2(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        case 3:
                            write_3(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        case 4:
                            write_4(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        case 5:
                            write_5(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        case 6:
                            write_6(value);
                            list.add(new Rv_Data_memo(value));
                            rvAdapter.notifyDataSetChanged();
                            break;
                        default:
                            Toast.makeText(Memo.this,"添加失败，已达最大数量",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //限定数量
                    num--;
                    SharedPreferences.Editor putNum = getSharedPreferences("data",MODE_PRIVATE).edit();
                    putNum.putInt("num",num);
                    putNum.apply();
                }
                break;
            default:
        }
    }

    //read_1()--read_6()键值对向上赋值时的获取键值对方法
    String read_1(){
        SharedPreferences preferences_1 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1.getString("mome_1", "");
        return mome;
    }

    String read_2(){
        SharedPreferences preferences_2 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_2.getString("mome_2", "");
        return mome;
    }

    String read_3(){
        SharedPreferences preferences_3 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_3.getString("mome_3", "");
        return mome;
    }

    String read_4(){
        SharedPreferences preferences_4 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_4.getString("mome_4", "");
        return mome;
    }

    String read_5(){
        SharedPreferences preferences_5 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_5.getString("mome_5", "");
        return mome;
    }

    String read_6(){
        SharedPreferences preferences_6 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_6.getString("mome_6", "");
        return mome;
    }


    //write_1()--write_6()修改键值对方法
    void write_1(String string){
        SharedPreferences.Editor editorWrote_1 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_1.putString("mome_1",string);
        editorWrote_1.apply();
    }

    void write_2(String string){
        SharedPreferences.Editor editorWrote_2 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_2.putString("mome_2",string);
        editorWrote_2.apply();
    }

    void write_3(String string){
        SharedPreferences.Editor editorWrote_3 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_3.putString("mome_3",string);
        editorWrote_3.apply();
    }

    void write_4(String string){
        SharedPreferences.Editor editorWrote_4 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_4.putString("mome_4",string);
        editorWrote_4.apply();
    }

    void write_5(String string){
        SharedPreferences.Editor editorWrote_5 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_5.putString("mome_5",string);
        editorWrote_5.apply();
    }

    void write_6(String string){
        SharedPreferences.Editor editorWrote_6 = getSharedPreferences("data",MODE_PRIVATE).edit();
        editorWrote_6.putString("mome_6",string);
        editorWrote_6.apply();
    }

    //read_1_1--read_1_6打开界面加载便签rv时的读取键值对方法
    void read_1_1(){
        SharedPreferences preferences_1_1 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_1.getString("mome_1", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

    void read_1_2(){
        read_1_1();
        SharedPreferences preferences_1_2 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_2.getString("mome_2", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

    void read_1_3(){
        read_1_2();
        SharedPreferences preferences_1_3 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_3.getString("mome_3", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

    void read_1_4(){
        read_1_3();
        SharedPreferences preferences_1_4 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_4.getString("mome_4", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

    void read_1_5(){
        read_1_4();
        SharedPreferences preferences_1_5 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_5.getString("mome_5", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

    void read_1_6(){
        read_1_5();
        SharedPreferences preferences_1_6 = getSharedPreferences("data", MODE_PRIVATE);
        String mome = preferences_1_6.getString("mome_6", "");
        list.add(new Rv_Data_memo(mome));
        rvAdapter.notifyDataSetChanged();
    }

}