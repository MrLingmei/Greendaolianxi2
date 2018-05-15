package com.example.zld.greendaolianxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zld on 2018/5/15.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
     Context context;
    List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.name.setText("ID:"+list.get(position).getId()+"   姓名:"+list.get(position).getName()+"   年龄:"+list.get(position).getPage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

      TextView name;


    public MyViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);

    }
}