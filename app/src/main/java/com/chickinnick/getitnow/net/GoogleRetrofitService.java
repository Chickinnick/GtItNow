package com.chickinnick.getitnow.net;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class GoogleRetrofitService {

    private static final String LOG_TAG = GoogleRetrofitService.class.getSimpleName();
    private static final String BASE_URL = "https://www.google.com/";
    private static GoogleRetrofitService ourInstance = new GoogleRetrofitService();
    OkHttpClient okClient = new OkHttpClient();
    public GoogleApi googleApi;

    public static GoogleRetrofitService getInstance() {
        return ourInstance;
    }

    private GoogleRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        googleApi = retrofit.create(GoogleApi.class);
    }

    public interface GoogleApi {
        @GET("search/")
        Call<String> getPage(@Query("q") String query);
    }

    public void makeCall(String query) {
        String q = query.replace(' ', '+');
        Call<String> stringCall = GoogleRetrofitService.getInstance().googleApi.getPage(q);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(LOG_TAG, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
