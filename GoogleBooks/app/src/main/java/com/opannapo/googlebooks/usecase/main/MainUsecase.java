package com.opannapo.googlebooks.usecase.main;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.opannapo.googlebooks.core.repository.ApiClient;
import com.opannapo.googlebooks.core.repository.EndPointInterface;
import com.opannapo.googlebooks.core.usecase.BaseUseCase;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by napouser on 23,February,2020
 */
public class MainUsecase extends BaseUseCase<IMainUsecase.View> implements IMainUsecase.Action {
    EndPointInterface endPoint;

    public MainUsecase(IMainUsecase.View view) {
        super(view);
        endPoint = ApiClient.getClient().create(EndPointInterface.class);
    }

    @Override
    public void doSearch(Context context, String q) {
        view.onProgressSeaching();
        endPoint.getSearch(q, 0, 20).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("RES", String.valueOf(response));
                Log.d("RES CODE", String.valueOf(response.code()));
                try {
                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body() != null) {
                            JsonObject res = response.body().getAsJsonObject();
                            int totalItem = res.get("totalItems").getAsInt();
                            view.onSearchResult(res, totalItem);
                        }
                    } else {
                        view.onSearchError(response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    view.onSearchError(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                view.onSearchError(t.getMessage());
            }
        });
    }
}

