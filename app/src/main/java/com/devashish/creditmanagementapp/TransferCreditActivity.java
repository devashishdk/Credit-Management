package com.devashish.creditmanagementapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TransferCreditActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView mRecyclerView;
    DataBeanCreditAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_credit);
        databaseHelper = new DatabaseHelper(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(dbAdapter);
        getAllCards();
    }

    public void getAllCards(){
        List<DataBean> list = new ArrayList<>();
        String query = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DatabaseHelper.COL_1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.COL_2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.COL_3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.COL_4);
            //int cid = cursor.getInt(index);
            int index = cursor.getInt(index1);
            String name = cursor.getString(index2);
            String card = cursor.getString(index3);
            String code = cursor.getString(index4);

            DataBean bean = new DataBean(index,name, card, code);
            list.add(bean);
        }
        dbAdapter = new DataBeanCreditAdapter(TransferCreditActivity.this, list, R.layout.single_user);
        mRecyclerView.setAdapter(dbAdapter);
    }

    void insertDummyData()
    {
        databaseHelper.insertData(51,"Devashish","dksanu3@gmail.com","5000");
        databaseHelper.insertData(52,"Dk","dksanu@gmail.com","6000");
        databaseHelper.insertData(53,"Ram","ram@gmail.com","5000");
        databaseHelper.insertData(54,"shm","shm@gmail.com","5000");
    }
}
