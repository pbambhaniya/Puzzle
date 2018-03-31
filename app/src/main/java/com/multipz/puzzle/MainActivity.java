package com.multipz.puzzle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerview, rview;
    NewsAdapter adapter;
    Context context;
    ArrayList<PuzzleModel> list;
    int clickCount = 0;
    ArrayList<DataModel> arrayList;
    DatabaseHelper db;
    String won;
    int id;
    BookMarkAdapter dataadapter;
    String event_name;
    SQLiteDatabase sd;
    String event_title;
    Button btn_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        db = new DatabaseHelper(context);
        sd = db.getWritableDatabase();
        sd = db.getReadableDatabase();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        list = new ArrayList<>();
        arrayList = new ArrayList<>();

        btn_data = (Button) findViewById(R.id.btn_data);
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });


        LawyerList();
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));
        list.add(new PuzzleModel(-1, true));


    }

    private void LawyerList() {
        adapter = new NewsAdapter(list, context);
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
//        rv_bookmark.setNestedScrollingEnabled(false);
        adapter.setClickListener(this);

    }


    @Override
    public void itemClicked(View view, int position) {

        int ccc = new Random().nextInt(2);

        PuzzleModel obj = list.get(position);
        obj.setNo(ccc);
        obj.setEnable(false);
        list.set(position, obj);

        clickCount++;
        boolean isWin = true;
        if (clickCount == 3) {
            clickCount = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNo() != -1) {
                    if (list.get(0).getNo() != list.get(i).getNo()) {
                        isWin = false;
                        break;
                    }
                }
            }
            if (isWin)
                Toast.makeText(context, "Won", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Loss", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < list.size(); i++) {
                list.set(i, new PuzzleModel(list.get(i).getNo(), false));
            }
        }
        if (clickCount == 0) {
            final boolean IsWin = isWin;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String str = "";
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getNo() != -1) {
                            str = str + list.get(i).getNo();
                        }
                        list.set(i, new PuzzleModel(-1, true));


                    }
                    if (IsWin) {
                        won = "Won";
                    } else {
                        won = "Loss";
                    }

                    boolean insert = db.insertData(str, won);
                    if (insert == true) {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                }
            }, 2000);
        }
        adapter.notifyDataSetChanged();


    }


}
