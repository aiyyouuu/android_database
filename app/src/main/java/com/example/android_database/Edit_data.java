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
    private EditText etNama, etAlamat, etSiswa, etGuru ;
    private Button btnSubmit;
    private String setNama, setAlamat, setSiswa, setGuru ;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etNama = findViewById(R.id.et_nama_sekolah);
        etAlamat = findViewById(R.id.et_alamat_sekolah);
        etSiswa = findViewById(R.id.et_jml_siswa);
        etGuru = findViewById(R.id.et_jml_guru);

        btnSubmit = findViewById(R.id.btn_submit);
        sekolahPresenter = new SekolahPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setNama = getIntent().getStringExtra("nama");
        setAlamat = getIntent().getStringExtra("alamat");
        setSiswa = getIntent().getStringExtra("jml_siswa");
        setGuru = getIntent().getStringExtra("jml_guru");

        id = getIntent().getIntExtra("id", 99);

        etNama.setText(setNama);
        etAlamat.setText(setAlamat);
        etSiswa.setText(setSiswa);
        etGuru.setText(setGuru);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        etSiswa.setText("");
        etGuru.setText("");
        etNama.setText("");
        etAlamat.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataSekolah item) {
        etNama.setText(item.getNama_sekolah());
        etAlamat.setText(item.getAlamat());
        etSiswa.setText(item.getJml_siswa());
        etGuru.setText(item.getJml_guru());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String JumlahSiswa, JumlahGuru, NamaSekolah, AlamatSekolah;
        JumlahSiswa = etSiswa.getText().toString();
        JumlahGuru = etGuru.getText().toString();
        NamaSekolah = etNama.getText().toString();
        AlamatSekolah = etAlamat.getText().toString();
        if(v ==  btnSubmit){
            if(JumlahSiswa.equals("") || JumlahGuru.equals("") || NamaSekolah.equals("") || AlamatSekolah.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                    sekolahPresenter.editData(JumlahSiswa, JumlahGuru, NamaSekolah, AlamatSekolah, id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }

