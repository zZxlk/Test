package com.example.lenovo.wechat;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by panchengjia on 2016/12/28 0028.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    String[] group;
    String[][] itemName;
    int[][] itemIcon;
    public MyExpandableListAdapter(Context context, String[] group, String[][] itemName, int[][] itemIcon) {
        this.context = context;
        this.group = group;
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemName[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemName[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder vh;
        //ExpandableList的第一个分组没有组名，这里需要自定义布局
        if(groupPosition==0){
            convertView =LayoutInflater.from(context).inflate(R.layout.contact_list_title,null);
        }else{
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.contact_list_group_item,null);
                vh = new ViewHolder();
                vh.tv = (TextView) convertView.findViewById(R.id.group_tv);
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();

            vh.tv.setText(group[groupPosition]);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder vh;
        //ExpandableList的第一个分组没有组名，这里需要自定义布局
        if (groupPosition==0){
            convertView =LayoutInflater.from(context).inflate(R.layout.contact_list_title,null);
        }else{
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.contact_list_item,null);
                vh = new ViewHolder();
                vh.tv = (TextView) convertView.findViewById(R.id.contact_item_tv);
                vh.iv= (ImageView) convertView.findViewById(R.id.contact_item_iv);
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();
            vh.tv.setText(itemName[groupPosition][childPosition]);
            vh.iv.setImageResource(itemIcon[groupPosition][childPosition]);
        }
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ViewHolder{
        TextView tv;
        ImageView iv;
    }
}