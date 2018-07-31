package com.nagarro.persistence.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nagarro.persistence.R;
import com.nagarro.persistence.core.GetDataContract;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;


public class DetailFragment extends Fragment implements GetDataContract.View {
    TextView txtVersion_number;
    TextView txtVersion_name;
    TextView txtVersion_released;
    TextView txtVersion_api;
    TextView txtDestribution;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        txtVersion_name = (TextView) view.findViewById(R.id.version_name);
        txtVersion_number = (TextView) view.findViewById(R.id.version_number);
        txtVersion_released = (TextView) view.findViewById(R.id.version_released);
        txtVersion_api = (TextView) view.findViewById(R.id.version_api);
        txtDestribution = (TextView) view.findViewById(R.id.version_distribution);
        VersionInfo versionInfo = getArguments().getParcelable("versionInfo");
        txtVersion_name.setText(versionInfo.getVersion_name());
        txtVersion_number.setText(versionInfo.getVersion_number());
        txtVersion_released.setText(versionInfo.getReleased_date());
        txtVersion_api.setText(versionInfo.getApi());
        txtDestribution.setText(versionInfo.getDistribution().toString());
        if (versionInfo.isFavourite()) {
            view.findViewById(R.id.isFav).setBackgroundResource(android.R.drawable.btn_star_big_on);
        } else {
            view.findViewById(R.id.isFav).setBackgroundResource(android.R.drawable.btn_star_big_off);
        }
        view.findViewById(R.id.isFav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (versionInfo.isFavourite()) {
                    versionInfo.setFavourite(false);
                    AppDatabase.getAppDatabase(getContext()).userDao().updateVersionInfo(versionInfo);
                    view.findViewById(R.id.isFav).setBackgroundResource(android.R.drawable.btn_star_big_off);
                    Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                    intent.putExtra("vers", versionInfo);
                    getActivity().sendBroadcast(intent);

                } else {
                    versionInfo.setFavourite(true);
                    AppDatabase.getAppDatabase(getContext()).userDao().updateVersionInfo(versionInfo);
                    view.findViewById(R.id.isFav).setBackgroundResource(android.R.drawable.btn_star_big_on);
                    Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                    intent.putExtra("vers", versionInfo);
                    getActivity().sendBroadcast(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onGetDataSuccess(List<VersionInfo> versionInfo) {

    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String mesDate) {
        Toast.makeText(getContext(), mesDate, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }
}
