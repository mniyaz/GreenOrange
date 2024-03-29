package com.aottec.arkot.gps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aottec.arkot.gps.Model.DrawerObjectResponseModel;
import com.aottec.arkot.gps.Util.AppConstants;
import com.aottec.arkot.gps.Util.GlobalValues;

import java.util.ArrayList;

public class NavigationAdaptor extends RecyclerView.Adapter<NavigationAdaptor.ViewHolder>{
    Context context;
    ArrayList<DrawerObjectResponseModel> vechicleList;
    AddClickEvent onClickListener;
    GlobalValues globalValues;
    public NavigationAdaptor(Context context, ArrayList<DrawerObjectResponseModel> vechicleList) {
    this.context=context;
    this.vechicleList=vechicleList;
        globalValues=new GlobalValues(context);
        AppConstants.selectedPosition = -1;
       // AppConstants.selectedVechicle="";

    }
    public interface AddClickEvent {
        public void onListClick(int position);

    }

    public void setAddClickListener(AddClickEvent onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NavigationAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdaptor.ViewHolder viewHolder, final int position) {
        viewHolder.navItemName.setText(vechicleList.get(position).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {

                    onClickListener.onListClick(position);



                }
            }
        });

        viewHolder.checkBox.setChecked(vechicleList.get(position).getName().equals(globalValues.getString("selectedVechicle")));
    }

    @Override
    public int getItemCount() {
        return vechicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView navItemName;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            navItemName=itemView.findViewById(R.id.text_nav_item);
            checkBox=itemView.findViewById(R.id.checkBox);
           // img_navigation=itemView.findViewById(R.id.img_navigation);
        }
    }
    public void updateList(ArrayList<DrawerObjectResponseModel> list){
        vechicleList = list;
        notifyDataSetChanged();
    }
}
