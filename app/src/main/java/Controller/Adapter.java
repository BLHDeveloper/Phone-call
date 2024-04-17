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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private OnItemClickListener listener;
    private Context context;
    private List<Data> List;
    private DatabaseHelper databaseHelper;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setfiltereadlist(List<Data> filtereadlist){
        this.List = filtereadlist;
        notifyDataSetChanged();
    }

    public Adapter(Context context, List<Data> list, DatabaseHelper databaseHelper) {
        this.context = context;
        List = list;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_info,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Data data = List.get(position);
        holder.data.setText(data.getFirstname());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,String.valueOf(data.getId()),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, EditData.class);
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
        holder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,String.valueOf(data.getId()),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, AfficheData.class);
                intent.putExtra("position",String.valueOf(data.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
        {
            return List.size();
        }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data;
        public ImageView delete,edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            delete  = itemView.findViewById(R.id.delete);
            edit   = itemView.findViewById(R.id.edit);


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
    private  void deleteData(int position){
        databaseHelper.deleteData(List.get(position));
        List.remove(position);
        ContactsFragment.notifyAdapter();

    }



}
