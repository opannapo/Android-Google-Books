package com.opannapo.googlebooks.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opannapo.googlebooks.R;
import com.opannapo.googlebooks.core.view.BaseActivity;
import com.opannapo.googlebooks.domain.BookEntity;
import com.opannapo.googlebooks.usecase.books.BooksUsecase;
import com.opannapo.googlebooks.usecase.books.IBooksUsecase;
import com.opannapo.googlebooks.view.adapter.BooksSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by napouser on 23,February,2020
 */
public class BooksActivity extends BaseActivity<BooksUsecase> implements IBooksUsecase.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarRoot)
    AppBarLayout toolbarRoot;
    @BindView(R.id.rclvSearchResult)
    RecyclerView rclvSearchResult;
    @BindView(R.id.imgNotFound)
    ImageView imgNotFound;
    @BindView(R.id.linNotFound)
    LinearLayout linNotFound;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.prbLoading)
    ProgressBar prbLoading;

    private BooksSearchAdapter adapter;
    private boolean isLoadingMore = false;
    private String key;
    private int totalItems;
    private String res;
    private List<BookEntity> books = new ArrayList<>();
    private int currentPage;
    Gson gson = new Gson();


    @Override
    public BooksUsecase initUseCase() {
        return new BooksUsecase(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_books;
    }

    @Override
    public void onCreated(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        prbLoading.setVisibility(View.GONE);
        key = "tes";

        if (extras != null) {
            key = extras.getString("key");
            totalItems = extras.getInt("totalItems");
            res = extras.getString("res");
            currentPage = 1;
            txtTitle.setText("Result for " + key);

            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            setupAdapter(jsonObject);

            if (jsonObject.has("items")) {
                jsonObject.getAsJsonArray("items").forEach(jsonElement -> {
                    BookEntity book = gson.fromJson(jsonElement, BookEntity.class);
                    System.out.println("BookEntity : " + book.toString());
                    books.add(book);
                });
                adapter.notifyItemInserted(books.size());
            }

            linNotFound.setVisibility(totalItems == books.size() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onProgressSeaching() {
        isLoadingMore = true;
        prbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchError(String errMsg) {
        prbLoading.setVisibility(View.GONE);
        isLoadingMore = false;
    }

    @Override
    public void onSearchResult(List<BookEntity> books, int totalItems) {
        prbLoading.setVisibility(View.GONE);
        currentPage++;
        isLoadingMore = false;
        this.books.addAll(books);
        this.totalItems = totalItems;
        adapter.notifyItemInserted(this.books.size());
        linNotFound.setVisibility(totalItems == books.size() ? View.VISIBLE : View.GONE);
    }

    private void setupAdapter(JsonObject resInitial) {
        System.out.println("setupAdapter " + resInitial);
        adapter = new BooksSearchAdapter(this, books, totalItems, (i, c)
                -> Log.d("CLICK", String.valueOf(c)));

        rclvSearchResult.setVisibility(totalItems == 0 ? View.GONE : View.VISIBLE);
        rclvSearchResult.setLayoutManager(new LinearLayoutManager(BooksActivity.this));
        rclvSearchResult.setAdapter(adapter);
        if (books.size() == totalItems) {
            adapter.notifyNoMoreData();
            isLoadingMore = true;
        }

        rclvSearchResult.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isLoadingMore) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    useCase.doGetNext(key, (currentPage + 1) * 20, 20);
                    isLoadingMore = true;
                }
            }
        });
    }
}
