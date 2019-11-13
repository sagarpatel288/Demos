package com.example.android.stackexchange.stacklistitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.stackexchange.model.StackListItem;
import com.library.android.common.adapters.BaseAdapter;
import com.library.android.common.baseconstants.BaseConstants;
import com.library.android.common.baseviewholders.ProgressLoadingHolder;
import com.library.android.common.databinding.ItemTitleSubTitleBinding;
import com.library.android.common.listeners.Callbacks;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StackListItemAdapter extends BaseAdapter {

    public StackListItemAdapter(Context mContext, List mFilteredList, int mPaginationLimit, Callbacks.RvAdapterCallback mRvAdapterCallback) {
        super(mContext, mFilteredList, mPaginationLimit, mRvAdapterCallback);
    }

    @Override
    public List getFilteredList(String query) {
        return getList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BaseConstants.ItemViewType.PROGRESS_BAR_CIRCULAR_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(com.library.android.common.R.layout.layout_progress_bar, parent, false);
            return new ProgressLoadingHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(com.library.android.common.R.layout.item_title_sub_title, parent, false);
            ItemTitleSubTitleBinding mBinding = ItemTitleSubTitleBinding.bind(view);
            return new ItemViewHolder(view, mBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isValidItem(holder, position) && holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            StackListItem stackListItem = (StackListItem) getItem(position);
            if (isValidItem(itemViewHolder, position, stackListItem)) {
                setData(itemViewHolder, stackListItem);
            }
        }
    }

    private void setData(ItemViewHolder itemViewHolder, StackListItem stackListItem) {
        itemViewHolder.mBinding.vTvTitle.setText(stackListItem.getTitle());
        itemViewHolder.mBinding.vTvSubTitle.setText(String.valueOf(stackListItem.getAnswerCount()));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemTitleSubTitleBinding mBinding;

        ItemViewHolder(@NonNull View itemView, ItemTitleSubTitleBinding binding) {
            super(itemView);
            this.mBinding = binding;
        }
    }
}
