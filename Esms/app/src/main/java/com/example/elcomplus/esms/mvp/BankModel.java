package com.example.elcomplus.esms.mvp;

import android.content.Context;

import com.example.elcomplus.esms.databases.BankDatahelper;
import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public class BankModel {
    private Callback callback;
    List<Bank> list;
    BankDatahelper bankDatahelper;
    Context context;
    Bank bank;

    public BankModel(Context context, Callback callback) {
        this.callback = callback;
        this.context =context;
    }


    public void insertBank(String phone){
        bank=new Bank(phone);
        bankDatahelper=new BankDatahelper(context);
        bankDatahelper.addBank(bank);
        bank=bankDatahelper.getBank(phone);
        callback.onAddSuccess(bank);
    }
    public void deleteBank(int id){
        bank=new Bank(id);
        bankDatahelper=new BankDatahelper(context);
        bankDatahelper.deletebank(bank);
        callback.onDeleteFailure("");
    }
}
