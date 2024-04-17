package Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonecall.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder> {
    private List<CallLogItem> callLogs = new ArrayList<>();

    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_recents, parent, false);
        return new CallLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        CallLogItem currentCallLog = callLogs.get(position);

        if (currentCallLog.getName() != null) {
            holder.textViewName.setText(currentCallLog.getName());
        } else {
            holder.textViewName.setText("vide");
        }
        holder.textViewNumber.setText(currentCallLog.getNumber());
//        holder.textViewName.setText(currentCallLog.getName());
//        holder.textViewNumber.setText(currentCallLog.getNumber());
        holder.textViewDuration.setText(currentCallLog.getDuration()+ "s");
        // Conversion de la date en une chaîne lisible
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(new Date(currentCallLog.getCallDate()));
        holder.textViewDate.setText(formattedDate);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCallLog(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return callLogs.size();
    }

    public void setCallLogs(List<CallLogItem> callLogs) {
        this.callLogs = callLogs;
        notifyDataSetChanged();
    }

    static class CallLogViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNumber;
        private TextView textViewName;
        private TextView textViewDuration;
        private TextView   textViewDate;
        private ImageView delete;


        public CallLogViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            delete = itemView.findViewById(R.id.delete);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
    public void removeCallLog(int position) {
        if (position >= 0 && position < callLogs.size()) {
            callLogs.remove(position);
            notifyItemRemoved(position);
        }
    }
}
