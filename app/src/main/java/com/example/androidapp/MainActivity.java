package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidapp.activity.ListProduct;
import com.example.androidapp.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private Button btAdd;
    private Button btView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        btAdd = findViewById(R.id.btAdd);
        btView = findViewById(R.id.btView);

        btAdd.setOnClickListener(this);
        btView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btAdd){
            onAdd();
        }
        if (view == btView){
            onView();
        }
    }

    private void onAdd() {
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edQuantity.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        String isAdd = db.add(edName.getText().toString(), edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void onView() {
        Intent intent = new Intent(MainActivity.this, ListProduct.class);
        startActivity(intent);
    }
}