package com.example.android.stackexchange.baseui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.library.android.common.R;
import com.library.android.common.databinding.BaseViewStubLayoutBinding;
import com.library.android.common.listeners.Callbacks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity extends AppCompatActivity implements Callbacks.ChildHostCommunication {

    BaseViewStubLayoutBinding mBinding;
    private boolean hasStubInflated;

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof BaseFragment) {
            BaseFragment baseFragment = (BaseFragment) fragment;
            baseFragment.setChildHostCommunication(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region https://developer.android.com/topic/libraries/data-binding/expressions
        //For Activity
        //        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //For Fragments, ListViews, Adapters
        //ListItemBinding binding = ListItemBinding.inflate(layoutInflater, viewGroup, false);
        //// or
        //ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false);
        //endregion

        //Normal method to inflate the layout
        mBinding = DataBindingUtil.setContentView(this, R.layout.base_view_stub_layout);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        if (mBinding != null && mBinding.viewStub != null && mBinding.viewStub.getViewStub() != null) {
            mBinding.viewStub.getViewStub().setLayoutResource(getLayoutId());
            if (!hasStubInflated) {
                View inflatedView = mBinding.viewStub.getViewStub().inflate();
                //Abstract method
                onViewStubInflated(inflatedView, savedInstanceState);
                //Class method
                onViewStubInflated(savedInstanceState);
            }
        }
    }

    public abstract int getLayoutId();

    //Bind the inflatedView for data binding
    public abstract void onViewStubInflated(View inflatedView, Bundle savedInstanceState);

    private void onViewStubInflated(Bundle savedInstanceState) {
        hasStubInflated = true;
        hideProgressbar();
        initControllers();
        handleViews();
        setListeners();
        restoreValues(savedInstanceState);
        otherStuff();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    public abstract void initControllers();

    public abstract void handleViews();

    public abstract void setListeners();

    public abstract void restoreValues(Bundle savedInstanceState);

    public abstract void otherStuff();

    public abstract void saveState(Bundle saveInstanceState);

    public void hideProgressbar() {
        if (mBinding.progressbar != null) {
            mBinding.progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hasStubInflated = false;
    }

    public ViewModel getViewModel(@NonNull Class viewModelClass) {
        if (viewModelClass != null) {
            return ViewModelProviders.of(this).get(viewModelClass);
        } else {
            return null;
        }
    }

    @Override
    public void onChildHostCommunication(View view, Intent intent) {
        // com: 11/10/2019 Get response from fragment
    }
}
