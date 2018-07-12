package com.example.elcomplus.esms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.elcomplus.esms.adapters.BankAdapter;
import com.example.elcomplus.esms.databases.BankDatahelper;
import com.example.elcomplus.esms.models.Bank;
import com.example.elcomplus.esms.mvp.BankPresenter;
import com.example.elcomplus.esms.mvp.BankView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BankView {

    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.rc_viewBank)
    RecyclerView rcViewBank;
    Bank bank;
    LinearLayoutManager manager;
    List<Bank> list;
    BankAdapter adapter;
    private BankPresenter presenter;
    BankDatahelper bankDatahelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list=new ArrayList<>();
        manager=new LinearLayoutManager(MainActivity.this);
        adapter=new BankAdapter(MainActivity.this,list);
        bankDatahelper=new BankDatahelper(MainActivity.this);
        list.addAll(bankDatahelper.getAll());
        rcViewBank.setLayoutManager(manager);
        rcViewBank.setAdapter(adapter);
        presenter=new BankPresenter(MainActivity.this,this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=edtPhone.getText().toString();
                presenter.insert(phone);
            }
        });
        adapter.setOnItemClick(new BankAdapter.OnItemClick() {
            @Override
            public void onClick(int postion) {
                showAlertDialog(postion);
            }
        });
    }



    @Override
    public void display(List<Bank> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void show(List<Bank> list) {
        list.clear();
        list.addAll(bankDatahelper.getAll());
        adapter.notifyDataSetChanged();
        rcViewBank.setAdapter(adapter);
    }

    public void showAlertDialog(final int id){
        final AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Chú ý!").setMessage("Bạn có chắc chắn muốn xóa bản ghi?").setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               presenter.delete(id);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
