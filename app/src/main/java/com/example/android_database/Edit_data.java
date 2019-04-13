package com.example.android_database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_database.Adapter.SekolahAdapter;
import com.example.android_database.Entity.AppDatabase;
import com.example.android_database.Entity.DataSekolah;
import com.example.android_database.Presenter.SekolahPresenter;
import com.example.android_database.main.LihatDataActivity;
import com.example.android_database.main.MainContact;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private SekolahPresenter sekolahPresenter;
    private SekolahAdapter sekolahAdapter;
    private EditText ET1, ET2, ET3, ET4;
    private Button btn1;
    private String SET_1, SET_2, SET_3, SET_4;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        ET1 = findViewById(R.id.et_jml_siswa);
        ET2 = findViewById(R.id.et_jml_guru);
        ET3 = findViewById(R.id.et_nama_sekolah);
        ET4 = findViewById(R.id.et_alamat_sekolah);
        btn1 = findViewById(R.id.btn_submit);
        sekolahPresenter = new SekolahPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        SET_1 = getIntent().getStringExtra("jml_siswa");
        SET_2 = getIntent().getStringExtra("jml_guru");
        SET_3 = getIntent().getStringExtra("nama");
        SET_4 = getIntent().getStringExtra("alamat");
        id = getIntent().getIntExtra("id", 99);
        ET1.setText(SET_1);
        ET2.setText(SET_2);
        ET3.setText(SET_3);
        ET4.setText(SET_4);
        btn1.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        ET1.setText("");
        ET2.setText("");
        ET3.setText("");
        ET4.setText("");
        btn1.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataSekolah item) {
        ET1.setText(item.getJml_siswa());
        ET2.setText(item.getJml_guru());
        ET3.setText(item.getNama_sekolah());
        ET4.setText(item.getAlamat());
        edit = true;
        btn1.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String J_S, J_G, N_S, Al;
        J_S = ET1.getText().toString();
        J_G = ET2.getText().toString();
        N_S = ET3.getText().toString();
        Al = ET4.getText().toString();
        if(v ==  btn1){
            if(J_S.equals("") || J_G.equals("") || N_S.equals("") || Al.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                    sekolahPresenter.editData(J_S, J_G, N_S, Al, id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }

