package com.opannapo.googlebooks.usecase.books;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opannapo.googlebooks.core.repository.ApiClient;
import com.opannapo.googlebooks.core.repository.EndPointInterface;
import com.opannapo.googlebooks.core.usecase.BaseUseCase;
import com.opannapo.googlebooks.domain.BookEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by napouser on 23,February,2020
 */
public class BooksUsecase extends BaseUseCase<IBooksUsecase.View> implements IBooksUsecase.Action {
    EndPointInterface endPoint;
    Gson gson = new Gson();

    public BooksUsecase(IBooksUsecase.View view) {
        super(view);
        endPoint = ApiClient.getClient().create(EndPointInterface.class);
    }

    @Override
    public void doLoadMore(Context context, String q, int start, int end) {

    }

    @Override
    public void doGetNext(String key, int start, int end) {
        view.onProgressSeaching();
        endPoint.getSearch(key, start, end).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Log.d("RES", String.valueOf(response));
                //Log.d("RES CODE", String.valueOf(response.code()));
                try {
                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body() != null) {
                            JsonObject res = response.body().getAsJsonObject();
                            int totalItem = res.get("totalItems").getAsInt();
                            List<BookEntity> books = new ArrayList<>();
                            res.getAsJsonArray("items").forEach(jsonElement -> {
                                BookEntity book = gson.fromJson(jsonElement, BookEntity.class);
                                //System.out.println("BookEntity : " + book.toString());
                                books.add(book);
                            });
                            view.onSearchResult(books, totalItem);
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
