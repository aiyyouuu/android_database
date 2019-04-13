package com.example.android_database.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_database.Edit_data;
import com.example.android_database.Entity.DataSekolah;
import com.example.android_database.R;
import com.example.android_database.main.MainContact;

import java.util.List;

public class SekolahAdapter extends RecyclerView.Adapter<SekolahAdapter.ViewHolder> {
    Context context;
    List<DataSekolah> list;
    MainContact.hapus view;

    public SekolahAdapter(Context context, List<DataSekolah> list, MainContact.hapus view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SekolahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_lihat_data, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataSekolah data = list.get(i);
        viewHolder.TV_1.setText(data.getJml_siswa());
        viewHolder.TV_2.setText(data.getJml_guru());
        viewHolder.TV_3.setText(data.getNama_sekolah());
        viewHolder.TV_4.setText(data.getAlamat());
        viewHolder.id.setText(String.valueOf(data.getId()));
        viewHolder.btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteData(data);
                // return true;
            }
        });
        viewHolder.btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, Edit_data.class);
                x.putExtra("jml_siswa", data.getJml_siswa());
                x.putExtra("jml_guru", data.getJml_guru());
                x.putExtra("nama", data.getNama_sekolah());
                x.putExtra("alamat", data.getAlamat());
                x.putExtra("id", data.getId());
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(x);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TV_1, TV_2, TV_3, TV_4,id;
        Button btn_1, btn_2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_1 = itemView.findViewById(R.id.tv_item_jml_siswa);
            TV_2 = itemView.findViewById(R.id.tv_item_jml_guru);
            TV_3 = itemView.findViewById(R.id.tv_item_nama_sekolah);
            TV_4 = itemView.findViewById(R.id.tv_item_alamat);
            btn_1 = itemView.findViewById(R.id.btn_hapus);
            btn_2 = itemView.findViewById(R.id.btn_edit);
            id = itemView.findViewById(R.id.tv_item_id);
        }
    }
}
