package com.nagarro.persistence.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nagarro.persistence.R;
import com.nagarro.persistence.activity.DetailActivity;
import com.nagarro.persistence.activity.MainActivity;
import com.nagarro.persistence.core.Presenter;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.dialog.YesNoDialog;
import com.nagarro.persistence.entity.VersionInfo;

import java.util.ArrayList;
import java.util.List;

public class VersionStatisticAdapter extends RecyclerView.Adapter<VersionStatisticAdapter.MyViewHolder> {
    private Context context;
    private Presenter mPresenter;
    private List<VersionInfo> versionInfos = new ArrayList<>();

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            VersionInfo versionInfo = intent.getParcelableExtra("vers");
            for (int i = 0; i < versionInfos.size(); i++) {
                if (versionInfos.get(i).getUid() == versionInfo.getUid()) {
                    versionInfos.get(i).setFavourite(versionInfo.isFavourite());
                    if (!MainActivity.isFavourite) {
                        notifyItemChanged(i);
                    } else {
                        versionInfos.remove(i);
                        notifyItemRemoved(i);
                    }
                }
            }
        }
    };

    public VersionStatisticAdapter(Context context, List<VersionInfo> versionInfos, Presenter mPresenter) {
        this.context = context;
        this.versionInfos = versionInfos;
        this.mPresenter = mPresenter;
        context.registerReceiver(broadcastReceiver, new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION"));
    }

    @Override
    public VersionStatisticAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, mPresenter);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(VersionStatisticAdapter.MyViewHolder holder, int position) {
        holder.version_number.setText(versionInfos.get(position).getVersion_number());
        holder.version_name.setText(versionInfos.get(position).getVersion_name());
        if (versionInfos.get(position).isFavourite()) {
            holder.isFavourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.isFavourite.setBackgroundResource(android.R.drawable.btn_star_big_off);

        }
    }

    @Override
    public int getItemCount() {
        return versionInfos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView version_number;
        TextView version_name;
        Presenter mPresenter;
        ImageButton isFavourite;

        public MyViewHolder(View itemView, Presenter mPresenter) {
            super(itemView);
            this.mPresenter = mPresenter;
            version_number = (TextView) itemView.findViewById(R.id.version_number);
            version_name = (TextView) itemView.findViewById(R.id.version_name);
            isFavourite = ((ImageButton) itemView.findViewById(R.id.favourite));
            isFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!versionInfos.get(getAdapterPosition()).isFavourite()) {
                        versionInfos.get(getAdapterPosition()).setFavourite(true);
                        AppDatabase.getAppDatabase(context).userDao().updateVersionInfo(versionInfos.get(getAdapterPosition()));
                        itemView.findViewById(R.id.favourite).setBackgroundResource(android.R.drawable.btn_star_big_on);
                        VersionInfo versionInfo = AppDatabase.getAppDatabase(context).userDao().findById(versionInfos.get(getAdapterPosition()).getUid());
                        Log.d("hklhlk", "" + versionInfo.getUid());

                    } else {
                        if (!MainActivity.isFavourite) {
                            versionInfos.get(getAdapterPosition()).setFavourite(false);
                            AppDatabase.getAppDatabase(context).userDao().updateVersionInfo(versionInfos.get(getAdapterPosition()));
                            itemView.findViewById(R.id.favourite).setBackgroundResource(android.R.drawable.btn_star_big_off);
                            VersionInfo versionInfo = AppDatabase.getAppDatabase(context).userDao().findById(versionInfos.get(getAdapterPosition()).getUid());
                            Log.d("hklhlk", "" + versionInfo.getUid());
                        } else {
                            versionInfos.get(getAdapterPosition()).setFavourite(false);
                            AppDatabase.getAppDatabase(context).userDao().updateVersionInfo(versionInfos.get(getAdapterPosition()));
                            versionInfos.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    }
                }
            });

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    YesNoDialog dialog = YesNoDialog.newInstance(context.getString(R.string.exit_confirmation_text));
                    dialog.setOnYesClickListener(new YesNoDialog.OnYesClickListener() {
                        @Override
                        public void onYesClick() {
                            AppDatabase.getAppDatabase(context).userDao().delete(versionInfos.get(getAdapterPosition()));
                            versionInfos.remove(versionInfos.get(getAdapterPosition()));
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });
                    dialog.show(((MainActivity) context).getSupportFragmentManager(), YesNoDialog.TAG);
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("versionInfo", versionInfos.get(getAdapterPosition()));
            context.startActivity(detailIntent);
        }
    }

    public void unRegisterResiver() {
        context.unregisterReceiver(broadcastReceiver);
    }

}
