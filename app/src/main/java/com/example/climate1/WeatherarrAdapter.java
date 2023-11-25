package com.example.climate1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherarrAdapter extends RecyclerView.Adapter<WeatherarrAdapter.ViewHolder> {
    Context context;
    ArrayList<Arraydata> datamodalarray;

    public WeatherarrAdapter(Context context, ArrayList<Arraydata> datamodalarray) {
        this.context = context;
        this.datamodalarray = datamodalarray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.singleitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Arraydata dataa=datamodalarray.get(position);
        // this will take the wind speed fron the arraylist
        holder.wind.setText("wind speed"+dataa.getWind()+" kmph");
        //to get and load the picture
        Picasso.get().load("http:".concat(dataa.getIcon())).into(holder.icon);

        // here it takes and convet the date into the hour and second format
       SimpleDateFormat input=new SimpleDateFormat("YYYY-MM-dd hh:mm");
       SimpleDateFormat output=new SimpleDateFormat("hh:mm aa");
       try {
           Date t=input.parse(dataa.getTime());
           holder.time.setText(output.format(t));
       }
       catch (ParseException e) {
           throw new RuntimeException(e);
       }
       //here date code ends
    }

    @Override
    public int getItemCount() {
        return datamodalarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wind,time;
       // TextView condition;
       ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wind=itemView.findViewById(R.id.wind);
          // condition=itemView.findViewById(R.id.condition);
            time=itemView.findViewById(R.id.time);
           icon=itemView.findViewById(R.id.icon);
        }
    }
}
