package com.example.a301.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 301 on 2017-09-19.
 */

public interface RetrofitService {
    @GET("")
    Call<GsonVO> loadAnswer();
}
