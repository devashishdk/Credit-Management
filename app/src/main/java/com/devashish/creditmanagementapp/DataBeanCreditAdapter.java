package com.devashish.creditmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBeanCreditAdapter extends RecyclerView.Adapter<DataBeanCreditAdapter.ViewHolder>{
    private List<DataBean> items;
    private int itemLayout;
    DatabaseHelper helper;
    Context context;
    int IDD,CreditT;
    String name,credit,email;
    public DataBeanCreditAdapter(Context context,List<DataBean> items, int itemLayout){
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
        this.helper = new DatabaseHelper(context);
        IDD = ((Activity) context).getIntent().getIntExtra("id",1);
        CreditT = ((Activity) context).getIntent().getIntExtra("credit",0);
        name = ((Activity) context).getIntent().getStringExtra("name");
        email = ((Activity) context).getIntent().getStringExtra("email");
        credit = ((Activity) context).getIntent().getStringExtra("crdt");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DataBean item = items.get(position);
        final int ID = item.getID();
        holder.name.setText(item.getNAME());
        holder.id.setText(String.valueOf(item.getID()));
        holder.email.setText(item.getEMAIL());
        holder.credit.setText(item.getCREDIT());
        holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean upd = helper.updateData(String.valueOf(item.getID()),item.getNAME(),item.getEMAIL(),String.valueOf(Integer.parseInt(item.getCREDIT()) + CreditT));
                    if(upd) {
                        Toast.makeText(context,String.valueOf(item.getID()),Toast.LENGTH_LONG).show();
                        helper.updateData(String.valueOf(50+IDD), name, email, String.valueOf(Integer.parseInt(credit) - CreditT));
                    }
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);

                }
            });
        //All the thing you gonna show in the item
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView email;
        public TextView credit,id;
        CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            credit = (TextView) itemView.findViewById(R.id.credit);
            id = (TextView) itemView.findViewById(R.id.id);
            card = (CardView) itemView.findViewById(R.id.card);
        }
    }
    public DataBean getData(String name) {
        //select _id,Name,Card,Code
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_1, DatabaseHelper.COL_2, DatabaseHelper.COL_3,DatabaseHelper.COL_4};
        DataBean bean=null;
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.COL_2 + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DatabaseHelper.COL_1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.COL_2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.COL_3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.COL_4);
            int id = cursor.getInt(index1);
            String nameUser = cursor.getString(index2);
            String email = cursor.getString(index3);
            String credit = cursor.getString(index4);
            //buffer.append(name + " " + card + " " + code + "\n");
            bean = new DataBean(id,nameUser,email, credit);
        }
        return bean;
    }
}

