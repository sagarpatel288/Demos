package com.example.android.stackexchange.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.library.android.common.pojos.BasePojo;

public class Owner extends BasePojo {

    @SerializedName("reputation")
    @Expose
    private long reputation;
    @SerializedName("user_id")
    @Expose
    private long userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("accept_rate")
    @Expose
    private long acceptRate;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("link")
    @Expose
    private String link;

    /**
     * No args constructor for use in serialization
     */
    public Owner() {
    }

    /**
     * @param acceptRate
     * @param displayName
     * @param link
     * @param reputation
     * @param userType
     * @param profileImage
     * @param userId
     */
    public Owner(long reputation, long userId, String userType, long acceptRate, String profileImage, String displayName, String link) {
        super();
        this.reputation = reputation;
        this.userId = userId;
        this.userType = userType;
        this.acceptRate = acceptRate;
        this.profileImage = profileImage;
        this.displayName = displayName;
        this.link = link;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(long acceptRate) {
        this.acceptRate = acceptRate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.reputation);
        dest.writeLong(this.userId);
        dest.writeString(this.userType);
        dest.writeLong(this.acceptRate);
        dest.writeString(this.profileImage);
        dest.writeString(this.displayName);
        dest.writeString(this.link);
    }

    protected Owner(Parcel in) {
        super(in);
        this.reputation = in.readLong();
        this.userId = in.readLong();
        this.userType = in.readString();
        this.acceptRate = in.readLong();
        this.profileImage = in.readString();
        this.displayName = in.readString();
        this.link = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel source) {
            return new Owner(source);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}