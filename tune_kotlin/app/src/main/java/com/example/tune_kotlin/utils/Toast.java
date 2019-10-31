package com.example.tune_kotlin.utils;

import android.content.Context;

public final class Toast {
    public static void toast(Context context, String text){
        android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).show();
    }
}

