package com.example.lenovo.wechat;

          import android.app.Activity;
          import android.os.Bundle;
          import android.support.annotation.Nullable;
          import android.support.v4.app.Fragment;
          import android.view.LayoutInflater;
          import android.view.View;
          import android.view.ViewGroup;
          import android.widget.ExpandableListView;


public class HomeFragment extends Fragment {
              private ExpandableListView contact_list;
              //定义分组以及组内成员（设置头文件位置为空）
              String[] group ={"","好友列表"};
              String[][] itemName={{},{"郭嘉", "黄月英", "华佗",
                      "刘备", "陆逊", "吕布", "吕蒙", "马超", "司马懿", "孙权", "孙尚香", "夏侯惇",
                      "许褚", "杨修", "张飞", "赵云", "甄姬", "周瑜", "诸葛亮"}};
              int[][] itemIcon={{},{R.mipmap.guojia,
                      R.mipmap.huangyueying, R.mipmap.huatuo,
                      R.mipmap.liubei, R.mipmap.luxun, R.mipmap.guojia, R.mipmap.guojia,
                      R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia,
                      R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia,
                      R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia, R.mipmap.guojia}};
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                 View view = inflater.inflate(R.layout.page_01, container, false);
         contact_list = (ExpandableListView) view.findViewById(R.id.contact_list);
         //实例化适配器
         MyExpandableListAdapter myExpandableListAdapter=new MyExpandableListAdapter(getContext(),group,itemName,itemIcon);
         //配置适配器
         contact_list.setAdapter(myExpandableListAdapter);
         //去掉ExpandableListView 默认的箭头
         contact_list.setGroupIndicator(null);
         //设置ExpandableListView默认展开
         for (int i = 0; i <group.length; i++) {
             contact_list.expandGroup(i);
         }
         //设置ExpandableListView不可点击收回
         contact_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
             @Override
             public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                 return true;
             }
         });
         return view;
             }

         }