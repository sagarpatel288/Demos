package com.example.android.stackexchange.stacklistitem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.stackexchange.R;
import com.example.android.stackexchange.api.ApiInterface;
import com.example.android.stackexchange.appconstants.AppConstants;
import com.example.android.stackexchange.appconstants.JsonKeys;
import com.example.android.stackexchange.baseui.BaseActivity;
import com.example.android.stackexchange.databinding.ActivityStackListBinding;
import com.example.android.stackexchange.model.StackListItem;
import com.example.android.stackexchange.model.StackResponse;
import com.library.android.common.listeners.EndlessRecyclerViewScrollListener;
import com.library.android.common.rest.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class StackListActivity extends BaseActivity {

    private ActivityStackListBinding mBinding;
    private List<StackListItem> mList = new ArrayList<>();
    private int mPageNumber = 1;
    private boolean hasLoadMore;
    private StackListItemAdapter mAdapter;
    private String query = "";
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stack_list;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mBinding = ActivityStackListBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {

    }

    @Override
    public void handleViews() {
        setToolbar();
        setRecyclerView();
    }

    private void setToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.st_placeholder));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            SearchView searchView = (SearchView) item.getActionView();
            if (searchView != null) {
                setSearchView(searchView);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSearchView(SearchView searchView) {
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    onQueryChange(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    onQueryChange(newText);
                    return false;
                }
            });
        }
    }

    private void onQueryChange(String query) {

    }

    private void setRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mBinding.viewRv.setLayoutManager(mLayoutManager);
        mAdapter = new StackListItemAdapter(this, mList, AppConstants.LIMIT_PAGINATION, null);
        mBinding.viewRv.setAdapter(mAdapter);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (hasLoadMore){
                    callApi(query, true);
                }
            }
        };
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }

    @Override
    public void otherStuff() {
        callApi("", false);
    }

    private void callApi(String query, boolean isLoadMore) {
        callApi(ServiceGenerator.createService(ApiInterface.class)
                .callApiSearchByTag(mPageNumber, AppConstants.LIMIT_PAGINATION, JsonKeys.QUERY_ORDER_DESC, JsonKeys.QUERY_SORT_ACTIVITY, query, JsonKeys.QUERY_SITE_STACKOVERFLOW), isLoadMore);

    }

    private void callApi(Call<StackResponse> call, boolean isLoadMore){
        if (!isLoadMore){
            call.cancel();
            mPageNumber = 1;
        }
        call.enqueue(new Callback<StackResponse>() {
            @Override
            public void onResponse(Call<StackResponse> call, Response<StackResponse> response) {
                StackResponse stackResponse = response.body();
                onGetStackResponse(stackResponse);
            }

            @Override
            public void onFailure(Call<StackResponse> call, Throwable t) {
                makeText(StackListActivity.this, "" + t.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

    private void onGetStackResponse(StackResponse stackResponse) {
        if (stackResponse != null) {
            hasLoadMore = stackResponse.isHasMore();
        } else {
            hasLoadMore = false;
        }

    }

    @Override
    public void saveState(Bundle saveInstanceState) {

    }
}
