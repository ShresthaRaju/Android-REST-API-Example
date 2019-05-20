package com.raju.androidrestapiexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raju.androidrestapiexample.R;
import com.raju.androidrestapiexample.model.Flag;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FlagsAdapter extends RecyclerView.Adapter<FlagsAdapter.FlagHolder> {

    private Context context;
    private List<Flag> flagList;
    private String imageUri = "http://sujitg.com.np/wc/teams/";

    public FlagsAdapter(Context context, List<Flag> flagList) {
        this.context = context;
        this.flagList = flagList;
    }

    @NonNull
    @Override
    public FlagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.item_flag_layout, parent, false);
        return new FlagHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagHolder holder, int position) {

        Flag flag = flagList.get(position);
        holder.bindData(flag);
    }

    @Override
    public int getItemCount() {
        return flagList.size();
    }

    public class FlagHolder extends RecyclerView.ViewHolder {
        private ImageView imgFlag;
        private TextView tvCountry;

        public FlagHolder(@NonNull View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            tvCountry = itemView.findViewById(R.id.tvCountryName);
        }

        public void bindData(Flag flag) {
            tvCountry.setText(flag.getCountry());
            Picasso.get().load(imageUri + flag.getFile()).into(imgFlag);
        }

    }

}
