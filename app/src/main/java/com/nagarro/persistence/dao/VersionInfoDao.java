package com.nagarro.persistence.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nagarro.persistence.entity.VersionInfo;

import java.util.List;

@Dao
public interface VersionInfoDao {

    @Query("SELECT * FROM VersionInfo")
    List<VersionInfo> getAll();

    @Query("SELECT * FROM VersionInfo where version_number LIKE  :version_number AND version_name LIKE :version_name")
    VersionInfo findByName(String version_number, String version_name);

    @Query("SELECT * FROM VersionInfo WHERE uid=:uid")
    VersionInfo findById(int uid);

    @Query("SELECT * FROM VersionInfo WHERE isFavourite =:isFavourite")
    List<VersionInfo> findByFavourite(boolean isFavourite);

    @Query("SELECT * FROM VersionInfo WHERE distribution <:distribution")
    List<VersionInfo> findByDistribution(int distribution);


    @Query("SELECT COUNT(*) from VersionInfo")
    int countUsers();

    @Insert
    void insertAll(VersionInfo versionInfos);

    @Delete
    void delete(VersionInfo versionInfo);

    @Update
    void updateVersionInfo(VersionInfo versionInfo);
}
