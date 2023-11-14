package com.example.amirul.cafefoodorderingserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amirul.cafefoodorderingserver.Interface.ItemClickListener;
import com.example.amirul.cafefoodorderingserver.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

    public ItemClickListener itemClickListener;

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderAddress, txtOrderDate, txtOrderName, txtOrderPrice;
    public Button btnEdit, btnRemove, btnDetail, btnDirection;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderDate = (TextView)itemView.findViewById(R.id.order_date);
        txtOrderName = (TextView)itemView.findViewById(R.id.order_name);
        txtOrderPrice = (TextView)itemView.findViewById(R.id.order_price);

         btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
         btnRemove = (Button)itemView.findViewById(R.id.btnRemove);
         btnDetail = (Button)itemView.findViewById(R.id.btnDetail);
         btnDirection = (Button)itemView.findViewById(R.id.btnDirection);

         itemView.setOnClickListener(this);
         itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select The Action");

        contextMenu.add(0,0,getAdapterPosition(),"Update");
        contextMenu.add(0,1,getAdapterPosition(),"Update");
    }
}
