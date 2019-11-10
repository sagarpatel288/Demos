package com.example.android.stackexchange.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.library.android.common.pojos.BasePojo;

import java.util.ArrayList;
import java.util.List;

public class StackSearchListItem extends BasePojo implements Parcelable {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("has_more")
    @Expose
    private boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private long quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private long quotaRemaining;

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    @SerializedName("error_id")
    @Expose
    private String errorId;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("error_name")
    @Expose
    private String errorName;

    /**
     * No args constructor for use in serialization
     */
    public StackSearchListItem() {
    }

    /**
     * Constructor
     */
    public StackSearchListItem(List<Item> items, boolean hasMore, long quotaMax, long quotaRemaining, String errorId, String errorMessage, String errorName) {
        super();
        this.items = items;
        this.hasMore = hasMore;
        this.quotaMax = quotaMax;
        this.quotaRemaining = quotaRemaining;
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorName = errorName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public long getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(long quotaMax) {
        this.quotaMax = quotaMax;
    }

    public long getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(long quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.items);
        dest.writeByte(this.hasMore ? (byte) 1 : (byte) 0);
        dest.writeLong(this.quotaMax);
        dest.writeLong(this.quotaRemaining);
        dest.writeString(this.errorId);
        dest.writeString(this.errorMessage);
        dest.writeString(this.errorName);
    }

    protected StackSearchListItem(Parcel in) {
        this.items = new ArrayList<Item>();
        in.readList(this.items, Item.class.getClassLoader());
        this.hasMore = in.readByte() != 0;
        this.quotaMax = in.readLong();
        this.quotaRemaining = in.readLong();
        this.errorId = in.readString();
        this.errorMessage = in.readString();
        this.errorName = in.readString();
    }

    public static final Parcelable.Creator<StackSearchListItem> CREATOR = new Parcelable.Creator<StackSearchListItem>() {
        @Override
        public StackSearchListItem createFromParcel(Parcel source) {
            return new StackSearchListItem(source);
        }

        @Override
        public StackSearchListItem[] newArray(int size) {
            return new StackSearchListItem[size];
        }
    };
}