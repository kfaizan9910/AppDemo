package com.devapp.demoapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devapp.demoapplication.R;
import com.devapp.demoapplication.model.Datum;
import com.devapp.demoapplication.model.GettingData;

import java.util.ArrayList;
import java.util.List;

public class GetData_Adapter extends RecyclerView.Adapter<GetData_Adapter.MyViewHolder> {
    Context context;
    List<Datum> gettingData;


    public GetData_Adapter(Context context, List<Datum> gettingData) {
        this.context = context;
        this.gettingData = gettingData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right));
        Datum getdata = gettingData.get(position);
        holder.Id.setText(getdata.getId());
        holder.EmpName.setText(getdata.getEmployeeName());
        holder.EmpSal.setText(getdata.getEmployeeName());
        holder.EmpAge.setText(getdata.getEmployeeName());

    }

    @Override
    public int getItemCount() {
        return gettingData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Id, EmpName,EmpSal,EmpAge;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.id);
            EmpName = itemView.findViewById(R.id.empName);
            EmpSal = itemView.findViewById(R.id.empSalary);
            EmpAge = itemView.findViewById(R.id.empAge);
            cardView= itemView.findViewById(R.id.card);

        }
    }

}
