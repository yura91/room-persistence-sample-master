package com.nagarro.persistence.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nagarro.persistence.R;
import com.nagarro.persistence.adapter.VersionStatisticAdapter;
import com.nagarro.persistence.core.GetDataContract;
import com.nagarro.persistence.core.Presenter;
import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;


public class MainFragment extends Fragment implements GetDataContract.View {
    private Presenter mPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    VersionStatisticAdapter forecastAdapter;
    ProgressBar progressBar;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mPresenter = new Presenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllData(getContext());
    }

    public Presenter getmPresenter() {
        return mPresenter;
    }

    public void setmPresenter(Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override

    public void onStart() {
        super.onStart();

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
        progressBar.setVisibility(View.GONE);
        forecastAdapter = new VersionStatisticAdapter(getContext(), versionInfo, mPresenter);
        recyclerView.setAdapter(forecastAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        progressBar.setVisibility(View.GONE);
        Log.d("Status", message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String mesDate) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), mesDate, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        forecastAdapter.unRegisterResiver();
    }
}
