package com.example.android.stackexchange.api;

import com.example.android.stackexchange.model.StackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /*https://api.stackexchange.com/2.2/search?page=1&pagesize=10&order=desc&sort=activity&tagged=android&site=stackoverflow*/

    @GET(Api.API_SEARCH)
    Call<StackResponse> callApiSearchByTag(@Query("page") int page, @Query("paegsize") int paginationLimit,
                                           @Query("order") String desc, @Query("sort") String activity,
                                           @Query("tagged") String searchQueryByTag, @Query("site") String stackoverflow);

}
