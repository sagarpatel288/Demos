package com.example.android.stackexchange.baseui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.android.common.R;
import com.library.android.common.databinding.BaseViewStubLayoutBinding;
import com.library.android.common.listeners.Callbacks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements Callbacks.OnFragmentLoad, Callbacks.ChildHostCommunication {

    private BaseViewStubLayoutBinding mBinding;
    private boolean hasViewStubInflated;
    private Bundle mSavedInstanceState;

    public Callbacks.ChildHostCommunication getChildHostCommunication() {
        return childHostCommunication;
    }

    public void setChildHostCommunication(Callbacks.ChildHostCommunication childHostCommunication) {
        this.childHostCommunication = childHostCommunication;
    }

    private Callbacks.ChildHostCommunication childHostCommunication;

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        if (childFragment instanceof BaseFragment){
            BaseFragment baseFragment = (BaseFragment) childFragment;
            baseFragment.setChildHostCommunication(this);
        }
    }

    @Override
    public void onFragmentVisible() {

    }

    @Override
    public void onFragmentHide() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mBinding != null && mBinding.viewStub != null && !hasViewStubInflated) {
            inflateViewStub();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO: 11/10/2019 Save your variables here
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.base_view_stub_layout, container, false);
        View view = mBinding.getRoot();
        //region https://developer.android.com/topic/libraries/data-binding/expressions
        //For Activity
        //        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //For Fragments, ListViews, Adapters
        //ListItemBinding binding = ListItemBinding.inflate(layoutInflater, viewGroup, false);
        //// or
        //ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false);
        //endregion
        if (mBinding != null && mBinding.viewStub != null && mBinding.viewStub.getViewStub() != null) {
            mBinding.viewStub.getViewStub().setLayoutResource(getLayoutId());
        }
        mSavedInstanceState = savedInstanceState;

        if (getUserVisibleHint() && !hasViewStubInflated) {
            inflateViewStub();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: 11/10/2019 Restore your variables for savedInstanceState here

    }

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hasViewStubInflated = false;
    }

    public void inflateViewStub() {
        if (!hasViewStubInflated && mBinding != null && mBinding.viewStub != null && mBinding.viewStub.getViewStub() != null) {
            View inflatedView = mBinding.viewStub.getViewStub().inflate();
            onViewStubInflated(inflatedView, mSavedInstanceState);
            onViewStubInflated(mSavedInstanceState);
        }
    }

    //Bind the inflatedView for data binding
    public abstract void onViewStubInflated(View inflatedView, Bundle savedInstanceState);

    private void onViewStubInflated(Bundle mSavedInstanceState) {
        hasViewStubInflated = true;
        hideProgressbar();
        initControllers();
        handleViews();
        setListeners();
        restoreValues(mSavedInstanceState);
    }

    public abstract void initControllers();

    public abstract void handleViews();

    public abstract void setListeners();

    public abstract void restoreValues(Bundle savedInstanceState);

    public void hideProgressbar() {
        if (mBinding.progressbar != null) {
            mBinding.progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChildHostCommunication(View view, Intent intent) {
        // com: 11/10/2019 Get response of childFragment here in this parent fragment
    }
}
