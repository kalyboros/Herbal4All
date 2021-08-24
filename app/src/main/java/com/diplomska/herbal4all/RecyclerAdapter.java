package com.diplomska.herbal4all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    String data[];
    Context context;

    public RecyclerAdapter( Context context, String[] data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        //TODO: tu se spilam s stringi pa jih cutam!
        holder.Ime.setText(data[position]);


        holder.Ime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Povezave kmalu na voljo!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Ime;
        /*
        TextView Delovanje;
        TextView Oblika;
        TextView Opozorilo;
        TextView Priprava;
        TextView Ucinkovina;
        TextView imgURL;
        */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Ime = itemView.findViewById(R.id.textViewRec1);
            /*
            Delovanje = itemView.findViewById(R.id.textViewRec1);
            Oblika = itemView.findViewById(R.id.textViewRec1);
            Opozorilo = itemView.findViewById(R.id.textViewRec1);
            Priprava = itemView.findViewById(R.id.textViewRec1);
            Ucinkovina = itemView.findViewById(R.id.textViewRec1);

             */
            //TODO: image




        }
    }
}
