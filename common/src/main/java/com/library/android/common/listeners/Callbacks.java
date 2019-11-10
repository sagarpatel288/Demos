package com.library.android.common.listeners;


import android.content.Intent;
import android.view.View;

import java.util.List;

public abstract class Callbacks {

    public interface OnFragmentLoad {
        void onFragmentVisible();

        void onFragmentHide();
    }

    public interface SimpleCallBack {
        void eventCallBack(Intent intent);
    }

    public interface ChildHostCommunication {
        void onChildHostCommunication(View view, Intent intent);
    }

    public interface EventCallBack {
        void onEventCallBack(View view, int position, Object object, List list, Intent intent);
    }

    public interface NetworkConnectionListener {
        void onConnectionChanged(boolean isConnected);
    }

    public interface AdapterCallback {
        void onGetAdapterCallback(View view, int position, Object parcel, List list, Intent intent);
    }
}
