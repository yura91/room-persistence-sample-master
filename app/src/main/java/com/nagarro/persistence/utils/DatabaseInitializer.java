package com.nagarro.persistence.utils;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nagarro.persistence.core.GetDataContract;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void getAll(@NonNull final AppDatabase db, GetDataContract.Interactor interactor) {
        AllDbAsync task = new AllDbAsync(db, interactor);
        task.execute();
    }

    public static void getFavourites(@NonNull final AppDatabase db, GetDataContract.Interactor interactor) {
        FavouriteDbAsync task = new FavouriteDbAsync(db, interactor);
        task.execute();
    }

    public static void getLessDistribution(@NonNull final AppDatabase db, GetDataContract.Interactor interactor) {
        LessDbAsync task = new LessDbAsync(db, interactor);
        task.execute();
    }

    private static VersionInfo addUser(final AppDatabase db, VersionInfo versionInfo) {
        db.userDao().insertAll(versionInfo);
        return versionInfo;
    }

    private static List<VersionInfo> getAllData(AppDatabase db) {
        if (db.userDao().countUsers() == 0) {
            VersionInfo versionInfo1 = new VersionInfo();
            versionInfo1.setVersion_number("8.1");
            versionInfo1.setVersion_name("Oreo");
            versionInfo1.setReleased_date("December 5, 2017");
            versionInfo1.setApi("27");
            versionInfo1.setDistribution(0.8);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo1);

            VersionInfo versionInfo2 = new VersionInfo();
            versionInfo2.setVersion_number("8.0");
            versionInfo2.setVersion_name("Oreo");
            versionInfo2.setReleased_date("August 21, 2017");
            versionInfo2.setApi("26");
            versionInfo2.setDistribution(4.9);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo2);

            VersionInfo versionInfo3 = new VersionInfo();
            versionInfo3.setVersion_number("7.1");
            versionInfo3.setVersion_name("Nougat");
            versionInfo3.setReleased_date("October 4, 2016");
            versionInfo3.setApi("25");
            versionInfo3.setDistribution(8.2);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo3);

            VersionInfo versionInfo4 = new VersionInfo();
            versionInfo4.setVersion_number("7.0");
            versionInfo4.setVersion_name("Nougat");
            versionInfo4.setReleased_date("August 22, 2016");
            versionInfo4.setApi("24 ");
            versionInfo4.setDistribution(22.9);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo4);

            VersionInfo versionInfo5 = new VersionInfo();
            versionInfo5.setVersion_number("6.0 ");
            versionInfo5.setVersion_name("Marshmallow ");
            versionInfo5.setReleased_date("October 5, 2015 ");
            versionInfo5.setApi("23 ");
            versionInfo5.setDistribution(25.5);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo5);

            VersionInfo versionInfo6 = new VersionInfo();
            versionInfo6.setVersion_number("5.1 ");
            versionInfo6.setVersion_name("Lollipop  ");
            versionInfo6.setReleased_date("March 9, 2015 ");
            versionInfo6.setApi("22 ");
            versionInfo6.setDistribution(17.6);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo6);

            VersionInfo versionInfo7 = new VersionInfo();
            versionInfo7.setVersion_number("5.0 ");
            versionInfo7.setVersion_name("Lollipop ");
            versionInfo7.setReleased_date("November 3, 2014 ");
            versionInfo7.setApi("21 ");
            versionInfo7.setDistribution(4.8);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo7);

            VersionInfo versionInfo8 = new VersionInfo();
            versionInfo8.setVersion_number("4.4 ");
            versionInfo8.setVersion_name("KitKat ");
            versionInfo8.setReleased_date("October 31, 2013 ");
            versionInfo8.setApi("19 ");
            versionInfo8.setDistribution(10.3);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo8);

            VersionInfo versionInfo9 = new VersionInfo();
            versionInfo9.setVersion_number("4.3 ");
            versionInfo9.setVersion_name("Jelly Bean ");
            versionInfo9.setReleased_date("July 24, 2013 ");
            versionInfo9.setApi("18 ");
            versionInfo9.setDistribution(0.6);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo9);

            VersionInfo versionInfo10 = new VersionInfo();
            versionInfo10.setVersion_number("4.2 ");
            versionInfo10.setVersion_name("Jelly Bean ");
            versionInfo10.setReleased_date("November 13, 2012 ");
            versionInfo10.setApi("17 ");
            versionInfo10.setDistribution(2.2);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo10);

            VersionInfo versionInfo11 = new VersionInfo();
            versionInfo11.setVersion_number("4.1 ");
            versionInfo11.setVersion_name("Jelly Bean ");
            versionInfo11.setReleased_date("July 9, 2012 ");
            versionInfo11.setApi("16 ");
            versionInfo11.setDistribution(1.5);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo11);

            VersionInfo versionInfo12 = new VersionInfo();
            versionInfo12.setVersion_number("4.0 ");
            versionInfo12.setVersion_name("Ice Cream Sandwich ");
            versionInfo12.setReleased_date("October 19, 2011 ");
            versionInfo12.setApi("15 ");
            versionInfo12.setDistribution(0.4);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo12);

            VersionInfo versionInfo13 = new VersionInfo();
            versionInfo13.setVersion_number("2.3 ");
            versionInfo13.setVersion_name("Gingerbread ");
            versionInfo13.setReleased_date("February 9, 2011 ");
            versionInfo13.setApi("10 ");
            versionInfo13.setDistribution(0.3);
            versionInfo1.setFavourite(false);
            addUser(db, versionInfo13);
        }
        List<VersionInfo> versionInfoList = db.userDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + versionInfoList.size());
        return versionInfoList;
    }

    private static List<VersionInfo> getFavouriteData(AppDatabase db) {
        List<VersionInfo> versionInfoList = db.userDao().findByFavourite(true);

        return versionInfoList;
    }

    private static List<VersionInfo> getLessData(AppDatabase db) {
        List<VersionInfo> versionInfoList = db.userDao().findByDistribution(3);

        return versionInfoList;
    }

    private static class AllDbAsync extends AsyncTask<Void, Void, List<VersionInfo>> {

        private final AppDatabase mDb;
        private GetDataContract.Interactor interactor;

        AllDbAsync(AppDatabase db, GetDataContract.Interactor interactor) {
            mDb = db;
            this.interactor = interactor;
        }

        @Override
        protected List<VersionInfo> doInBackground(final Void... params) {
            List<VersionInfo> versionInfos = getAllData(mDb);
            return versionInfos;
        }

        @Override
        protected void onPostExecute(List<VersionInfo> versionInfoList) {
            interactor.onUpdate(versionInfoList);
        }
    }

    private static class FavouriteDbAsync extends AsyncTask<Void, Void, List<VersionInfo>> {


        private final AppDatabase mDb;
        private GetDataContract.Interactor interactor;

        FavouriteDbAsync(AppDatabase db, GetDataContract.Interactor interactor) {
            mDb = db;
            this.interactor = interactor;
        }

        @Override
        protected List<VersionInfo> doInBackground(final Void... params) {
            List<VersionInfo> versionInfos = getFavouriteData(mDb);
            return versionInfos;
        }

        @Override
        protected void onPostExecute(List<VersionInfo> versionInfoList) {
            interactor.onUpdate(versionInfoList);
        }
    }

    private static class LessDbAsync extends AsyncTask<Void, Void, List<VersionInfo>> {


        private final AppDatabase mDb;
        private GetDataContract.Interactor interactor;

        LessDbAsync(AppDatabase db, GetDataContract.Interactor interactor) {
            mDb = db;
            this.interactor = interactor;
        }

        @Override
        protected List<VersionInfo> doInBackground(final Void... params) {
            List<VersionInfo> versionInfos = getLessData(mDb);
            return versionInfos;
        }

        @Override
        protected void onPostExecute(List<VersionInfo> versionInfoList) {
            interactor.onUpdate(versionInfoList);
        }
    }
}
