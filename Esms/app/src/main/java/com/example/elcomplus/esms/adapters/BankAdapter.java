package com.example.elcomplus.esms.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elcomplus.esms.R;
import com.example.elcomplus.esms.databases.BankDatahelper;
import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    BankDatahelper bankDatahelper;
    Bank bank;
    private OnItemClick itemClick;
    Context context;
    List<Bank> list;
    public void setOnItemClick(OnItemClick itemClick){
        this.itemClick=itemClick;
    }

    public BankAdapter(Context context, List<Bank> list) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Holder holder1=(Holder) holder;
        holder1.txt_phone.setText(list.get(position).getPhone());
        holder1.txt_id.setText(list.get(position).getId()+"");
        holder1.setIsRecyclable(false);
        bank=new Bank();
        bankDatahelper=new BankDatahelper(context);
        bank=bankDatahelper.getBank(list.get(position).getPhone());
        holder1.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClick(bank.getId());

            }
        });

    }
    public interface OnItemClick {
        void onClick(int postion);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView txt_phone,txt_id;
        ImageView image;
        public Holder(View itemView) {
            super(itemView);
            txt_phone=(TextView) itemView.findViewById(R.id.txt_phone);
            txt_id=(TextView) itemView.findViewById(R.id.txt_id);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
