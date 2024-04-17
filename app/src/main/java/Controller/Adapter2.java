package Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonecall.ContactsFragment;
import com.example.phonecall.OnItemClickListener;
import com.example.phonecall.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Data;
import Views.AfficheData;
import Views.EditData;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder> {
    private OnItemClickListener listener;
    private Context context;
    private java.util.List<Data> List;
    private DatabaseHelper databaseHelper;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setfiltereadlist(List<Data> filtereadlist){
        this.List = filtereadlist;
        notifyDataSetChanged();
    }

    public Adapter2(Context context, List<Data> list, DatabaseHelper databaseHelper) {
        this.context = context;
        List = list;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recents,parent,false);
        return new Adapter2.MyViewHolder(itemView);

    }

    public void onBindViewHolder(@NonNull final Adapter2.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Data data = List.get(position);
        holder.data.setText(data.getFirstname());
        holder.data2.setText(data.getTimeStamp());


        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,String.valueOf(data.getId()),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, AfficheData.class);
                intent.putExtra("position",String.valueOf(data.getId()));
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return List.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data ,data2;
        public ImageView info;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            data2 = itemView.findViewById(R.id.data2);

            info   = itemView.findViewById(R.id.info);


        }
    }
//    private String formatDate(String dateStr){
//        try {
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = fmt.parse(dateStr);
//            SimpleDateFormat fmtOut  = new SimpleDateFormat("MMM d");
//            return  fmtOut.format(date);
//        } catch (ParseException e) {
//            Log.e("error", e.getMessage());
//        }
//        return "";
//    }
//    private  void deleteData(int position){
//        databaseHelper.deleteData(List.get(position));
//        List.remove(position);
//        ContactsFragment.notifyAdapter();
//
//    }



}
