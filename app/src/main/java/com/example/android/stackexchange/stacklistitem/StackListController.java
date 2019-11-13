package com.example.android.stackexchange.stacklistitem;

import android.content.Context;
import android.content.Intent;

import com.library.android.common.basecontroller.BaseController;
import com.library.android.common.listeners.Callbacks;

import java.util.List;

public class StackListController extends BaseController {

    public StackListController(Context mContext, String mApi, Intent mIntent, Callbacks.ApiResponseDetail mApiResponseDetail) {
        super(mContext, mApi, mIntent, mApiResponseDetail);
    }

    @Override
    public void onSuccess(Object object, int pageNumber, Intent intent) {

    }

    @Override
    public void onCancel(int pageNumber, Intent intent) {

    }

    @Override
    public void onFail(int pageNumber, Intent intent) {

    }

    @Override
    public void onNoDataAtAll(int pageNumber, Intent intent) {

    }

    @Override
    public void onNoMoreData(int pageNumber, Intent intent) {

    }

    @Override
    public void onEmptySearchResult(int pageNumber, Intent intent) {

    }

    @Override
    public void onGetData(Object object, List list, int pageNumber, Intent intent) {

    }
}
