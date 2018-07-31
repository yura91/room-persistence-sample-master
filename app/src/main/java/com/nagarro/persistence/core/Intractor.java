package com.nagarro.persistence.core;

import android.content.Context;

import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.entity.VersionInfo;
import com.nagarro.persistence.utils.DatabaseInitializer;

import java.util.ArrayList;
import java.util.List;

public class Intractor implements GetDataContract.Interactor {
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    List<VersionInfo> versionInfos = new ArrayList<>();

    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
    }

    @Override
    public void getAllVersionsInfo(Context context) {
        DatabaseInitializer.getAll(AppDatabase.getAppDatabase(context), this);
    }

    @Override
    public void getFavouritesVersionsInfo(Context context) {
        DatabaseInitializer.getFavourites(AppDatabase.getAppDatabase(context), this);
    }

    @Override
    public void getLessDestribution(Context context) {
        DatabaseInitializer.getLessDistribution(AppDatabase.getAppDatabase(context), this);
    }

    public void onUpdate(List<VersionInfo> versionInfos) {
        mOnGetDatalistener.onSuccess(versionInfos);
    }
}
