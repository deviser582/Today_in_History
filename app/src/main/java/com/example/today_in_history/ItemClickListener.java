package com.example.today_in_history;

import android.view.View;

public interface ItemClickListener {
    void OnClick(View view,int position);
    void OnLongClick(View view,int position);
}
