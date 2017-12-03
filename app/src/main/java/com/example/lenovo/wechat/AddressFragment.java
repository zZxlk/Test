package com.example.lenovo.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AddressFragment extends Fragment {
    //private Button btn_enter;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_enter = (Button) getActivity().findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeChatActivity,WeChatActivity.class);
                startActivity(intent);
            }
        });
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page_02, container, false);
        imageView3 = (ImageView) v.findViewById(R.id.imageView3);
        imageView4 = (ImageView) v.findViewById(R.id.imageView4) ;
        imageView5 = (ImageView) v.findViewById(R.id.imageView5) ;

        textView2 = (TextView) v.findViewById(R.id.textView2);
        textView3 = (TextView) v.findViewById(R.id.textView3);
        textView4 = (TextView) v.findViewById(R.id.textView4);

        textView2.setOnClickListener(new LocationCheckedListener());
        textView3.setOnClickListener(new LocationCheckedListener());
        textView4.setOnClickListener(new LocationCheckedListener());

        imageView3.setOnClickListener(new LocationCheckedListener());
        imageView4.setOnClickListener(new LocationCheckedListener());
        imageView5.setOnClickListener(new LocationCheckedListener());

        return v;
    }

    class LocationCheckedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), WeChatActivity.class);  //从前者跳到后者，特别注意的是，在fragment中，用getActivity()来获取当前的activity
            getActivity().startActivity(intent);
        }

    }
}