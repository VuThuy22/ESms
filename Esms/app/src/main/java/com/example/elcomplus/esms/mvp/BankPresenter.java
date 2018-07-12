package com.example.elcomplus.esms.mvp;

import android.content.Context;
import android.nfc.Tag;
import android.widget.Toast;

import com.example.elcomplus.esms.MainActivity;
import com.example.elcomplus.esms.databases.BankDatahelper;
import com.example.elcomplus.esms.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankPresenter implements Callback {
    private static final String TAG = "BankPresenter";
    private BankView bankView;
    private BankModel bankModel;
    private List<Bank> list;
    BankDatahelper bankDatahelper;
    Context context;


    public BankPresenter(Context context,BankView bankView) {
        this.bankView = bankView;
        this.bankModel=new BankModel(context, this);
    }
    public void insert(String phone){
        bankModel.insertBank(phone);
    }
    public void delete(int id){
        bankModel.deleteBank(id);
    }



    @Override
    public void onAddSuccess(Bank bank) {
        list=new ArrayList<>();
        list.add(bank);
        bankView.display(list);
    }

    @Override
    public void onAddFailure(String message) {
    }

    @Override
    public void onDeleteSuccess(String message) {
        list=new ArrayList<>();
        bankView.show(list);
    }

    @Override
    public void onDeleteFailure(String message) {

    }
}
