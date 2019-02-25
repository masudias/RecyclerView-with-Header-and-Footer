package com.example.masud.stackoverflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private RecyclerViewWithHeaderFooterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.dummy_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewWithHeaderFooterAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        for (int i = 0; i < 10; i++) data.add("Position :" + i);
    }
}