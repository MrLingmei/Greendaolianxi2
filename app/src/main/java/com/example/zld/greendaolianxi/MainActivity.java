package com.example.zld.greendaolianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zld.dao.DaoMaster;
import com.example.zld.dao.DaoSession;
import com.example.zld.dao.UserDao;

import org.greenrobot.greendao.annotation.Index;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText ed_page;
    private EditText ed_name;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;
    private Button add;
    private Button query;
    private RecyclerView recy;
    private List<User> list;
    private Button delete;
    private TextView delete_name;
    private EditText up_name;
    private Button up;
    private Button delteall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devOpenHelper = new DaoMaster.DevOpenHelper(this, "user", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        initview();
        //插入数据
        insertDate();
        //查询
        queryDate();
        //删除
        deleteDate();
        //更改数据
        upDate();
        //删除全部
        delteAll();
    }

    private void delteAll() {
delteall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        list.clear();
        MyAdapter adapter = new MyAdapter(MainActivity.this,list);
        recy.setAdapter(adapter);
    }
});

    }

    private void initview() {
        ed_name = findViewById(R.id.ed_name);
        ed_page = findViewById(R.id.ed_page);
        recy = findViewById(R.id.recy);
        up_name = findViewById(R.id.up_name);
        up = findViewById(R.id.up);
        add = findViewById(R.id.add);
        query = findViewById(R.id.query);
        delete = findViewById(R.id.delete);
        delete_name = findViewById(R.id.delete_name);
        delteall = findViewById(R.id.delteall);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }
    //添加方法
    private void insertDate() {
        list = userDao.loadAll();
        MyAdapter adapter = new MyAdapter(MainActivity.this,list);
        recy.setAdapter(adapter);
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              User user = new User(null, ed_name.getText().toString(), ed_page.getText().toString());
              long insert = userDao.insert(user);
              String msg="";
              if (insert>0){
                  msg="添加成功";
              }else {
                  msg="添加失败";
              }
              ed_name.setText("");
              ed_page.setText("");
              list = userDao.loadAll();
              MyAdapter adapter = new MyAdapter(MainActivity.this,list);
              recy.setAdapter(adapter);
          }
      });

    }
    private void queryDate() {
     query.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              list = userDao.loadAll();
             MyAdapter adapter = new MyAdapter(MainActivity.this,list);
             recy.setAdapter(adapter);

         }
     });
    }
    private void deleteDate() {
     delete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String dename = delete_name.getText().toString();
             userDao.queryBuilder().where(UserDao.Properties.Name.eq("张三")).buildDelete().executeDeleteWithoutDetachingEntities();

             list = userDao.loadAll();
             MyAdapter adapter = new MyAdapter(MainActivity.this,list);
             recy.setAdapter(adapter);
         }
     });
    }
    private void upDate() {
      up.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String name = up_name.getText().toString();
              User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq("李四")).build().unique();
              if (user!=null){
                  user.setName(ed_name.getText().toString());
                  userDao.update(user);
                  String s = up_name.getText().toString();
                  user.setName(s);

              }
              queryDate();
          }

      });
    }

}
