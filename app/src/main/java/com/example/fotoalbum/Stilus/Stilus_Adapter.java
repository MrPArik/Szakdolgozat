package com.example.fotoalbum.Stilus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fotoalbum.Cim_stilus.Interface;
import com.example.fotoalbum.Cim_stilus.modell;
import com.example.fotoalbum.R;

import java.util.ArrayList;


public class Stilus_Adapter extends RecyclerView.Adapter<com.example.fotoalbum.Stilus.Stilus_Adapter.CimStilusHolder> {
    private final Interface cim_stilus_rec_interface;
    Context context;
    ArrayList<modell> cim_stilus_modells;

    public Stilus_Adapter(Context context, ArrayList<modell> cim_stilus_modells, Interface cim_stilus_rec_interface){
        this.context=context;
        this.cim_stilus_modells=cim_stilus_modells;
        this.cim_stilus_rec_interface=cim_stilus_rec_interface;
    }

    @NonNull
    @Override
    public com.example.fotoalbum.Stilus.Stilus_Adapter.CimStilusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.rec_kinezet,parent,false);
        return new com.example.fotoalbum.Stilus.Stilus_Adapter.CimStilusHolder(view,cim_stilus_rec_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.fotoalbum.Stilus.Stilus_Adapter.CimStilusHolder holder, int position) {
        holder.Szoveg.setText(cim_stilus_modells.get(position).getSzoveg());
        holder.Kep.setImageResource(cim_stilus_modells.get(position).getKep());
    }

    @Override
    public int getItemCount() {
        return cim_stilus_modells.size();
    }

    public static class CimStilusHolder extends RecyclerView.ViewHolder{

        ImageView Kep;
        TextView Szoveg;

        public CimStilusHolder(@NonNull View itemView, Interface cim_stilus_rec_interface) {
            super(itemView);
            Kep=itemView.findViewById(R.id.Cim_Stilus_kep);
            Szoveg=itemView.findViewById(R.id.Cim_Stilus_szoveg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cim_stilus_rec_interface!=null){
                        int pos=getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            cim_stilus_rec_interface.OnItemClicked(pos);

                        }
                    }
                }
            });

        }
    }
}
