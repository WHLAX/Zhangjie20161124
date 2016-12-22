package com.bwei.test.zhangjie20161124;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.bwei.test.zhangjie20161124.adapter.MyRecyclerViewAdapter;
import com.bwei.test.zhangjie20161124.bean.Bean;
import com.bwei.test.zhangjie20161124.okhttp.OkHttp;
import com.bwei.test.zhangjie20161124.view.PullBaseView;
import com.bwei.test.zhangjie20161124.view.PullRecyclerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;

/**
 * autour: 张杰
 * date: 2016/11/24 9:23
 * update: 2016/11/24
 * effect: 首页
 */
public class HomeActivity extends AppCompatActivity implements PullRecyclerView.OnHeaderRefreshListener,PullRecyclerView.OnFooterRefreshListener{

    private PullRecyclerView recycler;
    private int p=1;
    private int i=1;
    private String s="http://japi.juhe.cn/joke/content/list.from?key=%20874ed931559ba07aade103eee279bb37%20&page="+p+"&pagesize="+i+"&sort=asc&time=1418745237";
    private ArrayList<Bean.ResultBean.DataBean> list=new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //找到控件
        recycler=(PullRecyclerView) findViewById(R.id.recycler);
        //设置布局管理器
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //请求数据
        initshuju();
        //设置下拉监听
        recycler.setOnHeaderRefreshListener(this);
        //设置上拉监听
        recycler.setOnFooterRefreshListener(this);




    }

    /**
     * 请求数据
     */
    private void initshuju() {
        OkHttp.getAsync(s, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //解析数据
                Gson gson=new Gson();
                Bean bean=gson.fromJson(result,Bean.class);
                list.addAll(bean.getResult().getData());
                //适配器
                adapter=new MyRecyclerViewAdapter(list,HomeActivity.this);
                recycler.setAdapter(adapter);

            }
        });

    }

    //下拉加载
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                p++;
                i++;
                initshuju();
                adapter.notifyDataSetChanged();
                recycler.onFooterRefreshComplete();
            }
        },2000);
    }

    //上拉刷新
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mDatas.add(0, "TEXT新增");
                p++;
                initshuju();
                adapter.notifyDataSetChanged();
                recycler.onHeaderRefreshComplete();
            }
        }, 3000);
    }
}
