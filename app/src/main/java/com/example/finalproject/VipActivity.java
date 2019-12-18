package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VipActivity extends AppCompatActivity {

//    private BuyActivity buyActivity;
//    private long timeLeft = Integer.parseInt(String.valueOf(buyActivity.mTime)) * 60000;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
//    private CountDownTimer countDownTimer;
    private RecyclerView recyclerView;
    private ArrayList<Computers> arrayList;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        // Init Firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Computers").child("vip");
        arrayList = new ArrayList<Computers>();

        recyclerView = findViewById(R.id.recyclerView_id);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Computers c = snapshot.getValue(Computers.class);
                    arrayList.add(c);
                }

                adapter = new RecyclerViewAdapter(VipActivity.this, arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));


//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot.getValue());
//                for(int i = 0; i < buttons.size(); i++) {
//                    String status = dataSnapshot.child(String.valueOf(i)).getValue().toString();
//                    if (Integer.parseInt(status) == 3) {
//                        Toast.makeText(VipActivity.this, "works 3", Toast.LENGTH_SHORT).show();
//                        buttons.get(i).setBackgroundColor(Integer.parseInt("#C4C4C4"));
//                        buttons.get(i).setText(i);
//                    } else if (Integer.parseInt(status) == 2) {
//                        Toast.makeText(VipActivity.this, "works 2", Toast.LENGTH_SHORT).show();
//                        buttons.get(i).setBackgroundColor(Integer.parseInt("#DBDF12"));
//                        buttons.get(i).setText(i);
//                    } else if (Integer.parseInt(status) == 1) {
//                        Toast.makeText(VipActivity.this, "works 1", Toast.LENGTH_SHORT).show();
//                        buttons.get(i).setBackgroundColor(Integer.parseInt("#AC2B2B"));
//                        buttons.get(i).setText(i);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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

//    public void startTimer() {
//        countDownTimer = new CountDownTimer(timeLeft, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeft = millisUntilFinished;
//                updateTimer();
//            }
//
//            @Override
//            public void onFinish() {
//                btn1.setBackgroundColor(Color.parseColor("#C4C4C4"));
//            }
//        }.start();
//    }
//
//    public void updateTimer() {
//        int minutes = (int) timeLeft / 60000;
//        int seconds = (int) timeLeft % 60000 / 1000;
//
//        String timeLeftText;
//        timeLeftText = "" + minutes;
//        timeLeftText += ":";
//        if(seconds < 10) timeLeftText += "0";
//        timeLeftText += seconds;
//
//        btn1.setText(timeLeftText);
//    }
}
