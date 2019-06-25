package com.devashish.creditmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSingleUser extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView nameText,emailText,creditText;
    Button transfer;
    EditText creditAmount;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_user);
        ID = getIntent().getIntExtra("id",1);
        databaseHelper = new DatabaseHelper(this);
        nameText = (TextView) findViewById(R.id.name);
        emailText = (TextView) findViewById(R.id.email);
        creditText = (TextView) findViewById(R.id.credits);
        creditAmount = (EditText) findViewById(R.id.creditAmount);
        transfer = (Button) findViewById(R.id.transfer);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSingleUser.this,TransferCreditActivity.class);
                intent.putExtra("id",ID);
                intent.putExtra("credit",Integer.parseInt(creditAmount.getText().toString()));
                intent.putExtra("name",nameText.getText().toString());
                intent.putExtra("email",emailText.getText().toString());
                intent.putExtra("crdt",creditText.getText().toString());
                startActivity(intent);
            }
        });
        Cursor cursor = databaseHelper.viewData();
        int count = ID;
        while (count > 0)
        {
            cursor.moveToNext();
            Toast.makeText(ViewSingleUser.this,cursor.getString(1),Toast.LENGTH_LONG).show();
            nameText.setText(cursor.getString(1));
            emailText.setText(cursor.getString(2));
            creditText.setText(cursor.getString(3));
            count--;
        }

    }
}
