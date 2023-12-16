package com.example.advanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText layout_stdNum, layout_stdName, layout_stdBirthday, layout_stdEmail;
    Button insertBtn, updateBtn, deleteBtn, viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_stdNum = findViewById(R.id.std_reg_num);
        layout_stdName = findViewById(R.id.std_reg_name);
        layout_stdBirthday = findViewById(R.id.std_reg_birthday);
        layout_stdEmail = findViewById(R.id.std_reg_email);

        insertBtn = findViewById(R.id.btn_insert);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        viewBtn = findViewById(R.id.btn_view);

    }
}