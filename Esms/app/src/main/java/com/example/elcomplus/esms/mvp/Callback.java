package com.example.elcomplus.esms.mvp;

import com.example.elcomplus.esms.models.Bank;

import java.util.List;

public interface Callback {
    void onAddSuccess(Bank bank);
    void onAddFailure(String message);
    void onDeleteSuccess(String message);
    void onDeleteFailure(String message);
}
