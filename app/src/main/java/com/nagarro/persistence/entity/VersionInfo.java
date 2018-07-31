package com.nagarro.persistence.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity()
public class VersionInfo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String version_number;

    private String version_name;

    private String released_date;

    private String api;

    private Double distribution;

    private boolean isFavourite;

    private String description;

    public VersionInfo() {
    }

    protected VersionInfo(Parcel in) {
        uid = in.readInt();
        version_number = in.readString();
        version_name = in.readString();
        released_date = in.readString();
        api = in.readString();
        distribution = in.readDouble();
        isFavourite = in.readByte() != 0;
        description = in.readString();
    }

    public static final Creator<VersionInfo> CREATOR = new Creator<VersionInfo>() {
        @Override
        public VersionInfo createFromParcel(Parcel in) {
            return new VersionInfo(in);
        }

        @Override
        public VersionInfo[] newArray(int size) {
            return new VersionInfo[size];
        }
    };

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getReleased_date() {
        return released_date;
    }

    public void setReleased_date(String released_date) {
        this.released_date = released_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(version_number);
        dest.writeString(version_name);
        dest.writeString(released_date);
        dest.writeString(api);
        dest.writeDouble(distribution);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
        dest.writeString(description);
    }
}
