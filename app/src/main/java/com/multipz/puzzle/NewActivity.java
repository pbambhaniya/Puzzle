package com.multipz.puzzle;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {
    Context context;
    DatabaseHelper db;
    ArrayList<DataModel> arrayList;
    BookMarkAdapter dataadapter;
    String event_name;
    String event_title;
    int id;
    RecyclerView rvview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        context = this;

        rvview = (RecyclerView) findViewById(R.id.rvview);
        arrayList=new ArrayList<>();
        db = new DatabaseHelper(context);

        Cursor cr = db.getAllData();
        while (cr.moveToNext()) {

            id = cr.getInt(0);
            event_name = cr.getString(1);
            event_title = cr.getString(2);

            DataModel model = new DataModel();
            model.setId(String.valueOf(id));
            model.setNo(event_name);
            model.setPlay_category(event_title);
            arrayList.add(model);

        }

        dataadapter = new BookMarkAdapter(context, arrayList);
        RecyclerView.LayoutManager mLayoutManagers = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvview.setLayoutManager(mLayoutManagers);
        rvview.setItemAnimator(new DefaultItemAnimator());
        rvview.setAdapter(dataadapter);
//        rview.setNestedScrollingEnabled(false);
//        adapter.setClickListener(MainActivity.this);
//        adapter.notifyDataSetChanged();
    }
}
