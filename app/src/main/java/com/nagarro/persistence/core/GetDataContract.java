package com.nagarro.persistence.core;

import android.content.Context;

import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;

public interface GetDataContract {
    interface View {
        void onGetDataSuccess(List<VersionInfo> forecast);

        void onGetDataFailure(String message);

        void showToast(String mesDate);

        void showLoading();
    }

    interface Presenter {
        void getAllData(Context context);

        void getFavouriteData(Context context);

        void getLessDestribution(Context context);

        void clickItem(String date);
    }

    interface Interactor {
        void getAllVersionsInfo(Context context);

        void getFavouritesVersionsInfo(Context context);

        void getLessDestribution(Context context);

        void onUpdate(List<VersionInfo> versionInfos);
    }

    interface onGetDataListener {
        void onSuccess(List<VersionInfo> forecast);

        void onFailure(String message);

        void onLoading();
    }
}
