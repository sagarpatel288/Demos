package com.example.android.stackexchange.model;


import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.library.android.common.pojos.BasePojo;

import java.util.List;


public class Item extends BasePojo {

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("is_answered")
    @Expose
    private boolean isAnswered;
    @SerializedName("view_count")
    @Expose
    private long viewCount;
    @SerializedName("answer_count")
    @Expose
    private long answerCount;
    @SerializedName("score")
    @Expose
    private long score;
    @SerializedName("last_activity_date")
    @Expose
    private long lastActivityDate;
    @SerializedName("creation_date")
    @Expose
    private long creationDate;
    @SerializedName("question_id")
    @Expose
    private long questionId;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("last_edit_date")
    @Expose
    private long lastEditDate;
    @SerializedName("accepted_answer_id")
    @Expose
    private long acceptedAnswerId;

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param owner
     * @param questionId
     * @param lastEditDate
     * @param link
     * @param creationDate
     * @param title
     * @param tags
     * @param score
     * @param answerCount
     * @param lastActivityDate
     * @param isAnswered
     * @param acceptedAnswerId
     * @param viewCount
     */
    public Item(List<String> tags, Owner owner, boolean isAnswered, long viewCount, long answerCount, long score, long lastActivityDate, long creationDate, long questionId, String link, String title, long lastEditDate, long acceptedAnswerId) {
        super();
        this.tags = tags;
        this.owner = owner;
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.questionId = questionId;
        this.link = link;
        this.title = title;
        this.lastEditDate = lastEditDate;
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(long answerCount) {
        this.answerCount = answerCount;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(long lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public long getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(long acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringList(this.tags);
        dest.writeParcelable(this.owner, flags);
        dest.writeByte(this.isAnswered ? (byte) 1 : (byte) 0);
        dest.writeLong(this.viewCount);
        dest.writeLong(this.answerCount);
        dest.writeLong(this.score);
        dest.writeLong(this.lastActivityDate);
        dest.writeLong(this.creationDate);
        dest.writeLong(this.questionId);
        dest.writeString(this.link);
        dest.writeString(this.title);
        dest.writeLong(this.lastEditDate);
        dest.writeLong(this.acceptedAnswerId);
    }

    protected Item(Parcel in) {
        super(in);
        this.tags = in.createStringArrayList();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
        this.isAnswered = in.readByte() != 0;
        this.viewCount = in.readLong();
        this.answerCount = in.readLong();
        this.score = in.readLong();
        this.lastActivityDate = in.readLong();
        this.creationDate = in.readLong();
        this.questionId = in.readLong();
        this.link = in.readString();
        this.title = in.readString();
        this.lastEditDate = in.readLong();
        this.acceptedAnswerId = in.readLong();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

