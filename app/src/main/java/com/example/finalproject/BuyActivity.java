package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {

    EditText mTime, mCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mTime = (EditText)findViewById(R.id.edit_time);
        mCost = (EditText)findViewById(R.id.edit_cost);
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), VipActivity.class));
        finish();
    }

    public void menu(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void my_account(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void buy(View view) {
        mTime.getText();


    }
}
