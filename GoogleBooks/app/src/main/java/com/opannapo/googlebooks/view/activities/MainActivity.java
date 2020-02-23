package com.opannapo.googlebooks.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.JsonObject;
import com.opannapo.googlebooks.R;
import com.opannapo.googlebooks.core.view.BaseActivity;
import com.opannapo.googlebooks.usecase.main.IMainUsecase;
import com.opannapo.googlebooks.usecase.main.MainUsecase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by napouser on 23,February,2020
 */
public class MainActivity extends BaseActivity<MainUsecase> implements IMainUsecase.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.prbLoading)
    ProgressBar prbLoading;
    @BindView(R.id.laySearch)
    RelativeLayout laySearch;

    @Override
    public MainUsecase initUseCase() {
        return new MainUsecase(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreated(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                showSnack("Search " + edtSearch.getText(), true);
                useCase.doSearch(this, edtSearch.getText().toString());
            }
            return false;
        });

    }

    @Override
    public void onProgressSeaching() {
        prbLoading.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void onSearchError(String errMsg) {
        prbLoading.setVisibility(android.view.View.GONE);
        showSnack(errMsg, false);
    }

    @Override
    public void onSearchResult(JsonObject res, int totalItems) {
        prbLoading.setVisibility(android.view.View.GONE);

        Intent intent = new Intent(this, BooksActivity.class);
        Bundle extras = new Bundle();
        extras.putString("key", edtSearch.getText().toString());
        extras.putInt("totalItems", totalItems);
        extras.putString("res", res == null ? new JsonObject().toString() : res.toString());
        intent.putExtras(extras);
        startActivity(intent);
    }

}
