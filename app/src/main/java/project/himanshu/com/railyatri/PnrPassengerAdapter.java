package project.himanshu.com.railyatri;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PnrPassengerAdapter extends RecyclerView.Adapter<PnrPassengerAdapter.Data> {

    ArrayList<PnrPassengerData> pnrPassengerAdapterArrayList;

    public PnrPassengerAdapter(ArrayList<PnrPassengerData> pnrPassengerAdapterArrayList) {
        this.pnrPassengerAdapterArrayList = pnrPassengerAdapterArrayList;
    }

    @NonNull
    @Override
    public PnrPassengerAdapter.Data onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_pnr_passenger_adapter,null);
        return new Data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PnrPassengerAdapter.Data data, int i) {

        data.number.setText(pnrPassengerAdapterArrayList.get(i).no.toString());
        data.current.setText(pnrPassengerAdapterArrayList.get(i).current_status.toString());

    }

    @Override
    public int getItemCount() {
        return pnrPassengerAdapterArrayList.size();
    }

    public class Data extends RecyclerView.ViewHolder {
        TextView number, current;
        public Data(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.pnradapternumber);
            current = itemView.findViewById(R.id.pnradaptercurrent);

        }
    }
}
