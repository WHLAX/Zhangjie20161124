package com.bwei.test.zhangjie20161124.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.test.zhangjie20161124.R;
import com.bwei.test.zhangjie20161124.bean.Bean;

import java.util.List;

/**
 * 1.recyclerView适配器
 * 2.张杰
 * 3.2016/11/
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Bean.ResultBean.DataBean> list;

    public MyRecyclerViewAdapter(List<Bean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //加载视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getContent());
        holder.tv1.setText(list.get(position).getUpdatetime());
    }

    //获取条目数
    @Override
    public int getItemCount() {
        return list.size();
    }




}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    TextView tv1;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_name);
        tv1 = (TextView) itemView.findViewById(R.id.tv_time);


    }
}