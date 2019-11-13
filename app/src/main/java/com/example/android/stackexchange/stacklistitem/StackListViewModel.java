package com.example.android.stackexchange.stacklistitem;

import android.app.Application;

import com.example.android.stackexchange.model.StackListItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class StackListViewModel extends AndroidViewModel {

    private MutableLiveData<String> query = new MutableLiveData<>();
    private List<StackListItem> stackListItems = new ArrayList<>();
    private MutableLiveData<List<StackListItem>> liveStackListItem = new MutableLiveData<>();

    public void setQuery(MutableLiveData<String> query) {
        this.query = query;
    }

    public StackListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<StackListItem>> getLiveStackListItem(){
        if (liveStackListItem == null){
            liveStackListItem = new MutableLiveData<>();
        }
        return liveStackListItem;
    }


}
