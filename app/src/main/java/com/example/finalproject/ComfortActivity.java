package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComfortActivity extends AppCompatActivity {

    private BuyActivity buyActivity;
//    private long timeLeft = Integer.parseInt(String.valueOf(buyActivity.mTime)) * 60000;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private CountDownTimer countDownTimer;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    Button btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32, btn33, btn34, btn35, btn36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        final ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btn1);buttons.add(btn2);buttons.add(btn3);buttons.add(btn4);buttons.add(btn5);buttons.add(btn6);
        buttons.add(btn7);buttons.add(btn8);buttons.add(btn9);buttons.add(btn10);buttons.add(btn11);buttons.add(btn12);
        buttons.add(btn13);buttons.add(btn14);buttons.add(btn15);buttons.add(btn16);buttons.add(btn17);buttons.add(btn18);
        buttons.add(btn19);buttons.add(btn20);buttons.add(btn21);buttons.add(btn22);buttons.add(btn23);buttons.add(btn24);
        buttons.add(btn25);buttons.add(btn26);buttons.add(btn27);buttons.add(btn28);buttons.add(btn29);buttons.add(btn30);
        buttons.add(btn31);buttons.add(btn32);buttons.add(btn33);buttons.add(btn34);buttons.add(btn35);buttons.add(btn36);

        // Init Firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        for(int i = 0; i < 36; i++) {
            myRef = mFirebaseDatabase.getReference().child("Computers").child("comfort").child(String.valueOf(i+1));
            final int finalI = i;
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int status = (int) dataSnapshot.child("status").getValue();
                    if(status == 3) {
                        buttons.get(finalI).setBackgroundColor(Integer.parseInt("#C4C4C4"));
                        buttons.get(finalI).setText(finalI);
                    } else if(status == 2) {
                        buttons.get(finalI).setBackgroundColor(Integer.parseInt("#DBDF12"));
                        buttons.get(finalI).setText(finalI);
                    } else if(status == 1) {
                        buttons.get(finalI).setBackgroundColor(Integer.parseInt("#AC2B2B"));
                        buttons.get(finalI).setText(finalI);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
        startActivity(new Intent(getApplicationContext(), BuyActivity.class));
//        startTimer();
        finish();
    }
}
