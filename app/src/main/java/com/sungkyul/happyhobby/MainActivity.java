package com.sungkyul.happyhobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.happyhobby.Recycler.RecyclerAdapter;
import com.sungkyul.happyhobby.Room.AppDatabase;
import com.sungkyul.happyhobby.Room.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int SAVE_MEMO_ACTIVITY = 1;
    private FloatingActionButton add;

    //리사이클러 뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        initialized();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(v -> {
            move();
        });
    }


    private void initialized() {
        add = findViewById(R.id.addMemo);

        recyclerView = findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        users = AppDatabase.getInstance(this).userDao().getAll();
        int size = users.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(users.get(i));
        }
    }

    private void move() {
        Intent intent = new Intent(getApplicationContext(), SaveMemoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        users = AppDatabase.getInstance(this).userDao().getAll();
        adapter.addItems((ArrayList) users);
        super.onStart();
    }
}
