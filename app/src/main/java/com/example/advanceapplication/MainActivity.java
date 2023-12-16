package com.example.advanceapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText layout_stdNum, layout_stdName, layout_stdBirthday, layout_stdEmail;
    Button insertBtn, updateBtn, deleteBtn, viewBtn;

    String regNo, fullName, birthDay, email;

    DBHelper db;

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

        db = new DBHelper(MainActivity.this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData(1)) {
                    if (db.insertData(regNo, fullName, birthDay, email)) {
                        Toast.makeText(MainActivity.this, "Successful !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Unsuccessful !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData(2)) {
                    if (db.updateData(regNo, fullName, birthDay, email)) {
                        Toast.makeText(MainActivity.this, "Successful !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Unsuccessful !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData(3)) {
                    if (db.deleteData(regNo)) {
                        Toast.makeText(MainActivity.this, "Successful !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Unsuccessful !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getData();
                if (cursor.getCount() > 0) {
                    StringBuilder data = new StringBuilder();
                    while (cursor.moveToNext()) {
                        data.append("Registration Number : ").append(cursor.getString(0)).append("\n");
                        data.append("Registration Name : ").append(cursor.getString(1)).append("\n");
                        data.append("Registration Birthday : ").append(cursor.getString(2)).append("\n");
                        data.append("Registration Email : ").append(cursor.getString(3)).append("\n\n");
                    }
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Database Data");
                    alert.setMessage(data.toString());
                    alert.setCancelable(true);
                    alert.show();
                } else {
                    Toast.makeText(MainActivity.this, "No Data in Database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //type == 1 (insert data)
    //type == 2 (update data)
    private boolean validateData(int type) {
        if (layout_stdNum.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter your registration number !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (type == 1) {
            if (layout_stdName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your full name !", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (layout_stdBirthday.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your birthday !", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (layout_stdEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your email !", Toast.LENGTH_SHORT).show();

            }
            return true;
        } else if (type == 2) {
            boolean x = false;
            if (!layout_stdName.getText().toString().isEmpty()) {
                x = true;
            }
            if (!layout_stdBirthday.getText().toString().isEmpty()) {
                x = true;
            }
            if (!layout_stdEmail.getText().toString().isEmpty()) {
                x = true;
            }
            if (!x) {
                Toast.makeText(this, "Fill one or more to continue !", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        regNo = layout_stdNum.getText().toString().isEmpty() ? null : layout_stdNum.getText().toString();
        fullName = layout_stdName.getText().toString().isEmpty() ? null : layout_stdName.getText().toString();
        birthDay = layout_stdBirthday.getText().toString().isEmpty() ? null : layout_stdBirthday.getText().toString();
        email = layout_stdEmail.getText().toString().isEmpty() ? null : layout_stdEmail.getText().toString();
        return true;
    }
}