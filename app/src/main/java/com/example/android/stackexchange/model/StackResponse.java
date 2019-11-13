package com.example.android.stackexchange.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.library.android.common.pojos.BasePojo;

import java.util.ArrayList;
import java.util.List;

public class StackResponse extends BasePojo implements Parcelable {

    @SerializedName("stackListItems")
    @Expose
    private List<StackListItem> stackListItems = null;
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
    public StackResponse() {
    }

    /**
     * Constructor
     */
    public StackResponse(List<StackListItem> stackListItems, boolean hasMore, long quotaMax, long quotaRemaining, String errorId, String errorMessage, String errorName) {
        super();
        this.stackListItems = stackListItems;
        this.hasMore = hasMore;
        this.quotaMax = quotaMax;
        this.quotaRemaining = quotaRemaining;
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorName = errorName;
    }

    public List<StackListItem> getStackListItems() {
        return stackListItems;
    }

    public void setStackListItems(List<StackListItem> stackListItems) {
        this.stackListItems = stackListItems;
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
        dest.writeList(this.stackListItems);
        dest.writeByte(this.hasMore ? (byte) 1 : (byte) 0);
        dest.writeLong(this.quotaMax);
        dest.writeLong(this.quotaRemaining);
        dest.writeString(this.errorId);
        dest.writeString(this.errorMessage);
        dest.writeString(this.errorName);
    }

    protected StackResponse(Parcel in) {
        this.stackListItems = new ArrayList<StackListItem>();
        in.readList(this.stackListItems, StackListItem.class.getClassLoader());
        this.hasMore = in.readByte() != 0;
        this.quotaMax = in.readLong();
        this.quotaRemaining = in.readLong();
        this.errorId = in.readString();
        this.errorMessage = in.readString();
        this.errorName = in.readString();
    }

    public static final Parcelable.Creator<StackResponse> CREATOR = new Parcelable.Creator<StackResponse>() {
        @Override
        public StackResponse createFromParcel(Parcel source) {
            return new StackResponse(source);
        }

        @Override
        public StackResponse[] newArray(int size) {
            return new StackResponse[size];
        }
    };
}