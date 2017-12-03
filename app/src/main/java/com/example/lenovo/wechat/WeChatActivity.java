package com.example.lenovo.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 /* Created by gaogao on 2017/12/1.
 */

public class WeChatActivity extends Activity {
    private String time;//时间（会在每次发送或接收数据时重新赋值）
    private String path1 = "http://op.juhe.cn/robot/index?info=";//接口路径
    private String path2 = "&dtype=&loc=&lon=&lat=&userid=&key=8a2e2e11c3453a5fd3002b864174e440";//发送的各种参数及key值(这里就不过多设置参数了，直接默认)
    private String text = "";
    private TextView middle;
    private EditText ed_message;
    private List<MessageUtil> list = new ArrayList<MessageUtil>();
    private MyAdapter adapter;
    private ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        //获取控件
        middle = (TextView) findViewById(R.id.middle);
        ed_message = (EditText) findViewById(R.id.ed_message);
        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setDividerHeight(0);//去掉黑线
        middle.setText("聊天界面");//设置头部标题
        //将初始值放入list集合
        MessageUtil util = new MessageUtil();
        util.setJudge(false);
        util.setTime(getTime());
        util.setMessage("您好！我是小灵！");
        list.add(util);
        //定义自定义适配器并赋值
        adapter = new MyAdapter(WeChatActivity.this, list);
        //将自定义适配器添加到listview
        list_view.setAdapter(adapter);
    }
    //获取时间，每次调用都会获得调用时的当前时间。
    public String getTime() {
        Calendar c = Calendar.getInstance();
        time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "  " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        Log.e("时间", time);
        return time;
    }
    //按钮点击事件
    public void submit(View view) {
        String message = ed_message.getText().toString();
        //输入不能为空
        if (message == null || message.equals("")) {
            Toast.makeText(WeChatActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            //添加自己发送的数据到list集合
            MessageUtil util = new MessageUtil();
            util.setJudge(true);
            util.setTime(getTime());
            util.setMessage(message);
            list.add(util);
            //添加改变值
            adapter.notifyDataSetChanged();
            //显示listview最后一个值
            list_view.setSelection(list.size());
            //网络操作
            http_(message);
        }
        ed_message.setText("");
    }

    //网络访问操作
    public void http_(final String string) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //保证编码格式正确
                    String city = URLEncoder.encode(string, "UTF-8");
                    //拼接地址与参数
                    URL url = new URL(path1 + city + path2);
                    //访问
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    //获得返回值
                    String info = getInfo(is);
                    Log.e("****", info);
                    //子线程无法操作UI，所以讲数据传递给handler。
                    Message message = handler.obtainMessage();
                    message.obj = info;
                    message.what = 123;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    //获得返回数据，调用解析方法
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 123) {
                text = (String) msg.obj;
                json(text);
            }
        }
    };

    //读取返回数据将其转换成String型并返回
    public String getInfo(InputStream is) {
        String info = null;
        byte[] by = new byte[1024];
        StringBuilder builder = new StringBuilder();
        int count = -1;
        try {
            while ((count = is.read(by)) != -1) {
                builder.append(new String(by, 0, count));
            }
            info = builder.toString();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    //解析返回的jsons数据，并将值添加到list集合后保存添加数据（应该将解析方法与添加list集合的方法写成两个方法的，这里就懒得分开了）
    public void json(String string) {
        try {
            JSONObject object = new JSONObject(string);
            String back = object.getString("result");
            JSONObject object1 = new JSONObject(back);
            String back_message = object1.getString("text");
            MessageUtil util = new MessageUtil();
            util.setJudge(false);
            util.setTime(getTime());
            util.setMessage(back_message);
            list.add(util);
            adapter.notifyDataSetChanged();
            list_view.setSelection(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
