package com.example.walkfromhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends ArrayAdapter<WalkData> {

    Context context;
    List<WalkData> arrayListWalkData;

    public UserAdapter(@NonNull Context context, List<WalkData> arrayListWalkData) {
        super(context,R.layout.custom_list_item,arrayListWalkData);
        this.context = context;
        this.arrayListWalkData = arrayListWalkData;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tvDistance = view.findViewById(R.id.txt_distance);

        tvDistance.setText("ระยะทางที่ทำได้ในครั้งก่อน : "+arrayListWalkData.get(position).getDistance() + " เมตร");

        return view;
    }
}
