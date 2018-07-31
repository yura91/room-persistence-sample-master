package com.nagarro.persistence.core;

import android.content.Context;

import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;

public class Presenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private Intractor mIntractor;

    public Presenter(GetDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }

    @Override
    public void getAllData(Context context) {
        mIntractor.getAllVersionsInfo(context);
    }

    @Override
    public void getFavouriteData(Context context) {
        mIntractor.getFavouritesVersionsInfo(context);
    }

    @Override
    public void getLessDestribution(Context context) {
        mIntractor.getLessDestribution(context);
    }

    @Override
    public void clickItem(String date) {
        mGetDataView.showToast(date);
    }


    @Override
    public void onSuccess(List<VersionInfo> forecasts) {
        mGetDataView.onGetDataSuccess(forecasts);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }

    @Override
    public void onLoading() {
        mGetDataView.showLoading();
    }
}
